package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Category;

/**
 * @author Lucas
 */
public class CategoryDao extends HibernateGenericDAO<Category> implements
		GenericRepository<Category> {

	private static final long serialVersionUID = 1L;

	public void removeCategoryByName(final String name) {
		this.getHibernateTemplate().delete(this.getByName(name));
	}
	
	public Category getByName(String name){
		List<Category> categories = this.getHibernateTemplate().findByExample(new Category(name));
		if(categories.isEmpty())return null;
		return categories.get(0);
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
