package ar.edu.unq.desapp.grupoD.model.payment;

import ar.edu.unq.desapp.grupoD.model.account.Account;

public class CreditCard extends PaymentType {

	public CreditCard( Account account ){
		this.setAccount(account);
	}
	
}
