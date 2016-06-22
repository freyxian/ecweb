package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;

public class WHOrderItemRowMapper implements RowMapper<Good> {

	@Override
	public Good mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		Good good = new Good();
		
		good.setAmount(rs.getInt("amount") );
		good.setPrice(rs.getDouble("price") );
		good.setGoodId(rs.getString("good_id") );
		
				
		return good;
	}

}
