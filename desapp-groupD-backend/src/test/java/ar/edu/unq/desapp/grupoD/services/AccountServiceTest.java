package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;


/**
 * @author Lucas
 */
@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class AccountServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private AccountService accountService;
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Test
	public void findAll(){
		List<Account> accounts = accountService.findAll();
		assertEquals( 2 , accounts.size()); //Si no existen las cuentas se crean en el mismo momento, por lo que siempre van a ser 2.
	}
	
	@Test
	public void saveAccounts(){
		BankAccount account = new BankAccount();
		PettyCashAccount pettyCashAccount = new PettyCashAccount();
		accountService.save(account);
		accountService.save(pettyCashAccount);
		List<Account> accounts = accountService.findAll();
		assertEquals( 2 , accounts.size()); 
	}
}
