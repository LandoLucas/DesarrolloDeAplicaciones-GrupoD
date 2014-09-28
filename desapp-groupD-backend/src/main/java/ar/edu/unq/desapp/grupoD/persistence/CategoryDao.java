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
		List<Category> list = new ArrayList<Category>();
		list.add(new Category("asd"));
		return list;
	}

	public List<SubCategory> getAllSubCategories() {
		// TODO implement
		return new ArrayList<SubCategory>();
	}
	
	public void saveOrUpdateCategory(Category category) {
		//TODO implement
	}
	
	public void saveOrUpdateSubCategory(SubCategory subcategory) {
		//TODO implement
	}
	

}
