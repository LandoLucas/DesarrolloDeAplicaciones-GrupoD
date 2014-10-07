package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class CategoryDaoTest {

	private CategoryDao categoryDao;
	private SubcategoryDao subCategoryDao;
	
	@Before
	public void setUp(){
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-persistence-context.xml");
		categoryDao = (CategoryDao)factory.getBean("categoryDao");
		subCategoryDao = (SubcategoryDao)factory.getBean("subcategoryDao");
	}
	
	@Test
	public void saveAndLoadCategory(){
		Category category = new Category("pagos");
		
//		List<Category> categories = categoryDao.findAll();
//		assertEquals( 0 , categories.size());
		
		categoryDao.save(category);
		List<Category>categories = categoryDao.findAll();
		
		assertEquals( 1 , categories.size());
	}
	
//	@Test
//	public void removeCategory(){
//		categoryDao.save(new Category("pagos"));
//		categoryDao.save(new Category("sueldos"));
//
//		categoryDao.removeCategory("pagos");
//		
//		List<Category> categories = categoryDao.findAll();
//		assertEquals( 1 , categories.size());
//		
//		categoryDao.removeCategory("sueldos");
//		categories = categoryDao.findAll();
//		assertEquals( 0 , categories.size());
//	}
//	
//	@Test
//	public void saveAndLoadSubcategory(){
//		SubCategory subcategory = new SubCategory("pagos");
//		
//		List<SubCategory> subcategories = categoryDao.getAllSubCategories();
//		assertEquals( 0 , subcategories.size());
//		
//		categoryDao.saveOrUpdateSubCategory(subcategory);
//		subcategories = categoryDao.getAllSubCategories();
//		
//		assertEquals( 1 , subcategories.size());
//	}
//	
//	@Test
//	public void removeSubCategory(){
//		categoryDao.saveOrUpdateSubCategory(new SubCategory("pagos"));
//		categoryDao.saveOrUpdateSubCategory(new SubCategory("sueldos"));
//
//		categoryDao.removeSubCategory("pagos");
//		
//		List<SubCategory> subcategories = categoryDao.getAllSubCategories();
//		assertEquals( 1 , subcategories.size());
//		
//		categoryDao.removeSubCategory("sueldos");
//		subcategories = categoryDao.getAllSubCategories();
//		assertEquals( 0 , subcategories.size());
//	}	
}
