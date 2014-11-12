package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;


/**
 * This Object dao is used on the ArquitecturalPersistenceTest to handle objects
 * instead of having to infer the dao for each entity
 * 
 * @author Lucas
 *
 */
public class ObjectDao extends HibernateGenericDAO<Object> implements
		GenericRepository<Object> {

	@Override
	protected Class<Object> getDomainClass() {
		return Object.class;
	}

	public List<Object> findAll(Class<? extends Object> class1) {
		List<Object> allResults = this.findAll();
		List<Object> result = new ArrayList<Object>();
		
		for( Object o : allResults){
			if( o.getClass() == class1){
				result.add(o);
			}
		}
		return result;
	}

	
	
}
