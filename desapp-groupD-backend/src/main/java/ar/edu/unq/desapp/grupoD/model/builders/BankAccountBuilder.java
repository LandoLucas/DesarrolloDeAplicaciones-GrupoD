package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.account.BankAccount;


public class BankAccountBuilder implements ArquitecturalTestBuilder<BankAccount>{

	public BankAccount any() {
		return new BankAccount(10 , 10);
	}

}
