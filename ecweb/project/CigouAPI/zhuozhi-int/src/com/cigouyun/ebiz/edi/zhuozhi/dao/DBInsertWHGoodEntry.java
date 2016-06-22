package com.cigouyun.ebiz.edi.zhuozhi.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cigouyun.ebiz.edi.zhuozhi.beans.DBGoodEntryInfo;

public class DBInsertWHGoodEntry {

	private DataSource ds=null;
	
	/*
	public DataSource getdataSource() {
		return ds;
	}
	*/

	public void setdataSource(DataSource ds) {
		this.ds = ds;
	}


	public void dbInsertWHDeliveryInfo( DBGoodEntryInfo bean ){
		
		String sql = "insert into wh_goods_entry values(?,?,?,?,?)";
		JdbcTemplate jdbcTemp = new JdbcTemplate(ds);

		// Object 
		jdbcTemp.update(sql, new Object[]{ 
				bean.getorderNo(),
				bean.getreportId(),
				bean.getapplyTime(),
				bean.getstatus(),
				bean.getnotes()
				 }
				   
		);
		
		
		
		return;
	}



	


} //end of class



