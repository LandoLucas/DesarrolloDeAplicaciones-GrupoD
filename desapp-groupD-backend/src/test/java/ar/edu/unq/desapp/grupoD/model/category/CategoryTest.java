package ar.edu.unq.desapp.grupoD.model.category;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * @author Lucas
 */
public class CategoryTest {

	
	@Test
	public void categoryConstructor(){
		String categoryName = "Income";

		Category category = new Category(categoryName);
		
		assertEquals( categoryName , category.getCategoryName());
	}
	
	
	@Test
	public void getAndSetSubcategoryTest(){
		SubCategory subcategory = new SubCategory();
		Category category = new Category("categoryName");
		
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		subcategories.add(subcategory);
		category.setSubcategory(subcategories);
		
		assertTrue(category.getSubcategory().contains(subcategory));
	}
	
}
