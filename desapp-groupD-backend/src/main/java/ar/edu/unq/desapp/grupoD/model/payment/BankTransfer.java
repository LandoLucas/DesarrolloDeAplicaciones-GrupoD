package ar.edu.unq.desapp.grupoD.model.payment;

import ar.edu.unq.desapp.grupoD.model.account.Account;

public class BankTransfer extends PaymentType{

	public BankTransfer( Account account ){
		this.setAccount(account);
	}

}
