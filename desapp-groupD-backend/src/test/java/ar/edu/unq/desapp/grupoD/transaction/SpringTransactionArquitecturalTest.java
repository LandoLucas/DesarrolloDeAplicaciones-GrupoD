package ar.edu.unq.desapp.grupoD.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.services.SubCategoryService;

/**
 * This test aims to verify that the Spring transactional aspect works as expected
 * @author Lucas
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class SpringTransactionArquitecturalTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private SubCategoryService subcategoryService;

	public void setSubcategoryService(SubCategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@Test @Ignore
	public void testTransactionalAspect(){
		assertEquals( 0 , subcategoryService.findAll().size() );
		SubCategory subcategory = new SubCategory("test");
		subcategoryService.save(subcategory);
		
		try{
			subcategoryService.update("new", subcategory.getId());
			fail("Exception expected here");
		}
		catch(Exception ex){
			assertEquals( "test", subcategoryService.findAll().get(0).getSubcategoryName());
		}
	}

}
