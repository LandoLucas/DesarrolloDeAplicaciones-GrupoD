package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.model.Operation;


public class OperationDao extends HibernateGenericDAO<Operation> implements
GenericRepository<Operation> {

	public Operation getOperationById(int id) {
		return findById(id);
	}

	public List<Operation> getOperationSince(DateTime since) {
		return getHibernateTemplate().find("from Operation where date > " +since);
	}

	public void saveOperation(Operation operation) {
		save(operation);
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