package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;
import ar.edu.unq.desapp.grupoD.services.AccountService;
import ar.edu.unq.desapp.grupoD.services.CategoryService;
import ar.edu.unq.desapp.grupoD.services.OperationService;
import ar.edu.unq.desapp.grupoD.services.PaymentTypeService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeBService;

/**
 * This class is the responsible to populate the database with fake information
 * in order to test the application
 * 
 * @author Lucas
 */
public class DatabaseInitializer {

	private AccountService accountService;
	private PaymentTypeService paymentTypeService;
	private CategoryService categoryService;
	private OperationService operationService;
	private ReceiptTypeAService receiptTypeAService;
	private ReceiptTypeBService receiptTypeBService;

	public void setReceiptTypeAService(ReceiptTypeAService receiptTypeAService) {
		this.receiptTypeAService = receiptTypeAService;
	}

	public void setReceiptTypeBService(ReceiptTypeBService receiptTypeBService) {
		this.receiptTypeBService = receiptTypeBService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}

	@PostConstruct
	public void populateDatabase() throws InvalidAmountException,
			InvalidReceiptNumberException {

		//TODO initialize bank accounts
		
		
		loadOperations();
		loadReceipts();

		System.out.println("==========================");
		System.out.println("POPULATED");
		System.out.println("==========================");
	}

	private void loadReceipts() throws InvalidReceiptNumberException {
		ReceiptTypeA receiptA = new ReceiptTypeA(new DateTime(), 1,
				"coca-cola", "The coca cola company", "30-123456-3",
				"cocalandia", 5555555, 21, 12, 2, 1);
		ReceiptTypeB receiptB = new ReceiptTypeB(new DateTime(), 2,
				"La serenisima", "la serenisima", "30-987654321-3",
				"serenopolis", 12345678, 220);

		receiptTypeAService.save(receiptA);
		receiptTypeBService.save(receiptB);
	}

	private void loadOperations() throws InvalidAmountException {
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		SubCategory subcategory = new SubCategory("Ventas 12-10-2014");
		Concept concept = new Concept("ventas tienda");
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);
		subcategory.setConcepts(concepts);

		subcategories.add(subcategory);
		Account pettyCash = new PettyCashAccount();
		PaymentType cash = new PettyCash(100);
		accountService.save(pettyCash);
		paymentTypeService.save(cash);
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		paymentTypes.add(new PettyCash(200));
		paymentTypes.add(new CreditCard(300));
		paymentTypes.add(new DebitCard(500));
		
		loadOperation("Ventas", subcategory, concept , new DateTime(), paymentTypes , true, "tarde");
	}

	private void loadOperation(String categoryName, SubCategory subcategory, Concept concept , DateTime date, List<PaymentType> paymentTypes , boolean isIncome, String shift) throws InvalidAmountException {
		Category category = new Category(categoryName);
		
		Operation operation = new Operation(date, paymentTypes , isIncome, shift, category, subcategory, concept);

		categoryService.save(category);
		operationService.saveOperation(operation);
	}

}
