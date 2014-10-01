package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class SaveOrUpdateCategoryOperation implements TransactionalOperation{

	private Category category;
	
	public SaveOrUpdateCategoryOperation(Category category) {
		this.category = category;
	}
	
	@Override
	public void execute() {
		HibernateManager.getSession().saveOrUpdate(this.category);
	}
}
