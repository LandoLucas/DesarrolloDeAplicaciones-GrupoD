package ar.edu.unq.desapp.grupoD.model.builders;

import static org.mockito.Mockito.mock;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.persistence.ArquitecturalPersistenceTest;

/**
 * A Builder to construct Operation instances. It eases the reading of the tests.
 * @author Lucas
 */
public class OperationBuilder implements ArquitecturalTestBuilder<Operation>{

	private DateTime date = new DateTime();
	private double amount = 100;
	private boolean isIncome = true;
	private String shift = "tarde";
	private Category category = new Category("test category");
	private SubCategory subCategory = new SubCategory("test subcategory");
	private Concept concept = new Concept("test concept");
	private PaymentType paymentType = new PettyCash();
	private int operationID = 1;

	public Operation build() throws InvalidAmountException{
		return new Operation(date, operationID  , amount, isIncome, shift, category, subCategory, concept, paymentType);
	}

	public OperationBuilder withAmount(double amount){
		this.amount = amount;
		return this;
	}

	@Override
	public Operation any() {
		Operation operation = null;
		try {
			operation = build();
		} catch (InvalidAmountException e) {
		}
		return operation;
	}
}
