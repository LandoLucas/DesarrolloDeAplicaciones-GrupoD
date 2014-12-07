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

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.Receipt;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;

@Service
@Path("/ReceiptA")
public class ReceiptTypeARest {

	private ReceiptTypeAService receiptTypeAService;

	public void setReceiptTypeAService(ReceiptTypeAService receiptTypeAService) {
		this.receiptTypeAService = receiptTypeAService;
	}

	@GET
    @Path("/{receiptNumber}")
    @Produces("application/json")
    public Receipt getOperationByReceiptNumber(@PathParam("receiptNumber") int id){
		return receiptTypeAService.getReceiptByReceiptNumber(id);
    }
	
	@POST
	@Path("/new")
	public Response addReceiptA(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address, @FormParam("telephoneNumber") int telephoneNumber,
			@FormParam("iVA") double iVA, @FormParam("iIBB") double iIBB, @FormParam("gravado") double gravado,
			@FormParam("noGravado") double noGravado) throws InvalidReceiptNumberException{
		
		ReceiptTypeA receipt = new ReceiptTypeA(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,address, telephoneNumber,
			iVA, iIBB, gravado, noGravado);
		receiptTypeAService.save(receipt);
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/edit")
	public Response editReceiptA(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address, @FormParam("telephoneNumber") int telephoneNumber,
			@FormParam("iVA") double iVA, @FormParam("iIBB") double iIBB, @FormParam("gravado") double gravado,
			@FormParam("noGravado") double noGravado) throws InvalidReceiptNumberException{
		
		ReceiptTypeA receiptToEdit = new ReceiptTypeA(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, iVA, iIBB, gravado, noGravado);
		receiptTypeAService.save(receiptToEdit);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
}
