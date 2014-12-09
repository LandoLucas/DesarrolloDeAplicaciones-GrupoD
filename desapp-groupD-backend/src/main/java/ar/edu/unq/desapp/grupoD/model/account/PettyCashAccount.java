package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author JulianV
 *
 */
@Entity
public class PettyCashAccount extends Account {

	@Column
	private double amount;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public PettyCashAccount(double amount) {
		this.amount = amount;
	}

	public PettyCashAccount() {
	}

	public Integer getId() {
		return id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
