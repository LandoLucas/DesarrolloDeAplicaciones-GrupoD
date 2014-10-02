package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class SaveOrUpdateCategoryOperation implements TransactionalOperation {

	private Category category;
	private HibernateManager manager;

	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}

	protected Category getCategory() {
		return category;
	}

	protected HibernateManager getManager() {
		return manager;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SaveOrUpdateCategoryOperation(Category category) {
		this.category = category;
	}

	public SaveOrUpdateCategoryOperation() {
	}

	@Override
	public void execute() {
		manager.getSession().saveOrUpdate(this.category);
	}
}
