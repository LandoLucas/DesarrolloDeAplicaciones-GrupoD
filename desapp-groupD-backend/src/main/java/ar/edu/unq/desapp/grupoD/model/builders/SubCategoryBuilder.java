package ar.edu.unq.desapp.grupoD.model.builders;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;

public class SubCategoryBuilder implements ArquitecturalTestBuilder<SubCategory>{

	@Override
	public SubCategory any() {
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add( new ConceptBuilder().any());
		SubCategory subcategory = new SubCategory("test");
		subcategory.setConcepts(concepts);
		return subcategory;
	}

}
