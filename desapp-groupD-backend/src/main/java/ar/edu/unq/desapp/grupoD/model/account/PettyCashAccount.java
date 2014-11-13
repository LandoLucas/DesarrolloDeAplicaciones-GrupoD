package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
@Entity
public class PettyCashAccount extends Account {
	private static double balance = 0;

	private double amount;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@Override
	public void bill(Operation operation) {
		new PettyCashAccount(operation);
	}

	public PettyCashAccount(double amount) {
		this.amount = amount;
	}



	protected PettyCashAccount (Operation operation) {
		this.amount = operation.getAmount();
		setBalance(this.amount, operation);
	}

	public PettyCashAccount() {
	}

	public static void setBalance(double newAmount, Operation operation) {
		if (operation.isIncome())
			balance = balance + newAmount;
		else
			balance = balance - newAmount;
	}
	
	public static double getBalance(){
		return balance;
	}
	
	public static void resetBalance(){
		balance = 0;
	}

	public double getAmount() {
		return amount;
	}

}
