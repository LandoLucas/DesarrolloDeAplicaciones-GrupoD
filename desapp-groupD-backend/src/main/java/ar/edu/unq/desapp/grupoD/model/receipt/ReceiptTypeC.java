package ar.edu.unq.desapp.grupoD.model.receipt;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * @author Lucas
 */
public class ReceiptTypeC extends Receipt {

	/**
	 * A Type C receipt
	 * 
	 * @param date
	 * @param receiptNumber
	 * @param clientOrLegalEntityName
	 * @param firmName
	 * @param cUIT
	 * @param address
	 * @param telephoneNumber
	 * @param finalImport
	 * @throws InvalidReceiptNumberException if the receipt number is equal or less than 0
	 */
	protected ReceiptTypeC(DateTime date, int receiptNumber,
			String clientOrLegalEntityName, String firmName, String cUIT,
			String address, int telephoneNumber, double finalImport) throws InvalidReceiptNumberException {
	
		super(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
	}

}
