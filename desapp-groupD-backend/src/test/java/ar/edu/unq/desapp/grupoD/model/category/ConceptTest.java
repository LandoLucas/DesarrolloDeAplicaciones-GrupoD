package ar.edu.unq.desapp.grupoD.model.category;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConceptTest {

	
	@Test
	public void getAndSetConcept(){
		String conceptName = "Sells 01/20";
		
		Concept.setConceptName(conceptName);
	
		assertEquals( conceptName , Concept.getConceptName());
	}
	
}
