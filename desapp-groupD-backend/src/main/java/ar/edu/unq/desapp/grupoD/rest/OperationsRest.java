package ar.edu.unq.desapp.grupoD.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
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

//	@GET
//	@Path("/{id}")
//	@Produces("application/json")
//	public Operation getOperationByID(@PathParam("id") int id) {
//		return operationService.getOperationByID(id);
//	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllOperations() throws InvalidAmountException, InvalidReceiptNumberException {
		List<Operation> obs =  operationService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}
	
//	@GET
//	@Path("/incomes")
//	@Produces("application/json")
//	public Response getAllIncomes() throws InvalidAmountException, InvalidReceiptNumberException {
//		List<Operation> obs =  operationService.findIncomes();
//		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
//	}
//	
//	@GET
//	@Path("/outcomes")
//	@Produces("application/json")
//	public Response getAllOutcomes() throws InvalidAmountException, InvalidReceiptNumberException {
//		List<Operation> obs =  operationService.findOutcomes();
//		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
//	}
	
	@POST
	@Path("/new")
	public Response addOperation(@FormParam("date") String date, @FormParam("paymentTypes") Map<Integer,Double> payments, 
			@FormParam("isOutcome") boolean isOutcome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName) throws InvalidAmountException{
		
		//Parse date
		DateTimeFormatter dateDecoder = DateTimeFormat.forPattern("yyyy-MM-dd");		
		DateTime parsedDate = dateDecoder.parseDateTime(date);
		
		List<PaymentType> paymentTypes = parsePayments(payments);
				
		operationService.saveOperation(parsedDate , paymentTypes , isOutcome , shift , categoryName , subCategoryName , conceptName);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	private List<PaymentType> parsePayments(Map<Integer, Double> payments)
			throws InvalidAmountException {
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		
		//Parse payment Type
		for( Integer paymentCode : payments.keySet()){
			switch (paymentCode) {
			case 0: paymentTypes.add( new PettyCash(payments.get(paymentCode)) ); break;
			case 1: paymentTypes.add( new DebitCard(payments.get(paymentCode)) ); break;
			case 2: paymentTypes.add( new CreditCard(payments.get(paymentCode)) ); break;

			default: throw new RuntimeException("Payment type not set");
			}

		}
		return paymentTypes;
	}
	
//	@POST
//	@Path("/remove")
//	public Response removeOperation(@FormParam("id") int id){
//		operationService.removeOperationByID(id);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}
	
	@POST
	@Path("/edit/{id}")
	public Response editOperation(@FormParam("date") DateTime date,  @FormParam("paymentTypes") Map<Integer,Double> payments, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") String categoryName, @FormParam("subCategory") String subCategoryName,
			@FormParam("concept") String conceptName) throws InvalidAmountException{
		
		List<PaymentType> paymentTypes = parsePayments(payments);
		
		operationService.saveOperation(date , paymentTypes , isIncome , shift, categoryName , subCategoryName , conceptName);
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	

}
