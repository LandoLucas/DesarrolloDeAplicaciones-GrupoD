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

	

}

