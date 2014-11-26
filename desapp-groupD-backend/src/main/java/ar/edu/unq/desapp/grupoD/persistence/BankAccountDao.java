package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;

@SuppressWarnings("serial")
public class BankAccountDao extends HibernateGenericDAO<BankAccount> implements
GenericRepository<BankAccount>{

	@Override
	protected Class<BankAccount> getDomainClass() {
		return BankAccount.class;
	}

	public double getAmmount(){
		BankAccount account = getAccount();
		return account.getAmount();
	}
	
	public double getAvailableAmount(){
		BankAccount account = getAccount();
		return account.getAvailable();
	}

	public void newAmmount(double totalInBankAccount) {
		BankAccount account = getAccount();
		account.setAmount(totalInBankAccount);
		save(account);
	}
	
	public BankAccount getAccount(){
		List<BankAccount> accounts = findAll();
		if(accounts.isEmpty()){
			BankAccount account = new BankAccount(0,0,0);
			save(account);
			return account;
		}
		return accounts.get(0);
	}

	public void devengar(double devengado) {
		BankAccount account = getAccount();
		account.setAvailable( account.getAvailable() + devengado);
		account.setDevengado( account.getDevengado() - devengado);
		save(account);
	}
	
	public void updateAvailable(double available) {
		BankAccount account = getAccount();
		account.setAvailable(available);
		save(account);
	}

	public double getDevengado() {
		BankAccount account = getAccount();
		return account.getDevengado();
	}

	public void updateDevengado(double devengado) {
		BankAccount account = getAccount();
		account.setDevengado(devengado);
		save(account);
	}

}