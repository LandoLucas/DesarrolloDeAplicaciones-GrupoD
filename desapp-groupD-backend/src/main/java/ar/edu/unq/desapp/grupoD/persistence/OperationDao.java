package ar.edu.unq.desapp.grupoD.persistence;

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

}