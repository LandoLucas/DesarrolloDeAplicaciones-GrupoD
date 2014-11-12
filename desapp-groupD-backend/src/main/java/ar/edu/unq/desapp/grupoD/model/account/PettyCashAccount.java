package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
@Entity
public class PettyCashAccount extends Account {
	private static double balance = 0;

	private double amount;
	private int operationID;
	
	@Override
	public void bill(Operation operation) {
		new PettyCashAccount(operation);
	}

	public PettyCashAccount(double amount, int operationID) {
		this.amount = amount;
		this.operationID = operationID;
	}



	protected PettyCashAccount (Operation operation) {
		this.operationID = operation.getOperationID();
		this.amount = operation.getAmount();
		setBalance(this.amount, operation);
	}

	public PettyCashAccount() {
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
}
