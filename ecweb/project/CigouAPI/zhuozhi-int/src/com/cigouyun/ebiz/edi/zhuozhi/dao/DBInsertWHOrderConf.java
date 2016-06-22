package com.cigouyun.ebiz.edi.zhuozhi.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.OrderResponse;

public class DBInsertWHOrderConf {

	private DataSource ds=null;
	
	/*
	public DataSource getdataSource() {
		return ds;
	}
	*/

	public void setdataSource(DataSource ds) {
		this.ds = ds;
	}


	public void dbInsertWHOrderConfirm( OrderResponse res ){
		
		String sql = "insert into wh_order_confirm values(?,?,?)";
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);

		// Object 
		jdbcTemp.update(sql, new Object[]{ res.getOrderId(),
				res.getStatus(),res.getNotes() }   );
		return;
	}

}



