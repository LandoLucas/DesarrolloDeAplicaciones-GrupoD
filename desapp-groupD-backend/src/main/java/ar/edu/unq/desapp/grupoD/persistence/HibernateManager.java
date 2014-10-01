package ar.edu.unq.desapp.grupoD.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Lucas
 */
public class HibernateManager {

	private static ThreadLocal<Session> sessionInThread = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory = getSessionFactory();
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
		    Configuration cfg = new Configuration();
			cfg.configure();

			sessionFactory = cfg.buildSessionFactory();
		}

		return sessionFactory;
	}

	/**
	 * Executes a series of commands in a transaction.
	 * @param operation
	 */
	public static void executeWithTransaction(TransactionalOperation operation){ 
			Session session = null;
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			sessionInThread.set(session);
			
			operation.execute();

			session.flush();
			session.getTransaction().commit();
			
		}catch(RuntimeException e){
			if(session != null)
				if(session.getTransaction().isActive())
					session.getTransaction().rollback();
			throw e;
		}finally{
			if(session != null)
				session.close();
		}

	}

	public static Session getSession() {
		return sessionInThread.get();
	}

}
