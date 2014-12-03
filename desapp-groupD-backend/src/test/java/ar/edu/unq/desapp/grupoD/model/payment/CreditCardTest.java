package ar.edu.unq.desapp.grupoD.model.payment;

import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;

/**
 * @author Lucas
 */
public class CreditCardTest {

	@Test(expected = InvalidAmountException.class)
	public void invalidAmountException() throws InvalidAmountException{
		new CreditCard(-100);
	}

}
