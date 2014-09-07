package ar.edu.unq.desapp.grupoD.model.receipt;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * A Builder to construct Receipt instances. It eases the reading of the tests.
 * @author Lucas
 *
 */
public class ReceiptBuilder {

	private DateTime date = new DateTime();
	private int receiptNumber = 1;
	private String clientOrLegalEntityName = "The Coca Cola Company";
	private String firmName = "Coka Cola";
	private String cUIT = "20-33123123-2";
	private String address = "Street n°123 State";
	private int telephoneNumber = 41321234;
	private double finalImport = 100;
	
	public Receipt build() throws InvalidReceiptNumberException{
		return new ReceiptTypeB(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, finalImport);
	}
	
	public ReceiptBuilder withReceiptNumber(int number){
		this.receiptNumber = number;
		return this;
	}
	
}
