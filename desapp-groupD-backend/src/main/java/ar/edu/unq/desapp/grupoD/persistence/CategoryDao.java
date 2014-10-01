package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

/**
 * @author Lucas
 */
public class CategoryDao {

	private HibernateManager manager;
	private SaveOrUpdateCategoryOperation operation;
	private GetCategoriesOperation getOperation;
	
	public void setOperation(SaveOrUpdateCategoryOperation operation) {
		this.operation = operation;
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
		// TODO implement
		return new ArrayList<SubCategory>();
	}
	
	public void saveOrUpdateCategory(Category category) {
		operation.setCategory(category);
		manager.executeWithTransaction(operation);
	}
	
	public void saveOrUpdateSubCategory(SubCategory subcategory) {
		//TODO implement
	}
	

}
