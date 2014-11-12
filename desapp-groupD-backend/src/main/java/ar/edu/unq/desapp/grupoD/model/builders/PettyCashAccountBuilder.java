package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;

public class PettyCashAccountBuilder implements ArquitecturalTestBuilder<PettyCashAccount>{

	@Override
	public PettyCashAccount any() {
		return new PettyCashAccount(10, 2);
	}

	
}
