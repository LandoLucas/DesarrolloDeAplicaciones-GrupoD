package ar.edu.unq.desapp.grupoD.persistence;

import java.io.Serializable;
import java.util.List;

import ar.edu.unq.desapp.grupoD.model.providers.Provider;

public class ProviderDao extends HibernateGenericDAO<Provider> implements
GenericRepository<Provider> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409104105299693855L;
	
	public Provider getByProviderId(int providerId){
		List<Provider> providers = this.getHibernateTemplate().findByExample(new Provider(providerId));
		if(providers.isEmpty())return null;
		return providers.get(0);
	}

	@Override
	public Provider findById(Serializable id) {
		Provider provider = findById(id);
		return provider;
	}

	@Override
	protected Class<Provider> getDomainClass() {
		return Provider.class;
	}

}
