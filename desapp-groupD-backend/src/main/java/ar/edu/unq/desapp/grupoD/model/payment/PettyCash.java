package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * Represents the payment made with cash
 * @author Lucas
 */
@Entity
public class PettyCash extends PaymentType{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	
	public Integer getId() {
		return id;
	}
	
	public PettyCash( Account account ){
		this.setAccount(account);
	}
	
	public PettyCash(){
		
	}

}
