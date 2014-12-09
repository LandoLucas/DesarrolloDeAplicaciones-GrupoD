package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * @author Lucas
 */
@Entity
public class CreditCard extends PaymentType {

	public CreditCard( double amount ) throws InvalidAmountException {
		this.setAmount(amount);
	}
	
	public CreditCard(){}

	@Override
	public double getAmountInCash() {
		return 0;
	}

	@Override
	public double getAmountInBank() {
		return getAmount();
	}

	@Override
	public double getDevengado() {
		return getAmount();
	}

	@Override
	public boolean isDevengada() {
		return  !(getAmount() > 0); //If there is a charge in credit card
	};
	
}
