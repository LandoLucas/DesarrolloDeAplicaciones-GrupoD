package ar.edu.unq.desapp.grupoD.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

public class OperationTest {

	
	//Verifies that the operation constructor sets all the variables and delegates the bill method to the payment type.
	@Test
	public void newOperationTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		double amount = 100;
		boolean isIncome = true;
		String shift = "tarde";
		Category category = mock(Category.class);
		PaymentType paymentType = mock(PaymentType.class);
		
		Operation operation = new Operation(date, amount, isIncome, shift, category, paymentType);
		
		assertSame(date , operation.getDate());
		assertEquals(amount , operation.getAmount() , 1);
		assertSame(isIncome , operation.isIncome());
		assertSame(shift , operation.getShift());
		assertSame(category , operation.getCategory());
		assertSame(paymentType , operation.getPaymentType());
		
		verify(paymentType).bill(amount);
	}

	
	
}
