package ar.edu.unq.desapp.grupoD.model.category;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class CategoryTest {

	
	@Test
	public void getAndSetCategory(){
		String categoryName = "Income";
		
		Category.setCategoryName(categoryName);
	
		assertEquals( categoryName , Category.getCategoryName());
	}
	
	
	@Test
	public void getAndSetSubcategoryTest(){
		SubCategory subcategory = mock(SubCategory.class);
		
		Category.setSubcategory(subcategory);
		
		assertSame( subcategory , Category.getSubcategory());
	}
	
}
