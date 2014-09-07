package ar.edu.unq.desapp.grupoD.model.payment;

import ar.edu.unq.desapp.grupoD.model.account.Account;

public abstract class PaymentType {

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
	public void bill(double amount) {
		this.account.bill(amount);
	}

}
