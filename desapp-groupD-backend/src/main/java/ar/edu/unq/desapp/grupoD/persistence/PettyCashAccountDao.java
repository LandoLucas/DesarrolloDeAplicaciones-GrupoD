package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;

@SuppressWarnings("serial")
public class PettyCashAccountDao extends HibernateGenericDAO<PettyCashAccount> implements
GenericRepository<PettyCashAccount>{

	@Override
	protected Class<PettyCashAccount> getDomainClass() {
		return PettyCashAccount.class;
	}

	public double getAmount() {
		//If the account was not yet created do it and return 0
		List<PettyCashAccount> accounts = this.findAll();
		if ( accounts.isEmpty() ){
			this.save(new PettyCashAccount(0));
			return 0;
		}
		return accounts.get(0).getAmount();
	}

	public void newAmmount(double totalInPettyCash) {
		PettyCashAccount account = findAll().get(0);
		account.setAmount(totalInPettyCash);
		save(account);
	}

	
	
}
