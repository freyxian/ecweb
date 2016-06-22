package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;

public class WHOrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		Order order = new Order();
		
		order.setOrderId(rs.getString("order_id") );
		order.setOrderDate(rs.getString("orderDate") );
		order.setCustomerId(rs.getString("customerId") );
		order.setPackingMaterial(rs.getString("packingMaterial") );
		order.setWarehouseId(rs.getString("warehouseId") );
		order.setTpl(rs.getString("tpl") );
		order.setOrderType(rs.getInt("orderType") );
		order.setElectriccode(rs.getString("electriccode") );
		order.setDeliveryCode(rs.getString("deliveryCode") );
		order.setNotes(rs.getString("notes" ));
		order.setCbepcomcode(rs.getString("cbepcomcode") );
				
		return order;
	}

}
