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
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		
		BankAccount BankAccount = new BankAccount(amount, operation);
		
		assertEquals( amount, BankAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
	
	@Test
	public void TestBankAccountConstructorIfOutcome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		
		BankAccount bankAccount = new BankAccount(amount, operation);
		
		assertEquals( 0-amount, bankAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
}