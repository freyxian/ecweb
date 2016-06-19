package ecweb.ecoupon.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class EcouponMapping extends MappingSqlQuery<Ecoupon>{

	public EcouponMapping(DataSource ds){
		super(ds, "select id,ec_code,number,state,goods_id,goods_des,goods_url from ecoupon where ec_code=:ec_code");
        super.declareParameter(new SqlParameter("ec_code", Types.VARCHAR));
        compile();
	}
	
	@Override
	protected Ecoupon mapRow(ResultSet rs, int rowNumber) throws SQLException {
		// TODO Auto-generated method stub
		if(rowNumber==0) return null;
		
		Ecoupon ec=new Ecoupon();
		ec.setId(rs.getInt("id"));
		ec.setEc_code(rs.getString("ec_code"));
		ec.setNumber(rs.getInt("number"));
		ec.setState(rs.getInt("state"));
		//ec.setMethod(rs.getInt("method"));
		//ec.setCell(rs.getString("name"));
		//ec.setCell(rs.getString("cell"));
		//ec.setAddress(rs.getString("address"));
		//ec.setPostcode(rs.getString("postcode"));
		ec.setGoods_id(rs.getInt("goods_id"));
		ec.setGoods_des(rs.getString("goods_des"));
		ec.setGoods_url(rs.getString("goods_url"));
		//ec.setWechat(rs.getString("wechat"));
		//ec.setCashback(rs.getInt("cashback"));
		//ec.setProcessNumber(rs.getString("ProcessNumber"));
		return ec;
	}

}
