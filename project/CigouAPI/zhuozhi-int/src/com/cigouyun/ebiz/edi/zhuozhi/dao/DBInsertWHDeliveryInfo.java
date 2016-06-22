package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Constants;
import com.cigouyun.ebiz.edi.zhuozhi.beans.DeliveryInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsStatus;

public class DBInsertWHDeliveryInfo {

	private DataSource ds=null;
	
	/*
	public DataSource getdataSource() {
		return ds;
	}
	*/

	public void setdataSource(DataSource ds) {
		this.ds = ds;
	}


	public void dbInsertWHDeliveryInfo( DeliveryInfo bean ){
		
		String sql = "insert into wh_order_delivery values(?,?,?,?,?)";
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);

		// Object 
		jdbcTemp.update(sql, new Object[]{ 
				bean.getenOrderCode(),
				bean.getwayBillNo(),
				bean.getlogisticsname(),
				bean.getnotes(),
				"" }
				   
		);
		
		
		
		return;
	}



	


} //end of class



