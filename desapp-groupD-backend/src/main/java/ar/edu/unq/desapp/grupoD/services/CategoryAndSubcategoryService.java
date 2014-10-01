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

/**
 * REST service for Categories and Subcategories
 * @author Lucas
 */
@Service
@Path("/category")
public class CategoryAndSubcategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	
	@GET
    @Path("/{name}")
    @Produces("application/json")
    public List<Category> getAllCategories(@PathParam("name") String name){
		return categoryDao.getAllCategories();
    }
	
	
	@GET
    @Path("/add/{name}")
    @Consumes("application/json")
    public Response saveOrUpdateCategory(@PathParam("name") String name) {
		Category category = new Category(name);
		categoryDao.saveOrUpdateCategory(category);
        return Response.ok().build();
    }

	
	@GET
    @Path("/subcategory/{name}")
    @Consumes("application/json")
    public Response saveOrUpdateSubCategory(@PathParam("name") String name) {
		SubCategory subcategory = new SubCategory(name);
		categoryDao.saveOrUpdateSubCategory(subcategory);
        return Response.ok().build();
    }

	@GET
    @Path("/subcategory")
    @Produces("application/json")
    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subcategoriesList = categoryDao.getAllSubCategories();
        return subcategoriesList;
    }
}