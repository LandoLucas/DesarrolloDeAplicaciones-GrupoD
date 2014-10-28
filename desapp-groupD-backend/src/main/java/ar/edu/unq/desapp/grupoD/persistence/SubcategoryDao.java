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

	public void removeSubcategoryByName(final String name) {
		List<SubCategory> cats =  this.getHibernateTemplate().findByExample(new SubCategory(name));
		this.getHibernateTemplate().delete(cats.get(0));
	}

}
