package ar.edu.unq.desapp.grupoD.model.account;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidOperationIDException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.payment.BankTransfer;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
/**
 * @author JulianV
 */
public class DebitAccountTest {

	@Test
	public void TestDebitAccountConstructorIfIncome() throws InvalidOperationIDException {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(true);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		assertEquals( amount, debitAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
	
	@Test
	public void TestDebitAccountConstructorIfOutcome() throws InvalidOperationIDException {
		double amount = 10;
		int operationID = 1;
		Operation operation = mock(Operation.class);
		when(operation.getOperationID()).thenReturn(operationID);
		when(operation.isIncome()).thenReturn(false);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		assertEquals( 0-amount, debitAccount.getAmount(), 1);
		assertSame(operationID , operation.getOperationID());
	}
	
	@Test
	public void TestSetTimeToCreditIfCreditCard() throws InvalidOperationIDException {
		double amount = 10;
		Operation operation = mock(Operation.class);
		CreditCard creditCard = mock(CreditCard.class);
		PaymentType paymentType =mock(PaymentType.class);
		when(operation.getPaymentType().getTimeToCredit()).thenReturn(15);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		assertEquals( 15, debitAccount.getTimeToCredit(), 1);
	}
	
	@Test
	public void TestSetTimeToCreditIfBankTransfer() throws InvalidOperationIDException {
		double amount = 10;
		Operation operation = mock(Operation.class);
		BankTransfer bankTransfer = mock(BankTransfer.class);
		when(operation.getPaymentType()).thenReturn(bankTransfer);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		assertEquals( 0, debitAccount.getTimeToCredit(), 1);
	}
	
	@Test
	public void TestSetTimeToCreditIfPettyCash() throws InvalidOperationIDException {
		double amount = 10;
		Operation operation = mock(Operation.class);
		PettyCash pettyCash = mock(PettyCash.class);
		when(operation.getPaymentType()).thenReturn(pettyCash);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		assertEquals( 0, debitAccount.getTimeToCredit(), 1);
	}
	
	@Test
	public void TestGenerateCredit() throws InvalidAmountException{
		double amount = 10;
		Operation operation = mock(Operation.class);
		
		DebitAccount debitAccount = new DebitAccount(amount, operation);
		
		verify(debitAccount).generateCredit();
		
	}

}
