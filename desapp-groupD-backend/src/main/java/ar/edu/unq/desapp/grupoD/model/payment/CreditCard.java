package ar.edu.unq.desapp.grupoD.model.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * @author Lucas
 */
@Entity
public class CreditCard extends PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	// A credit card payment is available after 15 days.
	private final int TIME_TO_CREDIT = 15;

	public Integer getId() {
		return id;
	}

	public CreditCard(Account account) {
		this.setAccount(account);
	}

	public CreditCard() {
	}

	@Override
	public int getTimeToCredit() {
		return TIME_TO_CREDIT;
	}

}
