package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Recipient;


/*
 * This class is to get data from following tables 
 * wh_order_header, 
 * wh_order_items
 * wh_order_recipient
 * and create Order object
 */
public class DBGetWHOrder {
	
	DataSource ds;
	
	public DataSource getDatasource() {
		return ds;
	}



	public void setDatasource(DataSource ds) {
		this.ds = ds;
	}


	public Order getOrder() {
		
		return null;
		
	}

	public Order dbGetWHOrder( String orderId ){
		
		String ordSql = "select * from wh_order_header where order_id=?";
		
		String itemSql = "select * from wh_order_items where order_id=?";
		
		String recSql = "select * from wh_order_recipient where order_id=?";
		
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);
		
		List<Order> ordList = jdbcTemp.query(ordSql, new Object[]{ orderId }, new WHOrderRowMapper() );
		
		Order order = ordList.get(0);
		
		List<Recipient> recList = jdbcTemp.query(recSql, new Object[]{ orderId }, new WHOrderRecipientRowMapper() );
		
		Recipient rec = recList.get(0);
		
		List<Good> goodList = jdbcTemp.query(itemSql, new Object[]{ orderId }, new WHOrderItemRowMapper() );
		
		
		
		order.setGoodList(goodList);
		order.setRecipient(rec);
		
		// jdbcTemp.queryForList(sql, new Object[]{ orderId }, Order.class);

		return order;
		
		
	}

}
