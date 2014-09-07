package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * @author Lucas
 */
public class PettyCashTest {

	
	@Test
	public void newPettyCashTest(){
		Account account = mock(Account.class);
		
		PettyCash pettyCash = new PettyCash(account);
		
		assertSame( account , pettyCash.getAccount());
	}

	@Test
	public void billTest(){
		Account account = mock(Account.class);
		PettyCash pettyCash = new PettyCash(account);
		double amount = 10d;
		
		pettyCash.bill(amount);
	
		verify(account).bill(amount);
	}
	
	
}
