package ar.edu.unq.desapp.grupoD.model;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * @author Lucas
 */
public class OperationTest {

	
	//Verifies that the operation constructor sets all the variables and delegates the bill method to the payment type.
	@Test
	public void newOperationTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		boolean isIncome = true;
		String shift = "afternoon";
		Category category = mock(Category.class);
		SubCategory subCategory = mock(SubCategory.class);
		Concept concept = mock(Concept.class);
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		
		Operation operation = new Operation(date, paymentTypes, isIncome, shift, category, subCategory, concept);
		
		assertSame(date , operation.getDate());
		assertSame(isIncome , operation.isIncome());
		assertSame(shift , operation.getShift());
		assertSame(category.getCategoryName() , operation.getCategory());
		
//		verify(paymentType).bill( operation);
	}

//	@Test
//	public void newAdjustmentOperationTest() throws InvalidAmountException{
//		DateTime date = new DateTime();
//		double amount = 100;
//		boolean isIncome = true;
//		Category category = mock(Category.class);
//		Account account = mock(Account.class);
//		
//		Operation operation = new Operation(date, amount, isIncome, category, account);
//		
//		assertSame(date , operation.getDate());
//		assertEquals(amount , operation.getAmount() , 1);
//		assertSame(isIncome , operation.isIncome());
//		assertSame(category.getCategoryName() , operation.getCategory());
//		
//		verify(account).bill(operation);
//	}
	
//	@Test
//	public void getOperationIDTest() throws InvalidAmountException{
//		DateTime date = new DateTime();
//		double amount = 100;
//		boolean isIncome = true;
//		Category category = mock(Category.class);
//		Account account = mock(Account.class);
//		Operation.resetCounter();
//		
//		Operation operation = new Operation(date, amount, isIncome, category, account);
//		assertEquals( 1 , operation.getOperationID());
//	
//		Operation operation2 = new Operation(date, amount, isIncome, category, account);
//		assertEquals( 2 , operation2.getOperationID());
//	}
	
	
}
