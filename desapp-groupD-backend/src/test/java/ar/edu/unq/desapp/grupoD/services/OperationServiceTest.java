package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.builders.OperationBuilder;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.persistence.OperationDao;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class OperationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private OperationService operationService;
	
	@Autowired
	private OperationDao operationDao;
	
	
	@Test
	public void saveOperationWithParameters() throws InvalidAmountException{
		DateTime date = new DateTime();
		boolean isIncome = true;
		String shift = "mañana";
		String categoryName = "category";
		String subCategoryName = "subcategory";
		String conceptName = "concept";
		List<PaymentType> payments = new ArrayList<PaymentType>();
		
		operationService.saveOperation(date, payments, isIncome, shift, categoryName, subCategoryName, conceptName);
	
		assertTrue( operationService.findAll().get(0).getShift() == shift  );
		assertTrue( operationService.findAll().get(0).getDate() == date  );
		assertTrue( operationService.findAll().get(0).getCategory().equals(categoryName));
		
	}
	
	@Test
	public void findAllOutcomes() throws InvalidAmountException{
		OperationBuilder builder = new OperationBuilder();
		List<PaymentType> payments = new ArrayList<PaymentType>();
		payments.add(new PettyCash(2000));
		Operation outcome = builder.withIsIncome(false).withPaymentType(payments).any();
		operationService.saveOperation(outcome);

		List<Operation> outcomes = operationService.findAllOutcomes();
		
		assertEquals(1 , outcomes.size());
		assertSame( outcome , outcomes.get(0));
		assertEquals(2000 , outcome.getTotalAmount() , 0);
	}
	
	@Test
	public void findAllOutcomesByShift() throws InvalidAmountException{
		OperationBuilder builder = new OperationBuilder();
		List<PaymentType> payments = new ArrayList<PaymentType>();
		payments.add(new PettyCash(1000));
		Operation outcome = builder.withIsIncome(false).withPaymentType(payments).withShift("Tarde").any();
		operationService.saveOperation(outcome);

		List<Operation> outcomes = operationService.findAllOutcomesByShift("Tarde");
		
		assertEquals(1 , outcomes.size());
		assertSame( outcome , outcomes.get(0));
		assertEquals(1000 , outcome.getTotalAmount() , 0);
	}
		
	
	@Test
	public void devengar() throws InvalidAmountException{
		List<PaymentType> payments = new ArrayList<PaymentType>();
		CreditCard creditCard = new CreditCard(100);
		payments.add(creditCard);

		List<PaymentType> payments2 = new ArrayList<PaymentType>();
		CreditCard creditCard2 = new CreditCard(200);
		payments2.add(creditCard2);

		List<PaymentType> payments3 = new ArrayList<PaymentType>();
		CreditCard creditCard3 = new CreditCard(300);
		payments3.add(creditCard3);

		
		OperationBuilder builder = new OperationBuilder();
		Operation operationDecoy = builder.withPaymentType(payments).build();
		
		DateTime pastDate = DateTime.now().minusDays(16); 
		Operation operation = builder.withDate(pastDate).withPaymentType(payments2).build();
		
		DateTime pastDateDecoy = DateTime.now().minusDays(15); 
		Operation operationDecoy2 = builder.withDate(pastDateDecoy).withPaymentType(payments3).build();
		
		operationService.saveOperation(operationDecoy.getDate(), operationDecoy.getPaymentTypes(), operationDecoy.isIncome(), 
										operationDecoy.getShift(), operationDecoy.getCategory(), operationDecoy.getSubcategory(),
										operationDecoy.getConcept());

		operationService.saveOperation(operationDecoy2.getDate(), operationDecoy2.getPaymentTypes(), operationDecoy2.isIncome(), 
				operationDecoy2.getShift(), operationDecoy2.getCategory(), operationDecoy2.getSubcategory(),
				operationDecoy2.getConcept());

		operationService.saveOperation(operation.getDate(), operation.getPaymentTypes(), operation.isIncome(), 
				operation.getShift(), operation.getCategory(), operation.getSubcategory(),
				operation.getConcept());
		
		BankAccount account = operationService.devengar();
		
		assertTrue( operation.isDevengada());
		assertEquals( 400 , account.getDevengado() , 0);
		assertEquals( 200 , account.getAvailable() , 0);
	}
	
	
}
