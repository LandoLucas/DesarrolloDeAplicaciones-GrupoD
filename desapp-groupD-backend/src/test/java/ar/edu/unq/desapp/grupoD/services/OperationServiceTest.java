package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.account.PettyCashAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;

@ContextConfiguration(locations = {"classpath:spring-persistence-context.xml"})
public class OperationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private OperationService operationService;
	
	@Test
	public void saveOperation() throws InvalidAmountException{
		DateTime date = new DateTime();
		Category category = new Category("ventas");
		Account account = new PettyCashAccount();
		Operation operation = new Operation(date, 400, true, category, account);
	
		operationService.saveOperation(operation);
		
		assertEquals( operation , operationService.getOperationByID(operation.getOperationID()));
	}
	
	@Test
	public void removeOperation() throws InvalidAmountException{
		DateTime date = new DateTime();
		Category category = new Category("ventas");
		Account account = new PettyCashAccount();
		Operation operation = new Operation(date, 400, true, category, account);
		operationService.saveOperation(operation);
		int id = operation.getOperationID();
		
		operationService.removeOperationByID(id);
		
		assertNull( operationService.getOperationByID(id));
	}
}
