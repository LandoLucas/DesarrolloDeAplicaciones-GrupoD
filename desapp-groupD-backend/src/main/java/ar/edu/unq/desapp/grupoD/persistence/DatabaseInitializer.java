package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeBService;

/**
 * This class is the responsible to populate the database with fake information in order to test the application
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
		
		Logger logger = Logger.getLogger(getClass());
		logger.info("==========================");
		logger.info("DATABASE POPULATED");
		logger.info("==========================");
	}

	private void loadAccounts() {
		PettyCashAccount pettyCashAccount = new PettyCashAccount(0);
		BankAccount bankAccount = new BankAccount(0,0,0);
		accountService.save(bankAccount); 
		accountService.save(pettyCashAccount);
	}

	private void loadReceipts() throws InvalidReceiptNumberException {
		ReceiptTypeA receiptA = new ReceiptTypeA(new DateTime(), 1, "coca-cola", "The coca cola company", "30-123456-3", "cocalandia", 5555555, 21, 12, 2, 1); 
		ReceiptTypeB receiptB = new ReceiptTypeB(new DateTime(), 2, "La serenisima", "la serenisima", "30-987654321-3", "serenopolis", 12345678, 220);

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
		
		Concept concept2 = new Concept("pagos");
		List<Concept> concepts2 = new ArrayList<Concept>();
		concepts.add(concept2);
		subcategory3.setConcepts(concepts2);
		
		
		subcategories.add(subcategory);
		subcategories.add(subcategory2);
		
		category.setSubcategory(subcategories);
		
		categoryService.save(category);
		
		PettyCashAccount pettyCash = new PettyCashAccount();
		accountService.save(pettyCash);
		
		List<PaymentType> paymentTypes = createPaymentType(200 , 300 , 500);
		List<PaymentType> paymentTypes2 = createPaymentType(1200 , 1300 , 1500);
		List<PaymentType> paymentTypes3 = createPaymentType(600 , 300 , 1500);
		List<PaymentType> paymentTypes4 = createPaymentType(500 , 300 , 500);
		List<PaymentType> paymentTypes5 = createPaymentType(500 , 100 , 200);
		List<PaymentType> paymentTypes6 = createPaymentType(300 , 300 , 500);
		
		loadOperation(category, subcategory, concept , DateTime.now().minusDays(20), paymentTypes , true, "Tarde");
		loadOperation(category, subcategory2, concept , new DateTime(), paymentTypes2 , true, "Tarde");
		loadOperation(category2, subcategory3, concept2 , DateTime.now().minusDays(6), paymentTypes3 , false, "Tarde");
		loadOperation(category3, subcategory3, concept2 , DateTime.now().minusDays(12), paymentTypes4 , true, "Tarde");
		loadOperation(category3, subcategory3, concept2 , DateTime.now().minusDays(12), paymentTypes5 , false, "Mañana");
		loadOperation(category3, subcategory3, concept2 , DateTime.now().minusDays(12), paymentTypes6 , false, "Noche");
	}

	private List<PaymentType> createPaymentType(double pettyCashAmmount, double CreditCardAmount, double DebitCardAmount) throws InvalidAmountException {
		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		paymentTypes.add(new PettyCash(pettyCashAmmount));
		paymentTypes.add(new CreditCard(CreditCardAmount));
		paymentTypes.add(new DebitCard(DebitCardAmount));
		return paymentTypes;
	}

	private void loadOperation(Category category, SubCategory subcategory, Concept concept , DateTime date, List<PaymentType> paymentTypes , boolean isIncome, String shift) throws InvalidAmountException {
		operationService.saveOperation(date, paymentTypes , isIncome, shift, category.getCategoryName(), subcategory.getSubcategoryName(), concept.getConceptName());
	}

}
