package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class SubCategoryServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private SubCategoryService subcategoryService;
	@Autowired
	private CategoryService categoryService;
	private String name = "test_subcategory_2";
	private SubCategory subcategory = new SubCategory(name);
	
	@Before
	public void setUp(){
		Category category = new Category("test_category");
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		subcategories.add(subcategory);
		category.setSubcategory(subcategories);
		categoryService.save(category);
	}
	
	@Test
	public void save(){
		SubCategory subcategory = new SubCategory("test_subcategory");
		subcategoryService.save(subcategory);
	
		List<SubCategory> subcategories = subcategoryService.findAll();
		assertTrue( subcategories.contains(subcategory));
	}
	
	@Test
	public void removeSubcategoryByName(){
		
		int id = categoryService.findAll().get(0).getId();
		subcategoryService.removeSubcategoryByName(name, id);
	
		List<SubCategory> subcategoriesOnDB = subcategoryService.findAll();
		assertTrue(! subcategoriesOnDB.contains(subcategory));
	}
	
	@Test
	public void saveOnCategory(){
		int idCategory = categoryService.findByName("test_category").getId();
		SubCategory subCategory2 = new SubCategory("test_subcategory");
		
		subcategoryService.save(subCategory2, idCategory);
	}
	
	@Test
	public void update(){
		int idSub = subcategoryService.findAll().get(0).getId();
		String newName = "new_name";
		
		subcategoryService.update(newName, idSub);
		
		assertEquals(subcategoryService.findByName("new_name").getSubcategoryName()  , newName);
	}
}
