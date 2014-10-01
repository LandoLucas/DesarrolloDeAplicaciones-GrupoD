package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class CategoryDaoTest {

	@Test
	public void saveAndLoadCategory(){
		CategoryDao categoryDao = new CategoryDao();
		Category category = new Category("lucas");
		
		categoryDao.saveOrUpdateCategory(category);
		List<Category> categories = categoryDao.getAllCategories();
		
		assertEquals( 1 , categories.size());
	}
	
}
