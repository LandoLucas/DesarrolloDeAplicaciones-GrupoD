package ar.edu.unq.desapp.grupoD.model.builders;

import ar.edu.unq.desapp.grupoD.model.category.Concept;

public class ConceptBuilder implements ArquitecturalTestBuilder<Concept>{

	@Override
	public Concept any() {
		return new Concept("test concept");
	}

	
}
