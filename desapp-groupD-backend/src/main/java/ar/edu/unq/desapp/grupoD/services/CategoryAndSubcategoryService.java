package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

/**
 * REST service for Categories and Subcategories
 * 
 * @author Lucas
 */
@Service
@Path("/category")
public class CategoryAndSubcategoryService {

	private CategoryDao categoryDao;
	private SubcategoryDao subcategoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}

	@GET
	@Path("/{name}")
	@Produces("application/json")
	public List<Category> getAllCategories(@PathParam("name") String name) {
		return categoryDao.findAll();
	}

	@GET
	@Path("/deleteCategory/{name}")
	@Consumes("application/json")
	public Response deleteCategory(@PathParam("name") String name) {
		categoryDao.removeCategory(name);
		return Response.ok().build();
	}

	// @GET
	// @Path("/deleteSubcategory/{name}")
	// @Consumes("application/json")
	// public Response deleteSubcategory(@PathParam("name") String name){
	// subcategoryDao.removeSubCategory(name);
	// return Response.ok().build();
	// }

	@GET
	@Path("/add/{name}")
	@Consumes("application/json")
	public Response saveOrUpdateCategory(@PathParam("name") String name) {
		Category category = new Category(name);
		categoryDao.save(category);
		return Response.ok().build();
	}

	@GET
	@Path("/subcategory/{name}")
	@Consumes("application/json")
	public Response saveOrUpdateSubCategory(@PathParam("name") String name) {
		SubCategory subcategory = new SubCategory(name);
		subcategoryDao.save(subcategory);
		return Response.ok().build();
	}

	@GET
	@Path("/subcategory")
	@Produces("application/json")
	public List<SubCategory> getAllSubCategories() {
		List<SubCategory> subcategoriesList = subcategoryDao.findAll();
		return subcategoriesList;
	}
}