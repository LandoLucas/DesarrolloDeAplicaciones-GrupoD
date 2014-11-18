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

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.services.PaymentTypeService;

/**
 * 
 * @author eiroa
 *
 */
@Service
@Path("/paymentType")
public class PaymentTypeRest {

	private PaymentTypeService paymentTypeService;

	public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}

	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllPaymentTypes() throws InvalidAmountException, InvalidReceiptNumberException {
		List<PaymentType> obs =  paymentTypeService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}
	


//	@POST
//	@Path("/delete")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response deletePaymentType(@FormParam("name") String name) {
//		paymentTypeService.removePaymentType(name);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}

//	@POST
//	@Path("/save")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response saveOrUpdatePaymentType(@FormParam("name") String name) {
//		PaymentType paymentType = new PaymentType(name);
//		paymentTypeService.save(paymentType);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}
	
//	@POST
//	@Path("/update")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response updatePaymentType(@FormParam("name") String name,@FormParam("idPaymentType") Integer idCat) {
//		paymentTypeService.update(name,idCat);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}

}
