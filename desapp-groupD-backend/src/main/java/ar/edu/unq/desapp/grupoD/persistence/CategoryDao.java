package ar.edu.unq.desapp.grupoD.persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;

/**
 * @author Lucas
 */
public class CategoryDao extends HibernateGenericDAO<Category> implements
		GenericRepository<Category> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeCategoryByName(final String name) {
		this.getHibernateTemplate().delete(this.getByName(name));
	}
	
	public Category getByName(String name){
		List<Category> cats =this.getHibernateTemplate().findByExample(new Category(name));
		return cats.get(0);
	}

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}
	
	public void update(String name, Integer idCat){
		Category target = this.findById(idCat);
		target.setCategoryName(name);
		this.save(target);
	}

}
