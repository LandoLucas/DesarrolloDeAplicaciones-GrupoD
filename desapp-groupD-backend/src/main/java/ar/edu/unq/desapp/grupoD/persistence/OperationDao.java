package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.Operation;


public class OperationDao extends HibernateGenericDAO<Operation> implements
GenericRepository<Operation> {

	public Operation getOperationById(int id) {
		return findById(id);
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