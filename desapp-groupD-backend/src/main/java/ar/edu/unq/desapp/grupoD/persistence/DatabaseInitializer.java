package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;
import ar.edu.unq.desapp.grupoD.services.CategoryService;
import ar.edu.unq.desapp.grupoD.services.OperationService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeBService;

/**
 * This class is the responsible to populate the database with fake information
 * in order to test the application
 * 
 * @author Lucas
 */
public class DatabaseInitializer  {

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

	@PostConstruct
	public void populateDatabase() throws InvalidAmountException, InvalidReceiptNumberException {
		
		loadOperations();
		loadReceipts();
		
		System.out.println("==========================");
		System.out.println("POPULATED");
		System.out.println("==========================");
	}
	
	private void loadReceipts() throws InvalidReceiptNumberException {
		ReceiptTypeA receiptA = new ReceiptTypeA(new DateTime(), 1, "coca-cola", "The coca cola company", "30-123456-3", "cocalandia", 5555555, 21, 12, 2, 1);
		ReceiptTypeB receiptB = new ReceiptTypeB(new DateTime(), 2, "La serenisima", "la serenisima", "30-987654321-3", "serenopolis", 12345678, 220);

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
		
		loadOperation("Ventas", subcategories, new DateTime(), new PettyCashAccount(), 400, true);
//		loadOperation("Pagos", "Proveedores 12-10-2014", "proveedores", new DateTime(), new BankAccount(), 200, false);
//		loadOperation("Pagos", "Proveedores 05-10-2014", "proveedores", new DateTime(), new BankAccount(), 185, false);
//		loadOperation("Pagos", "Proveedores 19-10-2014", "proveedores", new DateTime(), new BankAccount(), 312, false);
	}

	private void loadOperation(String categoryName, List<SubCategory> subcategories, DateTime date, Account account, int amount, boolean isIncome) throws InvalidAmountException {
		
		Category category = new Category(categoryName);
		category.setSubcategory(subcategories);
		Operation operation = new Operation(date, 400, true, category, account);

		categoryService.save(category);
		operationService.saveOperation(operation);
	}

}
