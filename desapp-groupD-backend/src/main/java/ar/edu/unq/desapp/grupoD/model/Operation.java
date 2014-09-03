package ar.edu.unq.desapp.grupoD.model;

import org.joda.time.DateTime;

/**
 * Represents a money flow operation (income or outcome).
 * Each operation happens on a specific date, with an specific amount of money on a shift.
 * The operation ID is optional.
 * @author Lucas
 * 
 */
public class Operation {

	private DateTime date;
	private int operationID;
	private double amount;
	private boolean isIncome;
	private String shift;
	private Category category;
	private PaymentType paymentType;
	
	
	public Operation(DateTime date, int operationID, double amount, boolean isIncome, String shift, Category category, PaymentType paymentType) {
		super();
		this.date = date;
		this.operationID = operationID;
		this.amount = amount;
		this.isIncome = isIncome;
		this.shift = shift;
		this.category = category;
		this.paymentType = paymentType;
	}
	
	
}
