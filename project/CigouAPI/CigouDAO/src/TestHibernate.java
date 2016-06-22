

import java.util.List;
import org.hibernate.Session;

import CigouDAO.cigoudb.HibernateUtil;
import CigouDAO.cigoudb.WhOrderHeader;
import CigouDAO.cigoudb.WhOrderItems;
import CigouDAO.cigoudb.WhOrderItemsHome;
import CigouDAO.cigoudb.WhOrderItemsId;
public class TestHibernate {

	public static void main(String[] args) {
/*		if (HibernateUtil.getSessionFactory().getCurrentSession()==null){
			SessionFactory sessionf=HibernateUtil.getSessionFactory();
			Session session = sessionf.openSession();
			sessionf.
		}*/
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// TODO Auto-generated method stub
		WhOrderItemsId woiId = new WhOrderItemsId("00043473809","深海鱼油");
		WhOrderItemsHome woiHome = new WhOrderItemsHome();
		WhOrderItems woi=woiHome.findById(woiId);
		if(woi==null)System.out.println( "WhOrderItems not found");
		else System.out.println( "WhOrderItems ("+woi.getId().getGoodId()+";"+woi.getId().getOrderId());
		
		List<WhOrderItems> list = woiHome.findByOrderID("00043473809");
		for ( WhOrderItems i:list) System.out.println( i.getId().getGoodId());
		
		//woi.getId().setGoodId("goodId");
		woi.setAmount(10000);
		session.save(woi);
		
		WhOrderHeader woh=new WhOrderHeader();
		woh.setOrderId("orderId");
		woh.setCustomerId("customer");
		session.save(woh);

		session.getTransaction().commit();

	}

}
