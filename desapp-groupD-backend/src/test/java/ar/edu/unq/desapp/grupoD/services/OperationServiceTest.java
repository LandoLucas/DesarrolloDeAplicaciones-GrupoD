package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class OperationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private OperationService operationService;
	
	@Test
	public void saveOperation() throws InvalidAmountException{
		DateTime date = new DateTime();
		Category category = new Category("ventas1");
		Account account = new PettyCashAccount();
		Operation operation = new Operation(date, 400, true, category, account);
	
		operationService.saveOperation(operation);
		
		assertEquals( operation , operationService.getOperationByID(operation.getOperationID()));
	}
	
	@Test(expected = InvalidAmountException.class)
	public void saveOperationInvalidAmountExceptionExpected() throws InvalidAmountException{
		operationService.saveOperation(new DateTime(), -10, true, "afternoon", "Ventas", "Ventas Tarde", "ventas", new CreditCard(new BankAccount()));
	}
	
	
	@Test @Ignore
	public void removeOperation() throws InvalidAmountException{
		DateTime date = new DateTime();
		Category category = new Category("Compras");
		Account account = new PettyCashAccount();
		Operation operation = new Operation(date, 400, true, category, account);
		operationService.saveOperation(operation);
		int id = operation.getOperationID();
		
		operationService.removeOperationByID(id);
		
		assertNull( operationService.getOperationByID(id));
	}
}
