package ar.edu.unq.desapp.grupoD.model.account;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.payment.BankTransfer;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;

/**
 * @author JulianV
 *
 */
public class Account {

	public void bill(double amount, Operation operation){
		if(operation.getPaymentType() instanceof BankTransfer)
			new BankAccount(amount, operation);
		else if(operation.getPaymentType() instanceof PettyCash)
			new PettyCashAccount(amount, operation);
		else
			new DebitAccount(amount, operation);
	}

	/**
	 * This method is used when it is necessary to adjust an account balance. It is intended to be an immediate change on the total to reflect real values. 
	 * @param amount to be corrected
	 * @param isIncome to know if there is need to add or substract money
	 */
	public void adjustAccount(double amount, boolean isIncome) {
		// TODO Auto-generated method stub
		
	}

	

}

