package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	@Path("/all")
	@Produces("application/json")
	public Response getAllSubCategories() {
		List<SubCategory>obs = subcategoryService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}

	@POST
	@Path("/delete")
	@Consumes("application/x-www-form-urlencoded")
	public Response deleteSubcategory(@FormParam("name") String name,
			@FormParam("idCategory") Integer idCat) throws Exception {
		subcategoryService.removeSubcategoryByName(name,idCat);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateSubcategory(@FormParam("name") String name, 
			@FormParam("idCategory") Integer idCategory) {
		SubCategory subcategory = new SubCategory(name);
		subcategoryService.save(subcategory, idCategory);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/update")
	@Consumes("application/x-www-form-urlencoded")
	public Response updateSubcategory(@FormParam("name") String name, 
			@FormParam("idSubcategory") Integer idSub) {
		subcategoryService.update(name, idSub);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}