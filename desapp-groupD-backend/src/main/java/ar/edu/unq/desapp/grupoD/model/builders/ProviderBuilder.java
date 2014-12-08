package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.providers.Provider;

public class ProviderBuilder implements ArquitecturalTestBuilder<Provider> {
	String providerId = "1";
	String name = "Coca-Cola";
	String tradeName = "Coca-Cola-ferm";
	String direction = "Calle Poronga";
	Integer cuit = 12345;
	Integer telephone = 99999;

	@Override
	public Provider any() {
		return new Provider(providerId, name, direction, tradeName, cuit, telephone);
	}

}
