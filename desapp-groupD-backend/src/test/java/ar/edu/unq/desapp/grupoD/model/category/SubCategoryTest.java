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
	public void getAndSetSubCategory(){
		String subCategoryName = "Sells";
		
		SubCategory.setSubcategoryName(subCategoryName);
	
		assertEquals( subCategoryName , SubCategory.getSubcategoryName());
	}
	
	@Test
	public void getAndSetConceptTest(){
		Concept concept = mock(Concept.class);
		
		SubCategory.setConcept(concept);
		
		assertSame( concept , SubCategory.getConcept());
	}
	
}
