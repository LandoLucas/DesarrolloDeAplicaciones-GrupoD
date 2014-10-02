package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class CategoryDaoTest {

	@Test
	public void saveAndLoadCategory(){
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-persistence-context.xml");
		CategoryDao categoryDao = (CategoryDao)factory.getBean("categoryDao");
		Category category = new Category("pagos");
		
		List<Category> categories = categoryDao.getAllCategories();
		assertEquals( 0 , categories.size());
		
		categoryDao.saveOrUpdateCategory(category);
		categories = categoryDao.getAllCategories();
		
		assertEquals( 1 , categories.size());
	}
	
	@Test
	public void saveAndLoadSubcategory(){
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-persistence-context.xml");
		CategoryDao categoryDao = (CategoryDao)factory.getBean("categoryDao");
		
		SubCategory subcategory = new SubCategory("pagos");
		
		List<SubCategory> subcategories = categoryDao.getAllSubCategories();
		assertEquals( 0 , subcategories.size());
		
		categoryDao.saveOrUpdateSubCategory(subcategory);
		subcategories = categoryDao.getAllSubCategories();
		
		assertEquals( 1 , subcategories.size());
		
	}
}
