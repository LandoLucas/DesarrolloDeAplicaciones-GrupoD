package ar.edu.unq.desapp.grupoD.model.account;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
public class BankAccount extends Account {

	private double amount;
	private int operationID;
	private Operation operation;
	
	protected BankAccount(double amount, Operation operation) {
		this.operationID = operation.getOperationID();
		this.operation = operation;
		this.amount = setAmount(amount);
	}

	private double setAmount(double newAmount) {
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

}
