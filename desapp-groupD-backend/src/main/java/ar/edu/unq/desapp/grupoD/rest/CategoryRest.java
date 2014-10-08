package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.services.CategoryService;

/**
 * REST service for Categories and Subcategories
 * 
 * @author Lucas
 */
@Service
@Path("/category")
public class CategoryRest {

	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GET
	@Path("/{name}")
	@Produces("application/json")
	public List<Category> getAllCategories(@PathParam("name") String name) {
		return categoryService.findAll();
	}

	@GET
	@Path("/deleteCategory/{name}")
	@Consumes("application/json")
	public Response deleteCategory(@PathParam("name") String name) {
		categoryService.removeCategory(name);
		return Response.ok().build();
	}

	@GET
	@Path("/add/{name}")
	@Consumes("application/json")
	public Response saveOrUpdateCategory(@PathParam("name") String name) {
		Category category = new Category(name);
		categoryService.save(category);
		return Response.ok().build();
	}

}