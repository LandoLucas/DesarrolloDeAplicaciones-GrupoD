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
public class BankAccountTest {

	@Test
	public void TestBankAccountConstructorIfIncome() {
		double amount = 20;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		when(operation.getAmount()).thenReturn(amount);
		
		new BankAccount(operation);
		
		assertEquals( amount, BankAccount.getBalance(), 1);
		
		BankAccount.resetBalance();
	}
	
	@Test
	public void TestBankAccountConstructorIfOutcome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		when(operation.getAmount()).thenReturn(amount);
		
		new BankAccount(operation);
		
		assertEquals( 0-amount, BankAccount.getBalance(), 1);
		
		BankAccount.resetBalance();
	}
	
	@Test
	public void TestGetOperationID(){
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.getAmount()).thenReturn(amount);
		
		BankAccount bankAccount = new BankAccount(operation);
		
		assertSame(operationID , bankAccount.getOperationID());
		
		BankAccount.resetBalance();
	}
}