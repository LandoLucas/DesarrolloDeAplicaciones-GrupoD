package ar.edu.unq.desapp.grupoD.persistence;

import java.sql.SQLException;
import java.util.List;

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
	public void removeCategoryByName(final String name) {
		List<Category> cats =  this.getHibernateTemplate().findByExample(new Category(name));
		this.getHibernateTemplate().delete(cats.get(0));
	}

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}

}
