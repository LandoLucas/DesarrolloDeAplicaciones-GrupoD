package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * @author Lucas
 */
public class CreditCardTest {

	@Test(expected = InvalidAmountException.class)
	public void invalidAmountException() throws InvalidAmountException{
		new CreditCard(-100);
	}

//	@Test
//	public void billTest(){
//		Operation operation = mock(Operation.class);
//		Account account = mock(Account.class);
//		CreditCard creditCard = new CreditCard(account);
//		
//		creditCard.bill(operation);
//	
//		verify(account).bill(operation);
//	}
	
//	@Test
//	public void getTimeToCreditTest(){
//		Account account = mock(Account.class);
//		
//		CreditCard creditCard = new CreditCard(account);
//		
//		assertEquals(15, creditCard.getTimeToCredit(),1);
//	}
	
	
}
