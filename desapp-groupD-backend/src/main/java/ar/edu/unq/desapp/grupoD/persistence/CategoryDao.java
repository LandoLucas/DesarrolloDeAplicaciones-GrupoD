package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

/**
 * @author Lucas
 */
public class CategoryDao {

	public List<Category> getAllCategories() {
		return HibernateManager.getSessionFactory().openSession().createQuery("FROM Category").list();
	}

	public List<SubCategory> getAllSubCategories() {
		// TODO implement
		return new ArrayList<SubCategory>();
	}
	
	public void saveOrUpdateCategory(Category category) {
		SaveOrUpdateCategoryOperation operation = new SaveOrUpdateCategoryOperation(category);
		HibernateManager.executeWithTransaction(operation);
	}
	
	public void saveOrUpdateSubCategory(SubCategory subcategory) {
		//TODO implement
	}
	

}
