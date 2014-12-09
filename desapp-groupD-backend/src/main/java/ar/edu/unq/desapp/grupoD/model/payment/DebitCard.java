package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

@Entity
public class DebitCard extends PaymentType{
	
	public DebitCard( double amount  ) throws InvalidAmountException{
		this.setAmount(amount);
	}
	
	public DebitCard(){}

	@Override
	public double getAmountInBank(boolean isIncome) {
		if(isIncome) return getAmount();
		else{return - getAmount();}
	}

	@Override
	public double getAvailable(boolean isIncome) {
		if(isIncome) return getAmount();
		else{return - getAmount();}
	};
	
	
	
}
