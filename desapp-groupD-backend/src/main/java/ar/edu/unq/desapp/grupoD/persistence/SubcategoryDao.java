package ar.edu.unq.desapp.grupoD.persistence;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class SubcategoryDao extends HibernateGenericDAO<SubCategory> implements
		GenericRepository<SubCategory> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SubCategory> getDomainClass() {
		return SubCategory.class;
	}

	public void removeSubcategoryByName(final String name) {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("delete from SubCategory where subcategoryName= :name").setParameter("name", name);
				query.executeUpdate();
				return null;
			}
			
		});
	}

}
