package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.account.DebitAccount;

public class DebitAccountBuilder implements ArquitecturalTestBuilder<DebitAccount>{

	@Override
	public DebitAccount any() {
		return new DebitAccount(10);
	}

}
