package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

/**
 * @author Lucas
 */
public class CategoryDao {

	private HibernateManager manager;
	private SaveOrUpdateCategoryOperation saveCategoryOperation;
	private SaveOrUpdateSubCategoryOperation subCategoryOperation;
	private DeleteCategoryOperation deleteCategoryOperation;
	private GetCategoriesOperation getOperation;

	public void setSubCategoryOperation(
			SaveOrUpdateSubCategoryOperation subCategoryOperation) {
		this.subCategoryOperation = subCategoryOperation;
	}

	public void setDeleteCategoryOperation(
			DeleteCategoryOperation deleteCategoryOperation) {
		this.deleteCategoryOperation = deleteCategoryOperation;
	}

	public void setSaveCategoryOperation(
			SaveOrUpdateCategoryOperation saveCategoryOperation) {
		this.saveCategoryOperation = saveCategoryOperation;
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
		saveCategoryOperation.setCategory(category);
		manager.executeWithTransaction(saveCategoryOperation);
	}

	public void saveOrUpdateSubCategory(SubCategory subcategory) {
		subCategoryOperation.setSubCategory(subcategory);
		manager.executeWithTransaction(subCategoryOperation);
	}

	public void removeCategory(String name) {
		deleteCategoryOperation.setCategoryName(name);
		manager.executeWithTransaction(deleteCategoryOperation);
	}

	public void removeSubCategory(String name) {
		// TODO Auto-generated method stub

	}

}
