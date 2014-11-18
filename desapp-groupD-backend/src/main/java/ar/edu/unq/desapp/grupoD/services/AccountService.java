package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.persistence.AccountDao;
import ar.edu.unq.desapp.grupoD.persistence.AccountDao;

public class AccountService {

	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(readOnly=true)
	public List<Account> findAll(){
		return accountDao.findAll();
	}

//	@Transactional
//	public void removeAccount(String name) {
//		this.accountDao.removeAccountByName(name);
//	}

	@Transactional
	public void save(Account account) {
		this.accountDao.save(account);
	}
	
//	@Transactional
//	public void update(String name, Integer idCat) {
//		this.accountDao.update(name, idCat);
//	}
//	@Transactional
//	public Account findByName(String categoryName) {
//		return this.accountDao.getByName(categoryName);
//	}
}
