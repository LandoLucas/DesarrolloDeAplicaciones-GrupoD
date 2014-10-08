package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

public class SubCategoryService {

	private SubcategoryDao subcategoryDao;
	
	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
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
	public void save(SubCategory subcategory) {
		subcategoryDao.save(subcategory);
	}

}
