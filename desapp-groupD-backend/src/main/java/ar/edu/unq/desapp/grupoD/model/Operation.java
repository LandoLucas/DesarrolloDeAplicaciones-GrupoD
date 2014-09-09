package ar.edu.unq.desapp.grupoD.model;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

public class Operation {

	private DateTime date;
	private int operationID;
	private double amount;
	private boolean isIncome;
	private String shift;
	private Category category;
	private PaymentType paymentType;

	private static int next_operation_id = 1;
	/**
	 * Returns an instance of a money operation and it saves the transaction details.
	 * It is intended to be used when the transaction has already been done.  
	 * If you need to set an operation ID, use {@link #setOperationID(int)} to set it
	 * @param date
	 *            the date of the operation
	 * @param amount
	 *            amount of the operation in users currency
	 * @param isIncome
	 *            true if it's an income, false if it's an outcome of money
	 * @param shift
	 *            shift when the operation was made
	 * @param category
	 *            used to identify if it's income or outcome
	 * @param paymentType
	 *            how the operation was payed
	 * @throws InvalidAmountException if the amount is equal or below 0
	 */
	public Operation(DateTime date, double amount, boolean isIncome,
			String shift, Category category, PaymentType paymentType) throws InvalidAmountException {
		this.setDate(date);
		this.setAmount(amount);
		this.setIncome(isIncome);
		this.setShift(shift);
		this.setCategory(category);
		this.setPaymentType(paymentType);
		
		setOperationID();
		this.bill();
	}
	
	/**
	 * Intended to be used as an adjustment operation where you need to correct the balance of one of the accounts.
	 * It is like a normal operation but it lacks some information such as the shift and it needs to know which account needs to be adjusted.
	 */
	public Operation(DateTime date, double amount, boolean isIncome,
			 Category category, Account account) throws InvalidAmountException {
		this.setDate(date);
		this.setAmount(amount);
		this.setIncome(isIncome);
		this.setCategory(category);
		
		setOperationID();
		account.adjustAccount(amount , isIncome);
	}
	
	
	
	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getOperationID() {
		return operationID;
	}

	/**
	 * Sets automatically the operation ID
	 */
	private synchronized void setOperationID(){
		this.operationID = next_operation_id;
		next_operation_id = next_operation_id + 1;
		//TODO once we have the database we might want to consider to retrieve the last operation ID from it since any restart will make the counter to reset.
	}
	
	/**
	 * To be used ONLY for unit test
	 */
	protected static void resetCounter(){
		next_operation_id = 1;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) throws InvalidAmountException {
		if( amount <= 0) throw new InvalidAmountException();
		this.amount = amount;
	}

	public boolean isIncome() {
		return isIncome;
	}

	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}


	/**
	 * Delegates the billing to the correspondent payment type.
	 */
	private void bill() {
		this.paymentType.bill( this.amount , this);
	}

}
