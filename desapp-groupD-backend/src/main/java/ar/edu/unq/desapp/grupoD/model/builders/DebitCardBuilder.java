package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;

public class DebitCardBuilder implements ArquitecturalTestBuilder<DebitCard>{

	@Override
	public DebitCard any() {
		return new DebitCard();
	}

}
