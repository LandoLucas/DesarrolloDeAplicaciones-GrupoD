package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Concept;


public class ConceptDao extends HibernateGenericDAO<Concept> implements
GenericRepository<Concept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5361052114639668786L;

	@Override
	protected Class<Concept> getDomainClass() {
		return Concept.class;
	}
	
	public void removeConceptByName(final String name) {
		List<Concept> cats =  this.getHibernateTemplate().findByExample(new Concept(name));
		this.getHibernateTemplate().delete(cats.get(0));
	}

}