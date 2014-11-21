package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * @author Lucas
 */
public class PettyCashTest {

	@Test(expected = InvalidAmountException.class)
	public void invalidAmountException() throws InvalidAmountException{
		new PettyCash(-100);
	}
	
//	@Test
//	public void billTest(){
//		Operation operation = mock(Operation.class);
//		Account account = mock(Account.class);
//		PettyCash pettyCash = new PettyCash(account);
//		
//		pettyCash.bill(operation);
//	
////		verify(account).bill(operation);
//	}
	
	
}
