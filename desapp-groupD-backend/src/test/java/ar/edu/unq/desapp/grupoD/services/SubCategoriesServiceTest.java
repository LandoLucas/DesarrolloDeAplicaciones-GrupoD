package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

@ContextConfiguration(locations = {"classpath:spring-persistence-context.xml"})
public class SubCategoriesServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	SubCategoryService subcategoryService;
	
	@Test
	public void saveAndLoadSubCategory(){
		SubCategory subcategory = new SubCategory("ventas test");
		
		subcategoryService.save(subcategory);

		List<SubCategory> subcategories = subcategoryService.findAll();
		
		assertEquals( 1 , subcategories.size());
		assertTrue(subcategories.contains(subcategory));
	}
	
	
	@Test
	public void deleteSubcategoryTest(){
		SubCategory subcategory = new SubCategory("ventas");
		subcategoryService.save(subcategory);

		subcategoryService.removeSubcategoryByName("ventas");
		assertEquals( 0 , subcategoryService.findAll().size());
	}
	
}
