package ar.edu.unq.desapp.grupoD.model.providers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProviderTest {
	
	@Test
	public void completeConstructorTest(){
		Integer providerId = 1;
		String name = "Coca-Cola";
		String tradeName = "Coca-Cola-ferm";
		String direction = "Calle Poronga";
		Integer cuit = 12345;
		Integer telephone = 99999;
		
		Provider provider = new Provider(providerId, name, direction, tradeName, cuit, telephone);
		
		assertEquals(providerId, provider.getProviderId());
		assertEquals(name, provider.getName());
		assertEquals(tradeName, provider.getTradeName());
		assertEquals(direction, provider.getDirection());
		assertEquals(cuit, provider.getCuit());
		assertEquals(telephone, provider.getTelephone());
	}
	
	@Test
	public void providerIdConstructorTest(){
		Integer providerId = 1;
		
		Provider provider = new Provider(providerId);
		
		assertEquals(providerId, provider.getProviderId());
	}
	
	
}
