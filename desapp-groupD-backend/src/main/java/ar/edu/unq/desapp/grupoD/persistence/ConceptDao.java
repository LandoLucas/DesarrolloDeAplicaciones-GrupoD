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

	public void removeConceptByName(final String name, Integer categoryId) {
		this.getHibernateTemplate().delete(this.getByName(name));
	}
	
	public Concept getByName(String name){
		List<Concept> subs =this.getHibernateTemplate().findByExample(new Concept(name));
		if(subs.isEmpty())return null;
		return subs.get(0);
	}
	
	public void update(String name, int id){
		Concept target = this.findById(id);
		target.setConceptName(name);
		this.save(target);
	}

}