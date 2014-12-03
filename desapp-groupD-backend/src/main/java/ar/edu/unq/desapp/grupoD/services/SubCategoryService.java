package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

@Transactional
public class SubCategoryService {

	private SubcategoryDao subcategoryDao;
	private CategoryDao categoryDao;

	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Transactional(readOnly = true)
	public List<SubCategory> findAll() {
		return subcategoryDao.findAll();
	}

	@Transactional
	public void removeSubcategoryByName(String name, Integer idCategory){
		Category target = categoryDao.findById(idCategory);
		SubCategory subcategory = subcategoryDao.getByName(name);
		target.getSubcategory().remove(subcategory);
		categoryDao.save(target);
	}

	@Transactional
	public void save(SubCategory subcategory, Integer idCategory) {
		Category toUpdate = categoryDao.findById(idCategory);
		List<SubCategory> subcategories = toUpdate.getSubcategory();
		subcategories.add(subcategory);
		categoryDao.save(toUpdate);
	}

	@Transactional
	public void save(SubCategory subcategory) {
		subcategoryDao.save(subcategory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(String newName, Integer idSub) {
		subcategoryDao.update(newName, idSub);
	}

	@Transactional
	public SubCategory findByName(String subCategoryName) {
		return this.subcategoryDao.getByName(subCategoryName);
	}

}
