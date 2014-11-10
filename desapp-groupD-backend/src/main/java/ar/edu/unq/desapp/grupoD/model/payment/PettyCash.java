package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * Represents the payment made with cash
 * @author Lucas
 */
@Entity
public class PettyCash extends PaymentType{

	public PettyCash( Account account ){
		this.setAccount(account);
	}
	
	public PettyCash(){
		
	}

}
