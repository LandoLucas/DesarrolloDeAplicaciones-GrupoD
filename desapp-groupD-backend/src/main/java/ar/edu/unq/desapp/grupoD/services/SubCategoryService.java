package ar.edu.unq.desapp.grupoD.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

public class SubCategoryService {

	private SubcategoryDao subcategoryDao;
	private CategoryDao categoryDao;
	
	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Transactional(readOnly=true)
	public List<SubCategory> findAll() {
		return subcategoryDao.findAll();
	}

	@Transactional
	public void removeSubcategoryByName(String name) {
		subcategoryDao.removeSubcategoryByName(name);
	}

	@Transactional
	public void save(SubCategory subcategory, Integer idCategory) {
		Category toUpdate = categoryDao.findById(idCategory);
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		subcategories.add(subcategory);
		toUpdate.setSubcategory(subcategories);
		categoryDao.save(toUpdate);
	}
	
	@Transactional
	public void save(SubCategory subcategory) {
		subcategoryDao.save(subcategory);
	}

}
