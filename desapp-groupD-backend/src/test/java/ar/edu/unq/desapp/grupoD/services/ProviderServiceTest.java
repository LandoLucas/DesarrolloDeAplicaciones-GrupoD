package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.model.providers.Provider;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ProviderServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ProviderService providerService;
	
	@Test
	public void saveAndLoadCategory(){

		String providerId = "1";
		String name = "Coca-Cola";
		String tradeName = "Coca-Cola-ferm";
		String direction = "Calle Poronga";
		Integer cuit = 12345;
		Integer telephone = 99999;
		
		Provider provider = new Provider(providerId, name, direction, tradeName, cuit, telephone);
		providerService.save(provider);
		
		assertTrue(provider.getProviderId() != null);

		List<Provider> providers = providerService.findAll();
		assertEquals( 1 , providers.size());
		assertEquals( providerId , providers.get(0).getProviderId());
	}
	
	@Test
	public void findByProviderId(){
		String entero = "1";
		Provider provider = new Provider("1");
		providerService.save(provider);
		
		assertEquals(provider, providerService.findByProviderId(entero));
	}
	
	@Test
	public void saveProviderAndLoadTest(){
		String providerId = "1";
		String name = "Coca-Cola";
		String tradeName = "Coca-Cola-ferm";
		String direction = "Calle Poronga";
		Integer cuit = 12345;
		Integer telephone = 99999;
		
		providerService.saveProvider(providerId, name, tradeName, direction, cuit, telephone);
		
		Provider provider = providerService.findByProviderId(providerId);
		
		assertEquals(providerId, provider.getProviderId());
		assertEquals(name, provider.getName());
		assertEquals(tradeName, provider.getTradeName());
		assertEquals(direction, provider.getDirection());
		assertEquals(cuit, provider.getCuit());
		assertEquals(telephone, provider.getTelephone());
	}
	
	@Test
	public void editProviderTest(){
		String providerId = "1";
		String name = "Coca-Cola";
		String tradeName = "Coca-Cola-ferm";
		String direction = "Calle Poronga";
		Integer cuit = 12345;
		Integer telephone = 99999;
		
		Provider provider = new Provider(providerId, name, direction, tradeName, cuit, telephone);
		providerService.save(provider);
		Provider restored =  providerService.findByProviderId(provider.getProviderId());
		providerService.editProvider(restored.getId(),providerId, "Cambio de Nombre", tradeName, direction, cuit, telephone);
		
		Provider providerEdited = providerService.findByProviderId(providerId);
		
		assertEquals("Cambio de Nombre", providerEdited.getName());
		
	}
	
	@Test
	public void removeProviderTest(){
		String entero = "1";
		Provider provider = new Provider("1");
		providerService.save(provider);
		
		assertEquals(provider, providerService.findByProviderId(entero));
		
		providerService.removeProviderByProviderId(entero);
		
		assertEquals(null, providerService.findByProviderId(entero));	
	}
}
