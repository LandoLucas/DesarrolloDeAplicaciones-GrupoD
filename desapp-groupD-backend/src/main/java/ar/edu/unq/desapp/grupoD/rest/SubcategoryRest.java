package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.services.SubCategoryService;

/**
 * REST service for Subcategories
 * @author Lucas
 */
@Service
@Path("/subcategory")
public class SubcategoryRest {

	private SubCategoryService subcategoryService;
	
	public void setSubcategoryService(SubCategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@GET
	@Path("/{name}")
	@Produces("application/json")
	public List<SubCategory> getAllSubCategories(@PathParam("name") String name) {
		return subcategoryService.findAll();
	}

	@GET
	@Path("/deleteSubcategory/{name}")
	@Consumes("application/json")
	public Response deleteSubcategory(@PathParam("name") String name) {
		subcategoryService.removeSubcategoryByName(name);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/add/{name}")
	@Consumes("application/json")
	public Response saveOrUpdateSubcategory(@PathParam("name") String name) {
		SubCategory subcategory = new SubCategory(name);
		subcategoryService.save(subcategory);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}