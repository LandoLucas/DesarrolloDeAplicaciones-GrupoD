package ar.edu.unq.desapp.grupoD.model.category;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		Concept concept = new Concept();
		SubCategory subcategory = new SubCategory("name");
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);

		subcategory.setConcepts(concepts);
		
		assertTrue(subcategory.getConcepts().contains(concept));
	}
	
}
