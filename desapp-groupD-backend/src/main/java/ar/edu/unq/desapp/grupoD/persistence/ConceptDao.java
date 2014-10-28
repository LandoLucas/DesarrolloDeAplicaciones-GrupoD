package ar.edu.unq.desapp.grupoD.persistence;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import ar.edu.unq.desapp.grupoD.model.category.Concept;


public class ConceptDao extends HibernateGenericDAO<Concept> implements
GenericRepository<Concept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5361052114639668786L;

	@Override
	protected Class<Concept> getDomainClass() {
		return Concept.class;
	}
	
	public void removeConceptByName(final String name) {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("delete from Concept where conceptName= :name").setParameter("name", name);
				query.executeUpdate();
				return null;
			}
			
		});
	}

}