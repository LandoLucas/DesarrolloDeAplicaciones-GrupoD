package ar.edu.unq.desapp.grupoD.model.category;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Lucas
 */
public class ConceptTest {

	@Test
	public void getAndSetConcept(){
		String conceptName = "Sells 01/20";
		Concept concept = new Concept(conceptName);
		assertEquals( conceptName , concept.getConceptName());
	}
	
}
