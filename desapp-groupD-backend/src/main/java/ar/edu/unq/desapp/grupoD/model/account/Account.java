package ar.edu.unq.desapp.grupoD.model.account;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
public abstract class Account {

	public abstract void bill(double amount, Operation operation);
	
	public abstract double getAmount();
	
	public abstract int getOperationID();
	
	public abstract double setAmount(double amount);
	

}

