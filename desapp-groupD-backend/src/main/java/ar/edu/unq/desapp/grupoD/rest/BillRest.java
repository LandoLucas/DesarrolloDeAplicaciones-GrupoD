package ar.edu.unq.desapp.grupoD.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.Receipt;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeC;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeX;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeBService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeCService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeXService;

/**
 * Unified rest service for all types of bills
 * 
 * @author eiroa
 *
 */
@Service
@Path("/bill")
public class BillRest {
	private ReceiptTypeAService receiptTypeAService;
	private ReceiptTypeBService receiptTypeBService;
	private ReceiptTypeCService receiptTypeCService;
	private ReceiptTypeXService receiptTypeXService;

	public void setReceiptTypeAService(ReceiptTypeAService receiptTypeAService) {
		this.receiptTypeAService = receiptTypeAService;
	}

	public void setReceiptTypeBService(ReceiptTypeBService receiptTypeBService) {
		this.receiptTypeBService = receiptTypeBService;
	}

	public void setReceiptTypeCService(ReceiptTypeCService receiptTypeCService) {
		this.receiptTypeCService = receiptTypeCService;
	}

	public void setReceiptTypeXService(ReceiptTypeXService receiptTypeXService) {
		this.receiptTypeXService = receiptTypeXService;
	}

	@POST
	@Path("/new")
	public Response newBill(@FormParam("letter") String letter,
			@FormParam("date") String dateReceived, 
			@FormParam("serie") String serie,
			@FormParam("billNumber") int billNumber,
			@FormParam("client_seller") String clientSeller,
			@FormParam("address") String address,
			@FormParam("cuit") String cUIT,
			@FormParam("telephoneNumber") int telephoneNumber,
			@FormParam("iva") double iVA, 
			@FormParam("iibb") double iIBB,
			@FormParam("gravado") double gravado,
			@FormParam("total") double totalFinal,
			@FormParam("totalNoTaxes") double totalSinImpuestos,
			@FormParam("noGravado") double noGravado)
			throws InvalidReceiptNumberException {
		//Parse date
				DateTimeFormatter dateDecoder = DateTimeFormat.forPattern("yyyy-MM-dd");		
				DateTime date = dateDecoder.parseDateTime(dateReceived);
		Receipt bill;
		switch (letter) {
		case "a":
			bill = new ReceiptTypeA(date, billNumber, clientSeller, serie,
					cUIT, address, telephoneNumber, iVA, iIBB, gravado,
					noGravado);
			receiptTypeAService.save((ReceiptTypeA) bill);
			break;

		case "b":
			bill = new ReceiptTypeB(date, billNumber, clientSeller, serie,
					cUIT, address, telephoneNumber, iVA);
			receiptTypeBService.save((ReceiptTypeB) bill);

			break;

		case "c":
			bill = new ReceiptTypeC(date, billNumber, clientSeller, serie,
					cUIT, address, telephoneNumber, iVA);
			receiptTypeCService.save((ReceiptTypeC) bill);

			break;

		case "x":
			bill = new ReceiptTypeX(date, billNumber, clientSeller, serie,
					cUIT, address, telephoneNumber, iVA);
			receiptTypeXService.save((ReceiptTypeX) bill);
			break;

		default:
			break;
		}
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}
