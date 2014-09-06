package ar.edu.unq.desapp.grupoD.model.payment;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * Supported payment types are:
 * - Cash
 * - Credit card
 * - Bank account transfer 
 * @author Lucas
 *
 */
public abstract class PaymentType {

	protected Account account;
	
	/**
	 * Delegates to the account the process of billing
	 * @param amount of the operation to add or substract
	 */
	public void bill(double amount){
		this.account.bill(amount);
	}

}
