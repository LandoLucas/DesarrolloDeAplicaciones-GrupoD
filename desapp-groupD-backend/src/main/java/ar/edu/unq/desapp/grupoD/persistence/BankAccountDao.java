package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.account.BankAccount;

@SuppressWarnings("serial")
public class BankAccountDao extends HibernateGenericDAO<BankAccount> implements
GenericRepository<BankAccount>{

	@Override
	protected Class<BankAccount> getDomainClass() {
		return BankAccount.class;
	}

	public double getAmmount(){
		//If the account was not yet created do it and return 0
		List<BankAccount> accounts = this.findAll();
		if ( accounts.isEmpty() ){
			this.save(new BankAccount(0));
			return 0;
		}
		return accounts.get(0).getAmount();
	}

	public void newAmmount(double totalInBankAccount) {
		BankAccount account = findAll().get(0);
		account.setAmount(totalInBankAccount);
		save(account);
	}
	
}