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
public class Receipt {

	private DateTime date;
	private int receiptNumber;
	private String clientOrLegalEntityName;
	private String firmName;
	private String CUIT;
	private String address;
	private int telephoneNumber;
	private ReceiptType receiptType;

	
	public Receipt(DateTime date, int receiptNumber,
			String clientOrLegalEntityName, String firmName, String cUIT,
			String address, int telephoneNumber, ReceiptType receiptType) throws InvalidReceiptNumberException {
		super();
		this.setDate(date);
		this.setReceiptNumber(receiptNumber);
		this.setClientOrLegalEntityName(clientOrLegalEntityName);
		this.setFirmName(firmName);
		this.setCUIT(cUIT);
		this.setAddress(address);
		this.setTelephoneNumber(telephoneNumber);
		this.setReceiptType(receiptType);
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(int receiptNumber) throws InvalidReceiptNumberException {
		if(receiptNumber <=0) throw new InvalidReceiptNumberException();
		this.receiptNumber = receiptNumber;
	}

	public String getClientOrLegalEntityName() {
		return clientOrLegalEntityName;
	}

	public void setClientOrLegalEntityName(String clientOrLegalEntityName) {
		this.clientOrLegalEntityName = clientOrLegalEntityName;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String cUIT) {
		CUIT = cUIT;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public ReceiptType getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(ReceiptType receiptType) {
		this.receiptType = receiptType;
	}

}
