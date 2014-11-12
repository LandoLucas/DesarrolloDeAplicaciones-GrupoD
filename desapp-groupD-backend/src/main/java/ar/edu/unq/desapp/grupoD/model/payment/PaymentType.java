package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	protected Account account;

	protected Account getAccount() {
		return account;
	}

	protected void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * Delegates the process of billing to the account
	 * @param amount
	 */
	public void bill( Operation operation) {
		this.account.bill(operation);
	}

	/**
	 * @return the amount of days is necessary to 'wait' until the money is available.
	 * In case of the credit cards, this takes 15 days
	 */
	public int getTimeToCredit() {
		return 0;
	}

}
