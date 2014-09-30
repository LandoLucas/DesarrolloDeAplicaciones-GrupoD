package ar.edu.unq.desapp.grupoD.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.persistence.OperationDao;

/*
 * @author JulianV
 */
@Service
@Path("/operation")
public class OperationService {
	
	private OperationDao operationDao = new OperationDao();
	
	@POST
	@Path("/new")
	public Response addOperation(@FormParam("date") DateTime date, @FormParam("amount") double amount, 
			@FormParam("isIncome") boolean isIncome, @FormParam("shift") String shift, 
			@FormParam("category") Category category, @FormParam("subCategory") SubCategory subCategory,
			@FormParam("concept") Concept concept, @FormParam("paymentType") PaymentType paymentType) throws InvalidAmountException{
		
		Operation operation = new Operation(date, amount, isIncome, shift, category, subCategory, concept, paymentType);
		operationDao.saveOperation(operation);
		return Response.ok().build();
	}
	

}
