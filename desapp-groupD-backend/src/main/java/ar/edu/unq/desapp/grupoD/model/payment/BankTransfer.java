package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

@Entity
public class BankTransfer extends PaymentType{
	
	public BankTransfer( double amount  ) throws InvalidAmountException{
		this.setAmount(amount);
	}
	
}
