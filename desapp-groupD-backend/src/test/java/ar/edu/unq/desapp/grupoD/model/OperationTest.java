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

	
	@Test
	public void newOperationTest() throws InvalidAmountException{
		DateTime date = new DateTime();
		boolean isIncome = true;
		String shift = "afternoon";
		Category category = mock(Category.class);
		SubCategory subCategory = mock(SubCategory.class);
		Concept concept = mock(Concept.class);
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		
		Operation operation = new Operation(date, paymentTypes, isIncome, shift, category, subCategory, concept , 340 , 430);
		
		assertSame(date , operation.getDate());
		assertSame(isIncome , operation.isIncome());
		assertSame(shift , operation.getShift());
		assertSame(category.getCategoryName() , operation.getCategory());
	}

}
