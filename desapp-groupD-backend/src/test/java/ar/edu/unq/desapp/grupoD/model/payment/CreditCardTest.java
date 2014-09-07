package ar.edu.unq.desapp.grupoD.model.payment;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * @author Lucas
 */
public class CreditCardTest {

	
	@Test
	public void newCreditCardTest(){
		Account account = mock(Account.class);
		
		CreditCard creditCard = new CreditCard(account);
		
		assertSame( account , creditCard.getAccount());
	}

	@Test
	public void billTest(){
		Account account = mock(Account.class);
		CreditCard creditCard = new CreditCard(account);
		double amount = 10d;
		
		creditCard.bill(amount);
	
		verify(account).bill(amount);
	}
	
	
}
