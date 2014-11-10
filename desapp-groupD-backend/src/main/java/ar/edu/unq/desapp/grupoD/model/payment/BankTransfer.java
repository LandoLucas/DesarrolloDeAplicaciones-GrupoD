package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.model.account.Account;

@Entity
public class BankTransfer extends PaymentType{

	public BankTransfer( Account account ){
		this.setAccount(account);
	}
	
	public BankTransfer(){
		
	}

}
