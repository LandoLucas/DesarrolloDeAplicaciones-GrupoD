package ar.edu.unq.desapp.grupoD.model.receipt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * Represents an operation receipt. Consisting on: - Date of the operation -
 * Receipt number - Client or legal entity name - Firm name - CUIT number -
 * Address - Telephone number - Receipt Type (with its taxes)
 * 
 * @author Lucas
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Receipt {

	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	@Column
	private int receiptNumber;
	@Column
	private String clientOrLegalEntityName;
	@Column
	private String firmName;
	@Column
	private String CUIT;
	@Column
	private String address;
	@Column
	private int telephoneNumber;
	@Column
	private double finalImport;

	@Id
	private int operationID;

	public Receipt(DateTime date, int receiptNumber,
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

	public int getOperationID() {
		return operationID;
	}

	/**
	 * Sets an optional operationID to correspond this receipt with an operation on the system
	 * @param operationID
	 */
	public void setOperationID(int operationID) {
		this.operationID = operationID;
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

	public void setReceiptNumber(int receiptNumber)
			throws InvalidReceiptNumberException {
		if (receiptNumber <= 0)
			throw new InvalidReceiptNumberException();
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

	public double getFinalImport() {
		return finalImport;
	}

	public void setFinalImport(double finalImport) {
		this.finalImport = finalImport;
	}

}
