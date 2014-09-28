package ar.edu.unq.desapp.grupoD.model.category;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
		SubCategory subcategory = mock(SubCategory.class);
		Category category = new Category("categoryName");
		
		category.setSubcategory(subcategory);
		
		assertSame( subcategory , category.getSubcategory());
	}
	
}
