package ar.edu.unq.desapp.grupoD.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Lucas
 */
public class HibernateManager {

	private SessionFactory sessionFactory;
	private ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Executes a series of commands in a transaction.
	 * @param operation
	 */
	public void executeWithTransaction(TransactionalOperation operation){ 
			Session session = null;
		try{
			session = sessionFactory.openSession();
			threadLocal.set(session);
			
			session.beginTransaction();
			
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
	
	public Session getSession() {
		return threadLocal.get();
	}

}
