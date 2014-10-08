package ar.edu.unq.desapp.grupoD.persistence;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import ar.edu.unq.desapp.grupoD.model.category.Category;

/**
 * @author Lucas
 */
public class CategoryDao extends HibernateGenericDAO<Category> implements
		GenericRepository<Category> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeCategory(final String name) {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String hql = "delete from Category where categoryName= :name";
				  Query query = session.createQuery(hql);
				  query.setString("categoryName", name);
				  query.executeUpdate();
				return null;
			}
			
		});
	}

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}

}
