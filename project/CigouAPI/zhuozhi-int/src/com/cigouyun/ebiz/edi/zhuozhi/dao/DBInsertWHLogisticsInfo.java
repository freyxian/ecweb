package com.cigouyun.ebiz.edi.zhuozhi.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Constants;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsInfo;
import com.cigouyun.ebiz.edi.zhuozhi.beans.LogisticsStatus;

public class DBInsertWHLogisticsInfo {

	private DataSource ds=null;
	
	/*
	public DataSource getdataSource() {
		return ds;
	}
	*/

	public void setdataSource(DataSource ds) {
		this.ds = ds;
	}


	public void dbInsertWHLogisticsInfo( LogisticsInfo bean ){
		
		String sql = "insert into wh_order_logistics values(?,?,?,?,?)";
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);

		// Object 
		jdbcTemp.update(sql, new Object[]{ 
				bean.getorderId(),
				bean.getwayBillNo(),
				bean.getlogisticsname(),
				bean.getnotes(),
				bean.getlogisticsId() }
				   
		);
		
		List<LogisticsStatus> list = bean.getlogisticsInfos();
		String orderId = bean.getorderId();
		
		if ( list != null ) {
			for ( LogisticsStatus status: list ) {
				dbInsertWHLogisticsStatus(orderId,status);
			}
			
		}
		
		return;
	}
	
	public void dbInsertWHLogisticsStatus( String orderId, LogisticsStatus bean ){
		
		String sql = "insert into wh_order_logistics_flow values(?,?,?,?,?)";
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);
		
		SimpleDateFormat formatter= new SimpleDateFormat( Constants.DATE_FORMAT);;

		String timeString = bean.getAcceptTime();
		
		Date date;
		try {
			date = formatter.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			date = new Date();
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(date.getTime());
		
		// Object 
		jdbcTemp.update(sql, new Object[]{ 
						orderId,
						bean.getacceptAddress(),
						time, //bean.getAcceptTime(),
						bean.getacceptStatus(),
						bean.getacceptNotes() }
						   
				);
		
		return;
	}

} //end of class



