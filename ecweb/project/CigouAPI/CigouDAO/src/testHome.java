import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import CigouDAO.cigoudb.*;

public class testHome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log log = LogFactory.getLog(TestHibernate.class);
		
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

session.beginTransaction();
List result = session.createQuery( "from WhOrderItems" ).list();
for ( WhOrderItems event : (List<WhOrderItems>) result ) {
    System.out.println( "WhOrderItems (" + event.getId().getGoodId() + ") : "+event.getId().getOrderId() );
}
session.getTransaction().commit();
session.close();
	} catch (Exception e) {
		log.error("Could not locate SessionFactory in JNDI", e);
		throw new IllegalStateException("Could not locate SessionFactory in JNDI");
	}
		

	}

}
