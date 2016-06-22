package CigouDAO.CDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import CigouDAO.cigoudb.HibernateUtil;
import CigouDAO.cigoudb.WhOrderHeader;
import CigouDAO.cigoudb.WhOrderHeaderHome;
import CigouDAO.cigoudb.WhOrderItems;
import CigouDAO.cigoudb.WhOrderItemsHome;
import CigouDAO.cigoudb.WhOrderRecipient;
import CigouDAO.cigoudb.WhOrderRecipientHome;
import CigouDAO.cigoudb.WhRefTpl;

public class OrderFetch {
public	WholeOrder fetchWholeOrder(String orderid){
	WholeOrder wo=new WholeOrder();
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	WhOrderHeaderHome whh = new WhOrderHeaderHome(); 
	WhOrderHeader wh = whh.findById(orderid);
	wo.setHeader(wh);
	
	WhOrderRecipientHome wrh=new WhOrderRecipientHome();
	WhOrderRecipient wr=wrh.findById(orderid);
	wo.setRecipient(wr);
	
	WhOrderItemsHome wih=new WhOrderItemsHome();
	List<WhOrderItems> lwi=wih.findByOrderID(orderid);
	wo.setItems(lwi);
	
	return wo;
}

public void saveWholeOrder(WholeOrder wo){
	wo.getHeader().setWhOrderRecipient(wo.getRecipient());
	Set<WhOrderItems> mySet = new HashSet<WhOrderItems>(wo.getItems());
	wo.getHeader().setWhOrderItemses(mySet);
	WhOrderHeaderHome wohh = new WhOrderHeaderHome();
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
	//wohh.persist(wo.getHeader());0000017
	WhRefTpl tpl=new WhRefTpl("0000017","顺丰");
	wo.getHeader().setWhRefTpl(tpl);
	session.save(wo.getHeader());
	session.getTransaction().commit();
	session.close();
}
}

