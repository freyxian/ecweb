package com.cigouyun.ebiz.edi.zhuozhi.beans;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodEntryInfo  {
	
	DBGoodEntryInfo dbbean;
	
	public final static String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	private SimpleDateFormat formatter= new SimpleDateFormat(DATE_FORMAT);;

		
	public GoodEntryInfo (DBGoodEntryInfo b) {
		
		dbbean = b;
		return;
	}
	
	public GoodEntryInfo ( ) {
		
		dbbean = new DBGoodEntryInfo();
		return;
	}
	
	public DBGoodEntryInfo toDBGoodEntryInfo() {
		return dbbean;
	}

	public String getapplyTime() {
		
		String output = formatter.format(dbbean.getapplyTime());
		return output;
	}
	
	public void setapplyTime(String timeString ) {
		
		
		try {
			Date date = formatter.parse(timeString);
			Timestamp time = new Timestamp(date.getTime());
			dbbean.setapplyTime(time);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return;
	}
	
	 	public String getreportId() {
			return dbbean.getreportId();
		}
	 	
		public void setreportId(String reportId) {
			dbbean.setreportId(reportId);
			
		}
		
		public String getorderNo() {
			return dbbean.getorderNo();
		}
		
		public void setorderNo(String orderNo) {
			dbbean.setorderNo(orderNo);
			
		}

			
		public int getstatus() {
			return dbbean.getstatus();
		}
		
		public void setstatus(int status) {
			dbbean.setstatus(status);
		}
		
		public String getnotes() {
			return dbbean.getnotes();
			
		}
		
		public void setnotes(String notes) {
			dbbean.setnotes(notes);
			
		}

	
	
}
