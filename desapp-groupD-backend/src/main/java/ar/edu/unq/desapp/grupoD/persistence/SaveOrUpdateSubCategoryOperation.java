package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class SaveOrUpdateSubCategoryOperation implements TransactionalOperation{

	private SubCategory subCategory;
	private HibernateManager manager;
	
	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}
	
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public SaveOrUpdateSubCategoryOperation(SubCategory subcategory) {
		this.subCategory = subcategory;
	}

	public SaveOrUpdateSubCategoryOperation() {}
	
	@Override
	public void execute() {
		manager.getSession().saveOrUpdate(this.subCategory);
	}

}
