package ar.edu.unq.desapp.grupoD.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
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
	@Path("/all")
	@Produces("application/json")
	public Response getAllOperations() throws InvalidAmountException, InvalidReceiptNumberException {
		List<Operation> obs =  operationService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}
	
	
	@POST
	@Path("/new")
	@Consumes("application/x-www-form-urlencoded")
	public Response addOperation(@FormParam("date") String date, @FormParam("cash") Integer cash,
			@FormParam("credit") Integer credit, @FormParam("debit") Integer debit,  
			@FormParam("isOutcome") boolean isOutcome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName) throws InvalidAmountException{
		
		//Parse date
		DateTimeFormatter dateDecoder = DateTimeFormat.forPattern("yyyy-MM-dd");		
		DateTime parsedDate = dateDecoder.parseDateTime(date);
		
		List<PaymentType> paymentTypes = parsePayments(cash,credit,debit);
				
		operationService.saveOperation(parsedDate , paymentTypes , isOutcome , shift , categoryName , subCategoryName , conceptName);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	private List<PaymentType> parsePayments(Integer cash, Integer credit, Integer debit) throws InvalidAmountException {
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		
		//Parse payment Type
		if(cash>0) paymentTypes.add( new PettyCash(cash));
	    if(credit>0) paymentTypes.add( new CreditCard(credit));
	    if(debit>0) paymentTypes.add( new DebitCard(debit));
			
		if( cash <= 0 && credit <=0 && debit <= 0)throw new RuntimeException("Payment type not set");
		
		return paymentTypes;
	}
	
	@POST
	@Path("/edit/{id}")
	public Response editOperation(@FormParam("date") DateTime date,  @FormParam("cash") Integer cash,
			@FormParam("credit") Integer credit, @FormParam("debit") Integer debit, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName) throws InvalidAmountException{
		
		List<PaymentType> paymentTypes = parsePayments(cash,credit,debit);
		
		operationService.saveOperation(date , paymentTypes , isIncome , shift, categoryName , subCategoryName , conceptName);
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}
