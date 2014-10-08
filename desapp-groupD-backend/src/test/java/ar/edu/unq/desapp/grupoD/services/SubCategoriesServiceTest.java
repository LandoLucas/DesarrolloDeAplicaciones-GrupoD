package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		
		SubCategory subcategory = new SubCategory("ventas");
		subcategoryService.save(subcategory);
		
		assertTrue(subcategory.getId() != null);

		List<SubCategory> subcategories = subcategoryService.findAll();
		assertEquals( 1 , subcategories.size());
		assertEquals( "ventas" , subcategories.get(0).getSubcategoryName());
	}
	
}
