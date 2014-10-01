package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class CategoryDaoTest {

	@Test
	public void saveAndLoadCategory(){
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-persistence-context.xml");
		
		CategoryDao categoryDao = (CategoryDao)factory.getBean("categoryDao");
		
		Category category = new Category("lucas");
		
		categoryDao.saveOrUpdateCategory(category);
		List<Category> categories = categoryDao.getAllCategories();
		
//		assertEquals( 1 , categories.size());
	}
	
}
