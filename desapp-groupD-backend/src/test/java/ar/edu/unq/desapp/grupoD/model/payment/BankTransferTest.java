package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * @author Lucas
 */
public class BankTransferTest {

	
	@Test(expected = InvalidAmountException.class)
	public void invalidAmountException() throws InvalidAmountException{
		new DebitCard(-100);
	}

//	@Test
//	public void billTest(){
//		Operation operation = mock(Operation.class);
//		Account account = mock(Account.class);
//		BankTransfer bankTransfer = new BankTransfer(account);
//		
//		bankTransfer.bill(operation);
//	
//		verify(account).bill(operation);
//	}
	
	
}
