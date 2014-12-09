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

	public double getAmountInCash(boolean isIncome){
		return 0;
	}

	public double getAmountInBank(boolean isIncome){
		return 0;
	}

	public double getAvailable(boolean isIncome) {
		return 0;
	}

	public double getDevengado(boolean isIncome) {
		return 0;
	}

	public boolean isPendienteADevengar() {
		return false;
	}

}
