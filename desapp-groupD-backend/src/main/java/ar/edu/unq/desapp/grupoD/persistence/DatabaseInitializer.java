package ar.edu.unq.desapp.grupoD.persistence;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

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
@Service
@Path("/initDatabase")
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

	@GET
	@Path("/all")
	public Response populateDatabase() throws InvalidAmountException, InvalidReceiptNumberException {
		
		loadOperations();
		loadReceipts();
		
		System.out.println("==========================");
		System.out.println("POPULATED");
		System.out.println("==========================");
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	private void loadReceipts() throws InvalidReceiptNumberException {
		ReceiptTypeA receiptA = new ReceiptTypeA(new DateTime(), 1, "coca-cola", "The coca cola company", "30-123456-3", "cocalandia", 5555555, 21, 12, 2, 1);
		ReceiptTypeB receiptB = new ReceiptTypeB(new DateTime(), 2, "La serenisima", "la serenisima", "30-987654321-3", "serenopolis", 12345678, 220);

		receiptTypeAService.save(receiptA);
		receiptTypeBService.save(receiptB);
	}

	private void loadOperations() throws InvalidAmountException {
		loadOperation("Ventas", "Ventas 12-10-2014", "ventas tienda", new DateTime(), new PettyCashAccount(), 400, true);
		loadOperation("Ventas", "Ventas 13-10-2014", "ventas tienda", new DateTime(), new PettyCashAccount(), 565, true);
		loadOperation("Ventas", "Ventas 14-10-2014", "ventas tienda", new DateTime(), new PettyCashAccount(), 312, true);
		loadOperation("Pagos", "Proveedores 12-10-2014", "proveedores", new DateTime(), new BankAccount(), 200, false);
		loadOperation("Pagos", "Proveedores 05-10-2014", "proveedores", new DateTime(), new BankAccount(), 185, false);
		loadOperation("Pagos", "Proveedores 19-10-2014", "proveedores", new DateTime(), new BankAccount(), 312, false);
	}

	private void loadOperation(String categoryName, String subcategoryName, String conceptName, DateTime date, Account account, int amount, boolean isIncome) throws InvalidAmountException {
		SubCategory subcategory = new SubCategory(subcategoryName);
		subcategory.setConcept(new Concept(conceptName));
		Category category = new Category(categoryName);
		category.setSubcategory(subcategory);
		Operation operation = new Operation(date, 400, true, category, account);

		categoryService.save(category);
		operationService.saveOperation(operation);
	}

}
