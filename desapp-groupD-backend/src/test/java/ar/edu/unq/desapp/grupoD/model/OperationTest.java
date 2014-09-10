package ar.edu.unq.desapp.grupoD.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * @author Lucas
 */
public class OperationTest {

	
	//Verifies that the operation constructor sets all the variables and delegates the bill method to the payment type.
	@Test
	public void newOperationTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		double amount = 100;
		boolean isIncome = true;
		String shift = "afternoon";
		Category category = mock(Category.class);
		PaymentType paymentType = mock(PaymentType.class);
		
		Operation operation = new Operation(date, amount, isIncome, shift, category, paymentType);
		
		assertSame(date , operation.getDate());
		assertEquals(amount , operation.getAmount() , 1);
		assertSame(isIncome , operation.isIncome());
		assertSame(shift , operation.getShift());
		assertSame(category , operation.getCategory());
		assertSame(paymentType , operation.getPaymentType());
		
		verify(paymentType).bill( operation);
	}

	@Test
	public void newAdjustmentOperationTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		double amount = 100;
		boolean isIncome = true;
		Category category = mock(Category.class);
		Account account = mock(Account.class);
		
		Operation operation = new Operation(date, amount, isIncome, category, account);
		
		assertSame(date , operation.getDate());
		assertEquals(amount , operation.getAmount() , 1);
		assertSame(isIncome , operation.isIncome());
		assertSame(category , operation.getCategory());
		
		verify(account).bill(operation);
	}
	
	@Test(expected = InvalidAmountException.class)
	public void newOperationInvalidAmountExceptionTest() throws InvalidAmountException{
		OperationBuilder operationBuilder = new OperationBuilder();
		operationBuilder.withAmount(-10);
		
		operationBuilder.build();
	}

	
	@Test
	public void getOperationIDTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		double amount = 100;
		boolean isIncome = true;
		Category category = mock(Category.class);
		Account account = mock(Account.class);
		Operation.resetCounter();
		
		Operation operation = new Operation(date, amount, isIncome, category, account);
		assertEquals( 1 , operation.getOperationID());
	
		Operation operation2 = new Operation(date, amount, isIncome, category, account);
		assertEquals( 2 , operation2.getOperationID());
	}
	
}
