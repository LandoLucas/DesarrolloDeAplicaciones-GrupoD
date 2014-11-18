package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unq.desapp.grupoD.model.account.Account;

@Entity
public class BankTransfer extends PaymentType{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public BankTransfer( Account account ){
		this.setAccount(account);
	}
	
	public Integer getId() {
		return id;
	}
	
	public BankTransfer(){
		
	}

}
