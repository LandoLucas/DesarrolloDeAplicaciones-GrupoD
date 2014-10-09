package ar.edu.unq.desapp.grupoD.model.account;

import org.joda.time.DateTime;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;


/**
 * @author JulianV
 *
 */
public class DebitAccount extends Account {
	private static double balance = 0;
	
	private double amount;
	private int timeToCredit;
	private int operationID;
	private Operation operation;
	
	@Override
	public void bill(Operation operation) {
		new DebitAccount(operation);
		
	}
	
	protected DebitAccount(Operation operation) {
		this.operationID = operation.getOperationID();
		this.operation = operation;
		this.amount = operation.getAmount();
		setBalance(this.amount, operation);
		this.setTimeToCredit();
	} 

	private void setTimeToCredit() {
		timeToCredit = operation.getPaymentType().getTimeToCredit();
	}
	
	public Operation generateCredit() throws InvalidAmountException{
		//Creates a new operation that register the Credit movement from the DebitAccount to the BankAccount 
		return new Operation( new DateTime(), this.getAmount(),
					true, operation.getShift(), operation.getCategory(), operation.getCategory().getSubcategory(),
					operation.getCategory().getSubcategory().getConcept(), operation.getPaymentType());
		//balance = balance - operation.getAmount(); 
	} 

	public static void setBalance(double newAmount, Operation operation) {
		if (operation.isIncome())
			balance = balance + newAmount;
		else
			balance = balance - newAmount;
	}
	
	public static double getBalance(){
		return balance;
	}
	
	public static void resetBalance(){
		balance = 0;
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
