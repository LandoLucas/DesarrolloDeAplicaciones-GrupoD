package ar.edu.unq.desapp.grupoD.services;

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
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeC;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptCDao;

/*
 * @author JulianV
 */
@Service
@Path("/ReceiptC")
public class ReceiptTypeCService {
	private ReceiptCDao receiptCDao = new ReceiptCDao();
	
	@GET
    @Path("/{receiptNumber}")
    @Produces("application/json")
    public Receipt getOperationByReceiptNumber(@PathParam("receiptNumber") int id){
		return receiptCDao.getReceiptByReceiptNumber(id);
    }

	@POST
	@Path("/new")
	public Response addReceiptB(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address,
			@FormParam("telephoneNumber") int telephoneNumber, @FormParam("finalImport") double finalImport) throws InvalidReceiptNumberException{
		
		ReceiptTypeC receipt = new ReceiptTypeC(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
		receiptCDao.saveReceiptC(receipt);
		return Response.ok().build();
	}
	
	@POST
	@Path("/edit")
	public Response editReceiptB(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address,
			@FormParam("telephoneNumber") int telephoneNumber, @FormParam("finalImport") double finalImport) throws InvalidReceiptNumberException{
		
		ReceiptTypeC receipt = new ReceiptTypeC(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
		receiptCDao.updateReceiptC(receipt);
		return Response.ok().build();
	}
}

