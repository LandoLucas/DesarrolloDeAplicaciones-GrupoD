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
public class BankAccount extends Account {

	@Column
	private double amount;
	
	@Column 
	private double available;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public BankAccount(double amount, double available) {
		this.amount = amount;
		this.available = available;
	}
	
	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public BankAccount(){}

	public void setAmount(double totalInBankAccount) {
		amount = totalInBankAccount;
	}

}
