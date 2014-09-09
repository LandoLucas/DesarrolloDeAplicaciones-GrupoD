package ar.edu.unq.desapp.grupoD.model.account;

import org.joda.time.DateTime;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;


/**
 * @author JulianV
 *
 */
public class DebitAccount extends Account {
	private double amount;
	private int timeToCredit;
	private int operationID;
	private Operation operation;
	
	protected DebitAccount(double amount, Operation operation) {
		this.operationID = operation.getOperationID();
		this.operation = operation;
		this.amount = setAmount(amount);
		this.setTimeToCredit();
	}

	private void setTimeToCredit() {
		timeToCredit = operation.getPaymentType().getTimeToCredit();
		
	}
	
	public void generateCredit() throws InvalidAmountException{
		//Creates a new operation that register the Credit movement from the DebitAccount to the BankAccount 
		new Operation( new DateTime(), this.getAmount(),
					true, operation.getShift(), operation.getCategory(), operation.getPaymentType());
	} 

	public double setAmount(double newAmount) {
		if (operation.isIncome())
			return this.amount = this.amount + newAmount;
		else
			return this.amount = this.amount - newAmount;
	}

	public double getAmount() {
		return amount;
	}

	public int getOperationID() {
		return operationID;
	}

	public int getTimeToCredit() {
		return timeToCredit;
	}

}
