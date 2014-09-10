package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import ar.edu.unq.desapp.grupoD.model.Operation;
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
		Operation operation = mock(Operation.class);
		Account account = mock(Account.class);
		PettyCash pettyCash = new PettyCash(account);
		
		pettyCash.bill(operation);
	
		verify(account).bill(operation);
	}
	
	
}
