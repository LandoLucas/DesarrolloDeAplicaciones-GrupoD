package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.category.Category;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class CategoryServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	CategoryService categoryService;
	
	@Test
	public void saveAndLoadCategory(){
		
		Category category = new Category("ventas");
		categoryService.save(category);
		
		assertTrue(category.getId() != null);

		List<Category> categories = categoryService.findAll();
		assertEquals( 1 , categories.size());
		assertEquals( "ventas" , categories.get(0).getCategoryName());
	}
	
	@Test
	public void deleteCategory(){
		Category category = new Category("ventas");
		categoryService.save(category);
		List<Category> categories = categoryService.findAll();
		assertEquals( 1 , categories.size());
		
		categoryService.removeCategory("ventas");
		
		categories = categoryService.findAll();
		assertEquals( 0 , categories.size());
	}
	
}
