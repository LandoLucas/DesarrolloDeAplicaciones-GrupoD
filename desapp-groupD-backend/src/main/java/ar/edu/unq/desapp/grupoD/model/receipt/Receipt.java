package ar.edu.unq.desapp.grupoD.model.receipt;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * Represents an operation receipt. Consisting on: - Date of the operation -
 * Receipt number - Client or legal entity name - Firm name - CUIT number -
 * Address - Telephone number - Receipt Type (with its taxes)
 * 
 * @author Lucas
 */
public abstract class Receipt {

	protected DateTime date;
	protected int receiptNumber;
	protected String clientOrLegalEntityName;
	protected String firmName;
	protected String CUIT;
	protected String address;
	protected int telephoneNumber;
	protected double finalImport;
	protected int operationID;

	protected Receipt(DateTime date, int receiptNumber,
			String clientOrLegalEntityName, String firmName, String cUIT,
			String address, int telephoneNumber, double finalImport)
			throws InvalidReceiptNumberException {
		super();
		this.setDate(date);
		this.setReceiptNumber(receiptNumber);
		this.setClientOrLegalEntityName(clientOrLegalEntityName);
		this.setFirmName(firmName);
		this.setCUIT(cUIT);
		this.setAddress(address);
		this.setTelephoneNumber(telephoneNumber);
		this.setFinalImport(finalImport);
	}

	protected int getOperationID() {
		return operationID;
	}

	/**
	 * Sets an optional operationID to correspond this receipt with an operation on the system
	 * @param operationID
	 */
	protected void setOperationID(int operationID) {
		this.operationID = operationID;
	}

	protected DateTime getDate() {
		return date;
	}

	protected void setDate(DateTime date) {
		this.date = date;
	}

	protected int getReceiptNumber() {
		return receiptNumber;
	}

	protected void setReceiptNumber(int receiptNumber)
			throws InvalidReceiptNumberException {
		if (receiptNumber <= 0)
			throw new InvalidReceiptNumberException();
		this.receiptNumber = receiptNumber;
	}

	protected String getClientOrLegalEntityName() {
		return clientOrLegalEntityName;
	}

	protected void setClientOrLegalEntityName(String clientOrLegalEntityName) {
		this.clientOrLegalEntityName = clientOrLegalEntityName;
	}

	protected String getFirmName() {
		return firmName;
	}

	protected void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	protected String getCUIT() {
		return CUIT;
	}

	protected void setCUIT(String cUIT) {
		CUIT = cUIT;
	}

	protected String getAddress() {
		return address;
	}

	protected void setAddress(String address) {
		this.address = address;
	}

	protected int getTelephoneNumber() {
		return telephoneNumber;
	}

	protected void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	protected double getFinalImport() {
		return finalImport;
	}

	protected void setFinalImport(double finalImport) {
		this.finalImport = finalImport;
	}

}
