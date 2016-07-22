package ecweb.ecoupon.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;


public class ecstateDao {
	  private static final Logger logger = LoggerFactory.getLogger(ecstateDao.class);
private DataSource datasource;

private JdbcTemplate template=null;

private void init(){
	if (template==null)template=new JdbcTemplate(datasource);
}

@Autowired
public void setDataSource(DataSource dataSource){
	this.datasource=dataSource;
	template=new JdbcTemplate(datasource);
}

public int getecstate(){
	int ret=0;
	 String SQL = "select state from ecstate where id=1";
	 //init();
     Integer state = template.queryForObject(SQL,Integer.class);
     ret=state.intValue();
     
	return ret;
}

public double getecRate(){
	double ret=0;
	 String SQL = "select rate from ecstate where id=1";
	 //init();
     Double state = template.queryForObject(SQL,Double.class);
     ret=state.doubleValue();
     
	return ret;
}

public Ecoupon getECbyCode(String code){
	logger.debug("code="+code);
	Ecoupon ec;
	EcouponMapping ecm=new EcouponMapping(datasource);
	ec=ecm.findObject("ec_code", code);
	logger.debug(ec.toString());
	 return ec;
}

public boolean getPasswdVerify(String eccode, String passwd){
	//logger.debug("eccode="+eccode+"; passwd=passwd");
	String sql="SELECT COUNT(*) FROM ecoupon WHERE ec_code =? and passwd = SHA(?)";
	Integer result = template.queryForObject(sql,Integer.class,eccode,passwd);
	logger.debug("getPasswdVerify result="+result);
	if (result>0) return true;
	else return false;

}

public Ecoupon getECbyECCode(String code){
	logger.debug("code="+code);
	Ecoupon ec=null;
	String sql="SELECT COUNT(*) FROM ecoupon WHERE ec_code =?";
	Integer result = template.queryForObject(sql,Integer.class,code);
	if(result!=1) return ec;
	
	sql="select id,ec_code,number,state,goods_id,goods_des,goods_url,cell,passwd,name,wechat,ProcessNumber from ecoupon where ec_code=?";
	
	ec = this.template.queryForObject(
	        sql,
	        new Object[]{code},
	        new RowMapper<Ecoupon>() {
	            public Ecoupon mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Ecoupon ec = new Ecoupon();
	        		ec.setId(rs.getInt("id"));
	        		ec.setEc_code(rs.getString("ec_code"));
	        		ec.setNumber(rs.getInt("number"));
	        		ec.setState(rs.getInt("state"));
	        		ec.setGoods_id(rs.getInt("goods_id"));
	        		ec.setGoods_des(rs.getString("goods_des"));
	        		ec.setGoods_url(rs.getString("goods_url"));
	        		ec.setCell(rs.getString("cell"));
	        		if(rs.wasNull()) ec.setCell("");
	        		//ec.setPasswd(rs.getString("passwd"));
	        		ec.setName(rs.getString("name"));
	        		if(rs.wasNull()) ec.setName("");
	        		ec.setWechat(rs.getString("wechat"));
	        		if(rs.wasNull()) ec.setWechat("");
	        		ec.setProcessNumber(rs.getString("ProcessNumber"));
	        		if(rs.wasNull()) ec.setProcessNumber("");
	                return ec;
	            }
	        });
	 return ec;
}

public int updatePasswd(String eccode, String cell, String passwd,String name,String wechat){
	String sql;
	int result;
	if (wechat==null){
	 sql="update ecoupon set cell=?,passwd=sha(?),name=? where ec_code=?";
	 result=this.template.update(sql,cell,passwd,name,eccode);
	}
	else {
		sql="update ecoupon set cell=?,passwd=sha(?),name=?,wechat=? where ec_code=?";
		result=this.template.update(sql,cell,passwd,name,wechat,eccode);
	}

	return result;
}

//修改电子劵状态，写入购物信息. Here we need transaction control
@Transactional
public String  saveOrder(GoodsForm form){
	String pn=null;
	int result=0;
	String sql;

	sql="SELECT nextval('ProcessNumber') as next_sequence";
	Integer sequence = template.queryForObject(sql,Integer.class);
	pn=sequence.toString();
	if(pn.length()<7){
		int i = 6-pn.length();
		for(int j=0;j<i;j++) pn="0"+pn;
	}
	pn="D"+pn;
	logger.debug("SQL="+sql);
	
	sql="update ecoupon set state=1, edate=now(),ProcessNumber=? where ec_code=? and state=0";
	result=this.template.update(sql,pn,form.getEccode());
	if(result!=1) throw new RuntimeException();
	logger.debug("SQL="+sql);
	
	sql="insert into orders(ec_code,name,address,postcode,ProcessNumber,state,contact) values(?,?,?,?,?,?,?)";
	result=this.template.update(sql,form.getEccode(),form.getName(),form.getAddress(),form.getPostcode(),
			pn,0,form.getContact());
	if(result!=1) throw new RuntimeException();
	logger.debug("SQL="+sql);
	
	return pn;
}

@Transactional
public String  saveCashbckOrder(CashbackForm form){
	String pn=null;
	int result=0;
	String sql;
	
	sql="SELECT nextval('ProcessNumber') as next_sequence";
	Integer sequence = template.queryForObject(sql,Integer.class);
	pn=sequence.toString();
	if(pn.length()<7){
		int i = 6-pn.length();
		for(int j=0;j<i;j++) pn="0"+pn;
	}
	pn="C"+pn;
	
	sql="update ecoupon set state=1,edate=now(),ProcessNumber=? where ec_code=? and state=0";
	result=this.template.update(sql,pn,form.getEccode());
	logger.debug("update ecoupon set state=1; result="+result);
	if(result!=1) throw new RuntimeException();
	
	if(form.getAccountType().equals("bank")){
		sql="insert into cashback(ec_code,name,accountType,account,number,ProcessNumber,state) values(?,?,?,?,?,?,?)";
		result=this.template.update(sql,form.getEccode(),form.getName(),form.getAccountType(),form.getAccount(),form.getCashback(),pn,0);
	}else{
		sql="insert into cashback(ec_code,name,accountType,number,ProcessNumber,state) values(?,?,?,?,?,?)";
		result=this.template.update(sql,form.getEccode(),form.getName(),form.getAccountType(),form.getCashback(),pn,0);

	}
	if(result!=1) throw new RuntimeException();
	
	return pn;
}

public String getDelieverNum(String eccode){
	String sql="select delieverNum from orders where ec_code=?";
	String result = template.queryForObject(sql,String.class,eccode);
	
	return result;
}

public CashbackBean getCashback(String eccode){
	logger.debug("code="+eccode);
	String sql="select ec_code,name,account,number,state,ProcessNumber from cashback where ec_code=?";
	CashbackBean ec;
	
	ec = this.template.queryForObject(
	        sql,
	        new Object[]{eccode},
	        new RowMapper<CashbackBean>() {
	            public CashbackBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	CashbackBean ec = new CashbackBean();
	        		ec.setEccode(rs.getString("ec_code"));
	        		ec.setAccount(rs.getString("account"));
	        		ec.setState(rs.getInt("state"));
	        		ec.setNumber(rs.getDouble("number"));
	        		ec.setName(rs.getString("name"));
	        		ec.setProcessNumber(rs.getString("ProcessNumber"));
	                return ec;
	            }
	        });
	 return ec;
}
}
