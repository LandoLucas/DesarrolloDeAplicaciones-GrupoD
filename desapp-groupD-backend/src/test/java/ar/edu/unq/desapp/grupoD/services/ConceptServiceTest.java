package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ConceptServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ConceptService conceptService;
	
	@Autowired
	private SubCategoryService subcategoryService;
	
	private Concept concept = new Concept("test_concept");
	private SubCategory subcategory = new SubCategory("test_subcategory");
	
	@Before
	public void setUp(){
		List<Concept> concepts = new ArrayList<Concept>();
		subcategory.setConcepts(concepts);
		subcategoryService.save(subcategory);
		int id = subcategoryService.findAll().get(0).getId();
		conceptService.save(concept , id);
	}
	
	@Test
	public void findAll(){
		List<Concept> concepts = conceptService.findAll();
		
		assertTrue( ! concepts.isEmpty() );
		assertEquals( concepts.size() , 1  );
		assertTrue( concepts.contains(concept));
	}
	
	
	@Test
	public void removeConceptByName(){
		int id = subcategoryService.findAll().get(0).getId();
		conceptService.removeConceptByName("test_concept", id);
		
		List<Concept> concepts = conceptService.findAll();
		assertTrue( concepts.isEmpty() );
		
	}
	
	@Test
	public void update(){
		String newName = "test_updating_name";
		List<Concept> concepts = conceptService.findAll();
		int id = concepts.get(0).getId();
		
		conceptService.update(newName, id);
		
		assertTrue( concepts.size() == 1 );
		assertEquals( concepts.get(0).getConceptName() , newName);
	}
}
