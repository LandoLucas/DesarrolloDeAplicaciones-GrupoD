package ar.edu.unq.desapp.grupoD.model.account;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidOperationIDException;
import ar.edu.unq.desapp.grupoD.model.Operation;
/**
 * @author JulianV
 */
public class PettyCashAccountTest {

	@Test
	public void TestPettyCashAccountConstructorIfIncome() throws InvalidOperationIDException {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		
		PettyCashAccount pettyCashAccount = new PettyCashAccount(amount, operation);
		
		assertEquals( amount, pettyCashAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
	
	@Test
	public void TestPettyCashAccountConstructorIfOutcome() throws InvalidOperationIDException {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		
		PettyCashAccount pettyCashAccount = new PettyCashAccount(amount, operation);
		
		assertEquals( 0-amount, pettyCashAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
}
