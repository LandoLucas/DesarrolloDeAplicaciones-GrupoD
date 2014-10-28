package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
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
	@Path("/all")
	@Produces("application/json")
	public Response getAllCategories() throws InvalidAmountException, InvalidReceiptNumberException {
		List<Category> obs =  categoryService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}

	@POST
	@Path("/delete")
	@Consumes("application/x-www-form-urlencoded")
	public Response deleteCategory(@FormParam("name") String name) {
		categoryService.removeCategory(name);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("/add")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateCategory(@FormParam("name") String name) {
		Category category = new Category(name);
		categoryService.save(category);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}