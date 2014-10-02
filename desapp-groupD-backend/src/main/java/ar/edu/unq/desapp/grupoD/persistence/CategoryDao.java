package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

/**
 * @author Lucas
 */
public class CategoryDao {

	private HibernateManager manager;
	private SaveOrUpdateCategoryOperation categoryOperation;
	private SaveOrUpdateSubCategoryOperation subCategoryOperation;
	private GetCategoriesOperation getOperation;

	public void setSubCategoryOperation(
			SaveOrUpdateSubCategoryOperation subCategoryOperation) {
		this.subCategoryOperation = subCategoryOperation;
	}

	public void setCategoryOperation(
			SaveOrUpdateCategoryOperation categoryOperation) {
		this.categoryOperation = categoryOperation;
	}

	public void setGetOperation(GetCategoriesOperation getOperation) {
		this.getOperation = getOperation;
	}

	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}

	public List<Category> getAllCategories() {
		manager.executeWithTransaction(getOperation);
		return getOperation.getCategories();
	}

	public List<SubCategory> getAllSubCategories() {
		return manager.getSession().createQuery("FROM SubCategory").list();
	}

	public void saveOrUpdateCategory(Category category) {
		categoryOperation.setCategory(category);
		manager.executeWithTransaction(categoryOperation);
	}

	public void saveOrUpdateSubCategory(SubCategory subcategory) {
		subCategoryOperation.setSubCategory(subcategory);
		manager.executeWithTransaction(subCategoryOperation);
	}

	public void removeCategory(String name) {
		// TODO Auto-generated method stub

	}

	public void removeSubCategory(String name) {
		// TODO Auto-generated method stub

	}

}
