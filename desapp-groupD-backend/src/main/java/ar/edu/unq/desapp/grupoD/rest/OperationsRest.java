package ar.edu.unq.desapp.grupoD.rest;

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
import ar.edu.unq.desapp.grupoD.services.OperationService;

/**
 * @author JulianV
 */
@Service
@Path("/operation")
public class OperationsRest {

	private OperationService operationService;

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
			@FormParam("category") Category category, @FormParam("subCategory") SubCategory subCategory,
			@FormParam("concept") Concept concept, @FormParam("paymentType") PaymentType paymentType) throws InvalidAmountException{
		
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
			@FormParam("category") Category category, @FormParam("subCategory") SubCategory subCategory,
			@FormParam("concept") Concept concept, @FormParam("paymentType") PaymentType paymentType) throws InvalidAmountException{
		
		Operation operation = operationService.getOperationByID(id);
		operation.setDate(date);
		operation.setAmount(amount);
		operation.setIncome(isIncome);
		subCategory.setConcept(concept);
		category.setSubcategory(subCategory);
		operation.setCategory(category);
		operation.setPaymentType(paymentType);
		
		operationService.saveOperation(operation);	
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	

}
