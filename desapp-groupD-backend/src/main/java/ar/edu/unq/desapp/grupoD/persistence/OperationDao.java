package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.model.Operation;


public class OperationDao {

	public Operation getOperationById(int id) {
		// Search and return in hibernate an operation with same id
		return null;
	}

	public List<Operation> getOperationSince(DateTime since) {
		// returns all the operation since the given date
		return null;
	}

	public void saveOperation(Operation operation) {
		// TODO Auto-generated method stub
	}

}