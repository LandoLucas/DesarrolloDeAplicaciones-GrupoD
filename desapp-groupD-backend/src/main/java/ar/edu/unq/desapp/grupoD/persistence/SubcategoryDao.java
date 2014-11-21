package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class SubcategoryDao extends HibernateGenericDAO<SubCategory> implements
		GenericRepository<SubCategory> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<SubCategory> getDomainClass() {
		return SubCategory.class;
	}

	public void removeSubcategoryByName(final String name, Integer categoryId) {
		this.getHibernateTemplate().delete(this.getByName(name));
	}
	
	public SubCategory getByName(String name){
		List<SubCategory> subcategories =this.getHibernateTemplate().findByExample(new SubCategory(name));
		if(subcategories.isEmpty())return null;
		return subcategories.get(0);
	}
	
	public void update(String name, Integer idSub){
		SubCategory target = this.findById(idSub);
		target.setSubcategoryName(name);
		this.save(target);
	}

}
