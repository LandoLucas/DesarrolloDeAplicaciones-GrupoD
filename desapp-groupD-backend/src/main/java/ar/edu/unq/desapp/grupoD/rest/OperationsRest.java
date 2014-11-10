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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.account.DebitAccount;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.BankTransfer;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
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
	public Response addOperation(@FormParam("date") String date, @FormParam("amount") double amount, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName, @FormParam("paymentCode") Integer paymentCode) throws InvalidAmountException{
		
		//Parse date
		DateTimeFormatter dateDecoder = DateTimeFormat.forPattern("yyyy-MM-dd");		
		DateTime parsedDate = dateDecoder.parseDateTime(date);
		
		//Parse payment Type
		PaymentType paymentType;
		switch (paymentCode) {
		case 0: paymentType = new PettyCash(new PettyCashAccount()); break;
		case 1: paymentType = new BankTransfer(new BankAccount()); break;
		case 2: paymentType = new CreditCard(new DebitAccount()); break;

		default: throw new RuntimeException();
		}
		
		operationService.saveOperation(parsedDate , amount , isIncome , shift , categoryName , subCategoryName , conceptName , paymentType);
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
		
		operationService.saveOperation(id , date , amount , isIncome , shift, categoryName , subCategoryName , conceptName , paymentType );
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	

}
