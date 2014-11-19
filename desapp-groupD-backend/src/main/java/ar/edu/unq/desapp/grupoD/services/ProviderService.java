package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.providers.Provider;
import ar.edu.unq.desapp.grupoD.persistence.ProviderDao;

public class ProviderService {
	
	private ProviderDao providerDao;
	
	public void setProviderDao(ProviderDao providerDao){
		this.providerDao = providerDao;
	}
	
	@Transactional
	public Provider findByProviderId(int id){
		return providerDao.getByProviderId(id);
	}

	@Transactional
	public void save(Provider provider) {
		this.providerDao.save(provider);
	}

	@Transactional(readOnly=true)
	public List<Provider> findAll() {
		return providerDao.findAll();
	}

	@Transactional
	public void saveProvider(Integer providerId, String name, String tradeName, String direction, Integer cuit, Integer telephone) {
		Provider provider = new Provider(providerId, name, direction, tradeName, cuit, telephone);
		providerDao.save(provider);
	}

	@Transactional
	public void editProvider(Integer providerId, String name, String tradeName, String direction, Integer cuit, Integer telephone) {
		
		Provider provider = findByProviderId(providerId);
		provider.setCuit(cuit);
		provider.setDirection(direction);
		provider.setName(name);
		provider.setTelephon(telephone);
		provider.setTradeName(tradeName);
		
		providerDao.save(provider);
	}

}
