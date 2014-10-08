package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class SubcategoryDao extends HibernateGenericDAO<SubCategory> implements
		GenericRepository<SubCategory> {

	@Override
	protected Class<SubCategory> getDomainClass() {
		return SubCategory.class;
	}

	public void removeSubcategoryByName(String name) {
		// TODO Auto-generated method stub
		
	}

}
