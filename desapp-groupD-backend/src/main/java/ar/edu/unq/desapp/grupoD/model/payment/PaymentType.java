package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentType {

	@Column
	protected double amount;

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) throws InvalidAmountException {
		if(amount <= 0) throw new InvalidAmountException();
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public abstract double getAmountInCash();

	public abstract double getAmountInBank();

	public double getAvailable() {
		return 0;
	}

	public double getDevengado() {
		return 0;
	}

	public boolean isDevengada() {
		return true;
	}

}
