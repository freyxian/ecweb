package CigouDAO.cigoudb;
// Generated 2016-4-16 23:41:33 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class WhRefTpl.
 * @see CigouDAO.cigoudb.WhRefTpl
 * @author Hibernate Tools
 */
public class WhRefTplHome {

	private static final Log log = LogFactory.getLog(WhRefTplHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(WhRefTpl transientInstance) {
		log.debug("persisting WhRefTpl instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(WhRefTpl instance) {
		log.debug("attaching dirty WhRefTpl instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WhRefTpl instance) {
		log.debug("attaching clean WhRefTpl instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(WhRefTpl persistentInstance) {
		log.debug("deleting WhRefTpl instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WhRefTpl merge(WhRefTpl detachedInstance) {
		log.debug("merging WhRefTpl instance");
		try {
			WhRefTpl result = (WhRefTpl) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WhRefTpl findById(java.lang.String id) {
		log.debug("getting WhRefTpl instance with id: " + id);
		try {
			WhRefTpl instance = (WhRefTpl) sessionFactory.getCurrentSession().get("CigouDAO.cigoudb.WhRefTpl", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<WhRefTpl> findByExample(WhRefTpl instance) {
		log.debug("finding WhRefTpl instance by example");
		try {
			List<WhRefTpl> results = (List<WhRefTpl>) sessionFactory.getCurrentSession()
					.createCriteria("CigouDAO.cigoudb.WhRefTpl").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
