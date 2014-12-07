package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
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
import ar.edu.unq.desapp.grupoD.services.SubCategoryService;

/**
 * This class is the responsible to populate the database with fake information
 * in order to test the application
 * 
 * @author Lucas
 */
public class DatabaseInitializer {

	private AccountService accountService;
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

	@PostConstruct
	public void populateDatabase() throws InvalidAmountException,
			InvalidReceiptNumberException {

		loadAccounts();
		loadOperations();
		loadReceipts();
		
		System.out.println("==========================");
		System.out.println("DATABASE POPULATED");
		System.out.println("==========================");
	}

	private void loadAccounts() {
		PettyCashAccount pettyCashAccount = new PettyCashAccount(0);
		BankAccount bankAccount = new BankAccount(0,0,0);
		
		accountService.save(bankAccount); 
		accountService.save(pettyCashAccount);
		System.out.println("Loaded Accounts");
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
		Category category = new Category("Ventas");
		Category category2 = new Category("Pagos");
		Category category3 = new Category("Sobornos");
		
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		SubCategory subcategory = new SubCategory("Ventas 12-10-2014");
		SubCategory subcategory2 = new SubCategory("Ventas 13-10-2014");
		SubCategory subcategory3 = new SubCategory("Pago a proveedores");
		
		Concept concept = new Concept("ventas tienda");
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);
		subcategory.setConcepts(concepts);
		
		
		subcategories.add(subcategory);
		subcategories.add(subcategory2);
		
		category.setSubcategory(subcategories);
		
		categoryService.save(category);
		
		PettyCashAccount pettyCash = new PettyCashAccount();
		accountService.save(pettyCash);
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		paymentTypes.add(new PettyCash(200));
		paymentTypes.add(new CreditCard(300));
		paymentTypes.add(new DebitCard(500));
		
		List<PaymentType> paymentTypes2 = new ArrayList<PaymentType>();
		paymentTypes2.add(new PettyCash(1200));
		paymentTypes2.add(new CreditCard(1300));
		paymentTypes2.add(new DebitCard(1500));
		
		List<PaymentType> paymentTypes3 = new ArrayList<PaymentType>();
		paymentTypes3.add(new PettyCash(1500));
		paymentTypes3.add(new CreditCard(300));
		paymentTypes3.add(new DebitCard(1500));
		
		List<PaymentType> paymentTypes4 = new ArrayList<PaymentType>();
		paymentTypes4.add(new PettyCash(500));
		paymentTypes4.add(new CreditCard(300));
		paymentTypes4.add(new DebitCard(500));
		
		loadOperation(category, subcategory, concept , DateTime.now().minusDays(20), paymentTypes , true, "tarde");
		loadOperation(category, subcategory2, concept , new DateTime(), paymentTypes2 , true, "tarde");
		loadOperation(category2, subcategory3, concept , DateTime.now().minusDays(6), paymentTypes3 , false, "tarde");
		loadOperation(category3, subcategory3, concept , DateTime.now().minusDays(12), paymentTypes4 , false, "tarde");
	}

	private void loadOperation(Category category, SubCategory subcategory, Concept concept , DateTime date, List<PaymentType> paymentTypes , boolean isIncome, String shift) throws InvalidAmountException {
		
		operationService.saveOperation(date, paymentTypes , isIncome, shift, category.getCategoryName(), subcategory.getSubcategoryName(), concept.getConceptName());
	}

}
