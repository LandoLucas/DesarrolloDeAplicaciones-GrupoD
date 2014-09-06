package ar.edu.unq.desapp.grupoD.model.payment;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * Represents the payment made with cash
 * @author Lucas
 */
public class PettyCash extends PaymentType{

	public PettyCash( Account account ){
		this.setAccount(account);
	}

}
