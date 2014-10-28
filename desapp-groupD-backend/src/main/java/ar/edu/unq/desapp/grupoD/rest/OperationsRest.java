package ar.edu.unq.desapp.grupoD.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.services.CategoryService;
import ar.edu.unq.desapp.grupoD.services.OperationService;
import ar.edu.unq.desapp.grupoD.services.SubCategoryService;

/**
 * @author JulianV
 */
@Service
@Path("/operation")
public class OperationsRest {

	private OperationService operationService;
	private CategoryService categoryService;
	private SubCategoryService subCategoryService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Operation getOperationByID(@PathParam("id") int id) {
		return operationService.getOperationByID(id);
	}
	
	@POST
	@Path("/new")
	public Response addOperation(@FormParam("date") DateTime date, @FormParam("amount") double amount, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName, @FormParam("paymentType") PaymentType paymentType) throws InvalidAmountException{
		
		Category category = categoryService.findByName(categoryName);
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		Concept concept = new Concept(conceptName);
				
		Operation operation = new Operation(date, amount, isIncome, shift, category, subCategory, concept, paymentType);
		operationService.saveOperation(operation);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/remove/{id}")
	public Response removeOperation(@PathParam("id") int id){
		operationService.removeOperationByID(id);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/edit/{id}")
	public Response editOperation(@PathParam("id") int id, @FormParam("date") DateTime date, @FormParam("amount") double amount, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName, @FormParam("paymentType") PaymentType paymentType) throws InvalidAmountException{
		
		Category category = categoryService.findByName(categoryName);
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		Concept concept = new Concept(conceptName);
		
		Operation operation = operationService.getOperationByID(id);
		operation.setDate(date);
		operation.setAmount(amount);
		operation.setIncome(isIncome);
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);
		subCategory.setConcepts(concepts);
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		subcategories.add(subCategory);
		category.setSubcategory(subcategories);
		operation.setCategory(category);
		operation.setPaymentType(paymentType);
		
		operationService.saveOperation(operation);	
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	

}
