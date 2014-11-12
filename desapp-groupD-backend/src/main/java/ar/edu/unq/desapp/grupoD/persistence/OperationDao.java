package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupoD.model.Operation;


public class OperationDao extends HibernateGenericDAO<Operation> implements
GenericRepository<Operation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7096653990076486824L;

	public Operation getOperationById(int id) {
		Operation o =  findById(id);
		return o;
	}

	public void deleteOperationByID(int id) {
		Operation operation = findById(id);
		getHibernateTemplate().delete(operation);
	}

	@Override
	protected Class<Operation> getDomainClass() {
		return Operation.class;
	}

	public List<Operation> findIncomes() {
		return this.findByType(true);
	}

	public List<Operation> findOutcomes() {
		return this.findByType(false);
	}
	
	@SuppressWarnings("unchecked")
	public List<Operation> findByType(boolean isIncome) {
		String hql = "FROM Operation o WHERE o.isIncome ="+isIncome;
		Query query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);
		return query.list();
	}

}