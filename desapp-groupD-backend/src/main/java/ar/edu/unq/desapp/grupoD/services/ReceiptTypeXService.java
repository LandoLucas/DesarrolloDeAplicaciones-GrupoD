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
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeX;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptXDao;

/*
 * @author JulianV
 */
@Service
@Path("/ReceiptX")
public class ReceiptTypeXService {
	private ReceiptXDao receiptXDao = new ReceiptXDao();
	
	@GET
    @Path("/{receiptNumber}")
    @Produces("application/json")
    public Receipt getOperationByReceiptNumber(@PathParam("receiptNumber") int id){
		return receiptXDao.getReceiptByReceiptNumber(id);
    }

	@POST
	@Path("/new")
	public Response addReceiptB(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address,
			@FormParam("telephoneNumber") int telephoneNumber, @FormParam("finalImport") double finalImport) throws InvalidReceiptNumberException{
		
		ReceiptTypeX receipt = new ReceiptTypeX(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
		receiptXDao.save(receipt);
		return Response.ok().build();
	}
	
	@POST
	@Path("/edit")
	public Response editReceiptB(@FormParam("date") DateTime date, @FormParam("receiptNumber") int receiptNumber,
			@FormParam("clientName") String clientOrLegalEntityName, @FormParam("firmName") String firmName,
			@FormParam("cUIT") String cUIT, @FormParam("address") String address,
			@FormParam("telephoneNumber") int telephoneNumber, @FormParam("finalImport") double finalImport) throws InvalidReceiptNumberException{
		
		ReceiptTypeX receipt = new ReceiptTypeX(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
		receiptXDao.save(receipt);
		return Response.ok().build();
	}
}

