package ar.edu.unq.desapp.grupoD.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.account.BankAccount;

/**
 * Service responsible to transfer the credit card debt that passes 15 days
 * @author Lucas
 */
public class ConsolidateAccountsService {

	@Autowired
	private OperationService operationService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	@Transactional
	public BankAccount consolidateAccounts() {
		return operationService.devengar();
	}

}
