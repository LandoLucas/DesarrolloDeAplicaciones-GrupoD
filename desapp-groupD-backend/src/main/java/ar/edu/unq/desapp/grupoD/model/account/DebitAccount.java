package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author JulianV
 *
 */
@Entity
public class DebitAccount extends Account {
	
	private double amount;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public DebitAccount(double amount) {
		this.amount = amount;
	}

	
	public Integer getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}
	
	public DebitAccount(){
		
	}

}
