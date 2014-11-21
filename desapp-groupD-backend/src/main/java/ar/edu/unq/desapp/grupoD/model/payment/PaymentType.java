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

	
//	/**
//	 * Delegates the process of billing to the account
//	 * @param amount
//	 */
//	public void bill( Operation operation) {
//		this.account.bill(operation);
//	}

//	/**
//	 * @return the amount of days is necessary to 'wait' until the money is available.
//	 * In case of the credit cards, this takes 15 days
//	 */
//	public int getTimeToCredit() {
//		return 0;
//	}

}
