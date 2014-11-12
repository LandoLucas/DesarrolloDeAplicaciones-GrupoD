package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;


/**
 * @author JulianV
 *
 */
@Entity
public class DebitAccount extends Account {
	private static double balance = 0;
	
	private double amount;
	private int timeToCredit;
	private int operationID;
	
	public DebitAccount(double amount, int timeToCredit, int operationID) {
		this.amount = amount;
		this.timeToCredit = timeToCredit;
		this.operationID = operationID;
	}

	@Override
	public void bill(Operation operation) {
		new DebitAccount(operation);
		
	}
	
	public DebitAccount(Operation operation) {
		this.operationID = operation.getOperationID();
		this.amount = operation.getAmount();
		setBalance(this.amount, operation);
		this.setTimeToCredit(operation);
	} 

	private void setTimeToCredit(Operation operation) {
		timeToCredit = operation.returnPaymentType().getTimeToCredit();
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
	
	public DebitAccount(){
		
	}

}
