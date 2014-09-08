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
	
	@Override
	public void bill(double amount, Operation operation) {
		this.operationID = operation.getOperationID();
		this.operation = operation;
		this.amount = setAmount(amount);
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

}
