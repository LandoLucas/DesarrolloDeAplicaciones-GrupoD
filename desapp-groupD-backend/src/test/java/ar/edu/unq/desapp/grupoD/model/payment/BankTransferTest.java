package ar.edu.unq.desapp.grupoD.model.payment;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;

/**
 * @author Lucas
 */
public class BankTransferTest {

	
	@Test
	public void newBankTransferTest(){
		Account account = mock(Account.class);
		
		BankTransfer bankTranfer = new BankTransfer(account);
		
		assertSame( account , bankTranfer.getAccount());
	}

	@Test
	public void billTest(){
		Operation operation = mock(Operation.class);
		Account account = mock(Account.class);
		BankTransfer bankTransfer = new BankTransfer(account);
		
		bankTransfer.bill(operation);
	
		verify(account).bill(operation);
	}
	
	
}
