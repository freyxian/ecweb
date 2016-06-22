package com.cigouyun.ebiz.edi.zhuozhi.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

//import CigouDAO.cigoudb.HibernateUtil;
//import CigouDAO.cigoudb.WhOrderHeader;
//import CigouDAO.cigoudb.WhOrderHeaderHome;
//import CigouDAO.cigoudb.WhOrderItems;
//import CigouDAO.cigoudb.WhOrderItemsHome;
//import CigouDAO.cigoudb.WhOrderItemsId;
//import CigouDAO.cigoudb.WhOrderRecipient;
//import CigouDAO.cigoudb.WhOrderRecipientHome;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cigouyun.ebiz.edi.zhuozhi.beans.Good;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Order;
import com.cigouyun.ebiz.edi.zhuozhi.beans.Recipient;
import com.cigouyun.ebiz.edi.zhuozhi.dao.DBGetWHOrder;
import com.cigouyun.ebiz.edi.zhuozhi.dao.WHOrderRowMapper;
import com.echolab.common.batch.IListCreator;
import com.echolab.common.log.BasicLogger;
import com.echolab.commons.directory.ListFiles;

public class ZZDBOrderListCreator extends BasicLogger implements IListCreator<Order>{

	ObjectMapper mapper = new ObjectMapper();
	//ListFiles fileListor = new ListFiles();
	
	private String order_id="00043473809";
	
	private String whereClause="";
	
	private DataSource dataSource;
	
	
	public ZZDBOrderListCreator () {
		
		// Locale myLocale = new Locale("zh", "CN");
		return;
	}
	
	
	public String getwhereClause() {
		return whereClause;
	}
	
	public void setwhereClause(String v) {
		whereClause= v;
		return;
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public List<Order> createList() {
		
		List<Order> orderList = new ArrayList<Order>();
	
		StringBuffer sb = new StringBuffer();

		String ordSql = "select order_id from wh_order_header oh where "+whereClause;
		JdbcTemplate jdbcTemp = new JdbcTemplate(this.getDataSource());

		
		List<String> ordList = jdbcTemp.queryForList(ordSql, String.class);
				// (ordSql, String.class );

		for ( String order_id: ordList){
			Order order = jdbcDBGetOrder( order_id );
			orderList.add(order);
		}
		
		return orderList;
	}
	
	
	public List<Order> createList(String x) {
		
		List<Order> orderList = new ArrayList<Order>();
	
		//BufferedReader fr;
		StringBuffer sb = new StringBuffer();
	
		// WhOrderHeader woh = dBGetOrder( order_id );
		Order order = jdbcDBGetOrder( order_id );
		//Order order = toOrder(woh);
		
		orderList.add(order);
		
		System.out.println("CustomerId:"+order.getCustomerId());
		
		
		Recipient rec = order.getRecipient();
		System.out.println("Name:"+rec.getName());
		System.out.println("mobile:"+rec.getMobilePhone());

		try {
			mapper.writeValue(new File("tmp\\"+order.getOrderId()+".json"), order);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return orderList;
	}
	
	private Order jdbcDBGetOrder(String orderId ) {
		DBGetWHOrder db = new DBGetWHOrder();
		
		db.setDatasource(this.dataSource);
		return db.dbGetWHOrder(orderId);
	}
	
	/*
	@SuppressWarnings("unused")
	private WhOrderHeader dBGetOrder( String orderId ) {
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// TODO Auto-generated method stub
		
		WhOrderHeaderHome wohHome = new WhOrderHeaderHome();
		WhOrderHeader oh = wohHome.findById(orderId);
		
		
		String hql = "from WhOrderItems WHERE id.orderId = :order_id";
		Query query = session.createQuery(hql);
		query.setParameter("order_id",orderId);
		List<WhOrderItems> results = (List<WhOrderItems>) query.list();
		
		// List results = query.list();
				
		/ *
		WhOrderItemsHome woiHome = new WhOrderItemsHome();
		
		WhOrderItems instance = new WhOrderItems() ;
		WhOrderItemsId id = new WhOrderItemsId();
		id.setOrderId(orderId);
		instance.setId(id );
		List list = woiHome.findByExample(instance );
		* /
				
		Set set = new TreeSet();
		set.addAll(results);
		oh.setWhOrderItemses(set);
		
		WhOrderRecipientHome worHome = new WhOrderRecipientHome();
		WhOrderRecipient wor = worHome.findById(orderId);
		
		oh.setWhOrderRecipient(wor);
		
		
		// WhOrderItemsId woiId = new WhOrderItemsId("00043473809","深海鱼油");
		// WhOrderItems woi=woiHome.findById(woiId);
		
		
		session.getTransaction().commit();
		
		return oh;
		
		
	}
	*/
	
	/*
	public static synchronized Order toOrder( WhOrderHeader woh ) {
		
		Order order = new Order();
		List<Good> list = new ArrayList<Good>();
		
		order.setOrderId( woh.getOrderId()    );
		order.setOrderDate( woh.getOrderDate()    );
		order.setCustomerId( woh.getCustomerId()    );
		order.setPackingMaterial( woh.getPackingMaterial()    );
		order.setWarehouseId(  woh.getWarehouseId()  );
		order.setTpl(  woh.getTpl()	   );
		order.setOrderType( woh.getOrderType()    );
		order.setElectriccode(  woh.getElectriccode()   );
		order.setCbepcomcode(   woh.getCbepcomcode()  );
		order.setDeliveryCode(  woh.getDeliveryCode()   );
		order.setNotes(  woh.getNotes()   );
		
		
		WhOrderRecipient wor = woh.getWhOrderRecipient();
		
		Recipient rec = toOrderRecipient( wor );
		
		order.setRecipient(rec);
		
		for ( Object obj : woh.getWhOrderItemses()) {
			
			WhOrderItems item = (WhOrderItems)obj; 
			Good good = toGood(item);
			list.add(good);
			
		}
		
		return order;
		
	}
	*/
	
	/*
	public static synchronized Good toGood( 
			WhOrderItems item ) {
		
		Good good = new Good();
		
		good.setAmount(item.getAmount());
		good.setGoodId(item.getId().getGoodId());
		good.setPrice(item.getPrice());
		
		return good;
		
		
	}
	*/
	
	/*
	
	public static synchronized Recipient toOrderRecipient( 
			WhOrderRecipient wor ) {
		
		Recipient rec = new Recipient();
		
		rec.setName( wor.getName(  )  ); 
		rec.setReceiveType( wor.getReceiveType(  ) );
		rec.setReceiveNo( wor.getReceiveNo(  ) );
		rec.setMobilePhone( wor.getMobilePhone(  ) );
		rec.setPhone( wor.getPhone(  ) );
		rec.setCountry( wor.getCountry(  ) );
		rec.setProvince( wor.getProvince(  ) );
		rec.setCity( wor.getCity(  ) );
		rec.setDistrict( wor.getDistrict(  ) );
		rec.setAddress( wor.getAddress(  ) );
		rec.setPostCode( wor.getPostcode() );
		
		return rec;
		
	}
*/



} // end of class
