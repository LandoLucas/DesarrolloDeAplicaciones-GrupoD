package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.category.Category;

/**
 * 
 * @author eiroa
 *
 */
public class AccountDao extends HibernateGenericDAO<Account> implements
GenericRepository<Account>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8840556388983015156L;

	@Override
	protected Class<Account> getDomainClass() {
		return Account.class;
	}

}
