package ar.edu.unq.desapp.grupoD.model.category;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

/**
 * @author Lucas
 */
public class SubCategoryTest {

	
	@Test
	public void subCategoryConstructor(){
		String subCategoryName = "Sells";

		SubCategory subCategory = new SubCategory(subCategoryName);
		
		assertEquals( subCategoryName , subCategory.getSubcategoryName());
	}
	
	@Test
	public void getAndSetConceptTest(){
		Concept concept = mock(Concept.class);
		SubCategory subcategory = new SubCategory("name");
		
		subcategory.setConcept(concept);
		
		assertSame( concept , subcategory.getConcept());
	}
	
}
