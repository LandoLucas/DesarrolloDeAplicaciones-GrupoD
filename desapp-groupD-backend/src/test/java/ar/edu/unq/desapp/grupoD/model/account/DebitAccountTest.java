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
	
	@Test
	public void TestSetTimeToCreditIfCreditCard() {
		double amount = 10;
		Operation operation = mock(Operation.class);
		PaymentType paymentType = mock(PaymentType.class);
		when(operation.returnPaymentType()).thenReturn(paymentType);
		when(operation.getAmount()).thenReturn(amount);
		when(paymentType.getTimeToCredit()).thenReturn(15);
		
		DebitAccount debitAccount = new DebitAccount(operation);
		
		assertEquals( 15, debitAccount.getTimeToCredit(), 1);
		
		DebitAccount.resetBalance();
	}
	
	@Test
	public void TestSetTimeToCreditIfBankTransfer() {
		double amount = 10;
		Operation operation = mock(Operation.class);
		BankTransfer bankTransfer = mock(BankTransfer.class);
		when(operation.returnPaymentType()).thenReturn(bankTransfer);
		when(operation.getAmount()).thenReturn(amount);
		
		DebitAccount debitAccount = new DebitAccount(operation);
		
		assertEquals( 0, debitAccount.getTimeToCredit(), 1);
		
		DebitAccount.resetBalance();
	}
	
	@Test
	public void TestSetTimeToCreditIfPettyCash() {
		double amount = 10;
		Operation operation = mock(Operation.class);
		PettyCash pettyCash = mock(PettyCash.class);
		when(operation.getAmount()).thenReturn(amount);
		when(operation.returnPaymentType()).thenReturn(pettyCash);
		
		DebitAccount debitAccount = new DebitAccount(operation);
		
		assertEquals( 0, debitAccount.getTimeToCredit(), 1);
		
		DebitAccount.resetBalance();
	}
	
	@Test 
	public void TestGetOperationID(){
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		PaymentType paymentType = mock(PaymentType.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.getAmount()).thenReturn(amount);
		when(operation.isIncome()).thenReturn(true);
		when(operation.returnPaymentType()).thenReturn( paymentType );
		when(operation.getAmount()).thenReturn(amount);
		when(paymentType.getTimeToCredit()).thenReturn( 0 );
		
		//DebitAccount debitAccount = mock(DebitAccount.class);
		
		DebitAccount debitAccount = new DebitAccount(operation);
		
		assertSame(operationID , debitAccount.getOperationID());
		
		DebitAccount.resetBalance();
		
	} 

}
