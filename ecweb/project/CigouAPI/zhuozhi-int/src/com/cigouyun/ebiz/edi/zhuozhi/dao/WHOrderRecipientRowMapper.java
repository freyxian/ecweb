package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Recipient;

public class WHOrderRecipientRowMapper implements RowMapper<Recipient> {

	@Override
	public Recipient mapRow(ResultSet rs, int rownum) throws SQLException {
		// TODO Auto-generated method stub
		Recipient rec = new Recipient();
		
		rec.setName(rs.getString("name") );
		rec.setReceiveNo(rs.getString("receive_no") );
		rec.setReceiveType(rs.getInt("receive_type") );
		rec.setMobilePhone(rs.getString("mobile_phone") );
		rec.setPhone(rs.getString("phone") );
		rec.setCountry(rs.getString("country") );
		rec.setCity(rs.getString("city") );
		rec.setProvince(rs.getString("province") );
		rec.setDistrict(rs.getString("district") );
		rec.setAddress(rs.getString("address") );
		rec.setPostCode(rs.getString("postCode") );
				
		return rec;
	}

}
