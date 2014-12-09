package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * Represents the payment made with cash
 * @author Lucas
 */
@Entity
public class PettyCash extends PaymentType{
	
	public PettyCash( double amount) throws InvalidAmountException{
		this.setAmount(amount);
	}
	
	public PettyCash(){}

	@Override
	public double getAmountInCash(boolean isIncome) {
		if(isIncome) return getAmount();
		else{return - getAmount();}
	}

}
