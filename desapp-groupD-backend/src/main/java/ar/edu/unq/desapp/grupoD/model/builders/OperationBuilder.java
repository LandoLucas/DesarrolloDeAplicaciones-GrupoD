package ar.edu.unq.desapp.grupoD.model.builders;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * A Builder to construct Operation instances. It eases the reading of the tests.
 * @author Lucas
 */
public class OperationBuilder implements ArquitecturalTestBuilder<Operation>{

	private DateTime date = new DateTime();
	private boolean isIncome = true;
	private String shift = "tarde";
	private Category category = new Category("test category");
	private SubCategory subCategory = new SubCategory("test subcategory");
	private Concept concept = new Concept("test concept");
	private List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
	private double totalInPettyCash = 100;
	private double totalInBankAccount = 200;
	
	
	public Operation build() throws InvalidAmountException{
		return new Operation(date , paymentTypes , isIncome, shift, category, subCategory, concept , totalInPettyCash , totalInBankAccount);
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
