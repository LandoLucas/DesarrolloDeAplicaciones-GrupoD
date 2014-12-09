package ar.edu.unq.desapp.grupoD.persistence;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.services.AccountService;
import ar.edu.unq.desapp.grupoD.services.CategoryService;
import ar.edu.unq.desapp.grupoD.services.OperationService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeAService;
import ar.edu.unq.desapp.grupoD.services.ReceiptTypeBService;

@ContextConfiguration(locations = {"classpath:spring-production-context.xml"})
public class DatabaseInitializerTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private DatabaseInitializer databaseInitializer;

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ReceiptTypeAService receiptTypeAService;
	
	@Autowired
	private ReceiptTypeBService receiptTypeBService;
	
	@Test
	public void initializeDatabase() throws InvalidAmountException, InvalidReceiptNumberException{
		//Spring post-construct has already fired populateDatabase()
		assertEquals(6 , operationService.findAll().size());
		assertEquals(2 , accountService.findAll().size());
		assertEquals(3 , categoryService.findAll().size());
		assertEquals(1 , receiptTypeAService.findAll().size());
		assertEquals(1 , receiptTypeBService.findAll().size());
		
	}
	
	
}
