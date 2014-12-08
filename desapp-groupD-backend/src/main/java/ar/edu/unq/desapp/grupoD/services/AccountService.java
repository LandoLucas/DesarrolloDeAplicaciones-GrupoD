package ar.edu.unq.desapp.grupoD.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.persistence.BankAccountDao;
import ar.edu.unq.desapp.grupoD.persistence.PettyCashAccountDao;

public class AccountService {

	private PettyCashAccountDao pettyCashAccountDao;
	private BankAccountDao bankAccountDao;

	public void setPettyCashAccountDao(PettyCashAccountDao pettyCashAccountDao) {
		this.pettyCashAccountDao = pettyCashAccountDao;
	}

	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}

	@Transactional(readOnly=true)
	public List<Account> findAll(){
		List<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add( pettyCashAccountDao.getAccount() );
		allAccounts.add( bankAccountDao.getAccount() );
		
		return allAccounts;
	}

	@Transactional
	public void save(PettyCashAccount account) {
		this.pettyCashAccountDao.save(account);
	}
	
	@Transactional
	public void save(BankAccount account) {
		this.bankAccountDao.save(account);
	}
	
}
