package ar.edu.unq.desapp.grupoD.model.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.model.Operation;
/**
 * @author JulianV
 */
public class PettyCashAccountTest {

	@Test
	public void TestPettyCashAccountConstructorIfIncome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		when(operation.getAmount()).thenReturn(amount);
		
		new PettyCashAccount(operation);
		
		assertEquals( amount, PettyCashAccount.getBalance(), 1);
		assertSame(operationID , operation.getOperationID());
		
		PettyCashAccount.resetBalance();
	}
	
	@Test
	public void TestPettyCashAccountConstructorIfOutcome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		when(operation.getAmount()).thenReturn(amount);
		
		new PettyCashAccount(operation);
		
		assertEquals( 0-amount, PettyCashAccount.getBalance(), 1);
		assertSame(operationID , operation.getOperationID());
		
		PettyCashAccount.resetBalance();
	}
	
	@Test
	public void TestGetOperationID(){
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.getAmount()).thenReturn(amount);
		
		PettyCashAccount pettyCashAccount = new PettyCashAccount(operation);
		
		assertSame(operationID , pettyCashAccount.getOperationID());
		
		PettyCashAccount.resetBalance();
		
	}
}
