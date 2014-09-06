package ar.edu.unq.desapp.grupoD.model;

import static org.mockito.Mockito.mock;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * A Builder to construct Operation instances. It eases the reading of the tests.
 * @author Lucas
 */
public class OperationBuilder {

	private DateTime date = new DateTime();
	private double amount = 100;
	private boolean isIncome = true;
	private String shift = "tarde";
	private Category category = mock(Category.class);
	private PaymentType paymentType = mock(PaymentType.class);

	public Operation build() throws InvalidAmountException{
		return new Operation(date, amount, isIncome, shift, category, paymentType);
	}

	public OperationBuilder withAmount(double amount){
		this.amount = amount;
		return this;
	}
}
