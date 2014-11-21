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
//	private static double balance = 0;

	@Column
	private double amount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
//	@Override
//	public void bill(Operation operation) {
//		new BankAccount(operation);
//	}
	
	public BankAccount(double amount) {
		this.amount = amount;
	}

//	public BankAccount(Operation operation) {
//		this.amount = operation.getAmount();
//		setBalance(this.amount, operation);
//	}

//	public static void setBalance(double newAmount, Operation operation) {
//		if (operation.isIncome())
//			balance = balance + newAmount;
//		else
//			balance = balance - newAmount;
//	}
//	
//	public static double getBalance(){
//		return balance;
//	}
//	
//	public static void resetBalance(){
//		balance = 0;
//	}
	
	public Integer getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public BankAccount(){}
	

}
