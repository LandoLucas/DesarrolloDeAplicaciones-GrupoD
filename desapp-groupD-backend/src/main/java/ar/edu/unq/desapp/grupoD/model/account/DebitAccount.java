package ar.edu.unq.desapp.grupoD.model.account;

import org.joda.time.DateTime;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * @author JulianV
 *
 */
public class DebitAccount extends Account {
	private double amount;
	private int timeToCredit;
	private int operationID;
	private Operation operation;
	
	@Override
	public void bill(double amount, Operation operation) {
		this.operationID = operation.getOperationID();
		this.operation = operation;
		this.amount = setAmount(amount);
		this.setTimeToCredit();
	}

	private int setTimeToCredit() {
		if(operation.getPaymentType().isCreditCard())
			return 15;
		else 
			return 0;
	}
	
	public Operation generateCredit() throws InvalidAmountException{
		//Creates a new operation that register the Credit movement from the DebitAccount to the BankAccount 
			Operation creditOperation = new Operation( new DateTime(), this.getAmount(),
					true, operation.getShift(), operation.getCategory(), operation.getPaymentType());
			return creditOperation;
	} 

	@Override
	public double setAmount(double newAmount) {
		if (operation.isIncome())
			return this.amount = this.amount + newAmount;
		else
			return this.amount = this.amount - newAmount;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public int getOperationID() {
		return operationID;
	}

	public int getTimeToCredit() {
		return timeToCredit;
	}

}
