package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;
import org.joda.time.Days;

import ar.edu.unq.desapp.grupoD.model.Operation;


public class OperationDao extends HibernateGenericDAO<Operation> implements
GenericRepository<Operation> {

	@Override
	protected Class<Operation> getDomainClass() {
		return Operation.class;
	}

	public List<Operation> findOperationsWithCreditCardDebts() {
		String hql = "FROM Operation o WHERE o.isDevengada =false";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);

		List<Operation> operations = excludeRecentOperations( query.list() );
		
		return operations;
	}

	private List<Operation> excludeRecentOperations(List<Operation> operations) {
		List<Operation> result = new ArrayList<Operation>();
		
		for(Operation operation : operations){
			int daysBetweenTodayAndOperation = Days.daysBetween(operation.getDate().toLocalDate(), DateTime.now().toLocalDate()).getDays();
			if( daysBetweenTodayAndOperation > 15){
				result.add(operation);
			}
		}
		
		return result;
	}

	public List<Operation> findAllOutcomes() {
		String hql = "FROM Operation o WHERE o.isIncome =false";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);

		return query.list();
	}

	public List<Operation> findAllOutcomesByShift(String shift) {
		String hql = "FROM Operation o WHERE o.shift like '"+shift+"%' and o.isIncome =false";
		
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);

		return query.list();
	}

}