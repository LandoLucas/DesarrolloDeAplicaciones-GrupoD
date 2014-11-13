package ar.edu.unq.desapp.grupoD.model.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.payment.BankTransfer;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
/**
 * @author JulianV
 */
public class DebitAccountTest {

	@Test
	public void TestDebitAccountConstructorIfIncome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		PaymentType paymentType = mock(PaymentType.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		when(operation.getAmount()).thenReturn(amount);
		when(operation.returnPaymentType()).thenReturn( paymentType );
		when(paymentType.getTimeToCredit()).thenReturn( 0 );
		
		new DebitAccount(operation);
		
		assertEquals( amount, DebitAccount.getBalance(), 1);
		assertSame(operationID , operation.getOperationID());
		
		DebitAccount.resetBalance();
	}
	
	@Test
	public void TestDebitAccountConstructorIfOutcome() {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		when(operation.getAmount()).thenReturn(amount);
		PaymentType paymentType = mock(PaymentType.class);
		when(operation.returnPaymentType()).thenReturn( paymentType ); 
		when(paymentType.getTimeToCredit()).thenReturn( 0 );
		
		new DebitAccount(operation);
		
		assertEquals( 0-amount, DebitAccount.getBalance(), 1);
		assertSame(operationID , operation.getOperationID());
		
		DebitAccount.resetBalance();
	}
	
}
