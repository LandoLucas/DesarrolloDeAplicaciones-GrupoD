package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * @author Lucas
 */
@Entity
public class CreditCard extends PaymentType {

	//A credit card payment is available after 15 days.
	private final int TIME_TO_CREDIT = 15;
	
	public CreditCard( Account account ){
		this.setAccount(account);
	}
	
	public CreditCard() {
	}

	@Override
	public int getTimeToCredit() {
		return TIME_TO_CREDIT;
	}

	
	
}
