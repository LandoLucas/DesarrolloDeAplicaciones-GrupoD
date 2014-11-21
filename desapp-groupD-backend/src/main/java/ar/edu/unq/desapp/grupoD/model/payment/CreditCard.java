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
	
	public CreditCard(){};

}
