package ar.edu.unq.desapp.grupoD.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.persistence.OperationDao;

/**
 * @author JulianV
 */
public class OperationService {

	private OperationDao operationDao = new OperationDao();

	public void setOperationDao(OperationDao operationDao) {
		this.operationDao = operationDao;
	}

	@Transactional
	public Operation getOperationByID(int id) {
		return operationDao.getOperationById(id);
	}

	@Transactional
	public void removeOperationByID(int id) {
		operationDao.deleteOperationByID(id);
	}

	@Transactional
	public void saveOperation(Operation operation) {
		operationDao.save(operation);
	}

}
