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
		return getAccount().getAmount();
	}

	public void newAmmount(double totalInPettyCash) {
		PettyCashAccount account = getAccount();
		account.setAmount(totalInPettyCash);
		save(account);
	}

	public PettyCashAccount getAccount() {
		List<PettyCashAccount> accounts = findAll();
		if(accounts.isEmpty()){
			PettyCashAccount account = new PettyCashAccount(0);
			save(account);
			return account;
		}
		return accounts.get(0);
	}

	
	
}
