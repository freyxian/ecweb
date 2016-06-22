package com.cigouyun.ebiz.dao;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Example;

import CigouDAO.cigoudb.HibernateUtil;
import CigouDAO.cigoudb.WhOrderItems;
import CigouDAO.cigoudb.WhOrderItemsHome;
import CigouDAO.cigoudb.WhOrderItemsId;
public class TestHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log log = LogFactory.getLog(TestHibernate.class);
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		try {
			SessionFactory sessionFactory= (SessionFactory) new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			Session session = sessionFactory.openSession();
		

session.beginTransaction();
List result = session.createQuery( "from WhOrderItems" ).list();
for ( WhOrderItems event : (List<WhOrderItems>) result ) {
    System.out.println( "WhOrderItems (" + event.getId() + ") : " );
}
session.getTransaction().commit();
session.close();
	} catch (Exception e) {
		log.error("Could not locate SessionFactory in JNDI", e);
		throw new IllegalStateException("Could not locate SessionFactory in JNDI");
	}

	}

}
