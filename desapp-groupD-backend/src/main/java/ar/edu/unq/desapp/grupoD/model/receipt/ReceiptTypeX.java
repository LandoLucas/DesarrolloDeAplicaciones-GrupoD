package ar.edu.unq.desapp.grupoD.model.receipt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

@Entity
public class ReceiptTypeX extends Receipt {

	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * A Type X receipt
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
	public ReceiptTypeX(DateTime date, int receiptNumber,
			String clientOrLegalEntityName, String firmName, String cUIT,
			String address, int telephoneNumber, double finalImport) throws InvalidReceiptNumberException {
	
		super(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, finalImport);
	}
	
	
}
