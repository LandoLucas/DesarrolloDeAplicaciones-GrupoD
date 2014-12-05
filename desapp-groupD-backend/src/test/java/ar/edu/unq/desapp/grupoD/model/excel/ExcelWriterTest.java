package ar.edu.unq.desapp.grupoD.model.excel;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.payment.CreditCard;
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.services.OperationService;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ExcelWriterTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private ExcelWriter writer;
	
	@Autowired
	private OperationService operationService;
	
	
	@Test
	public void newExcelWriterTest() throws InvalidAmountException{
		
		DateTime date = new DateTime();
		boolean isIncome = true;
		String shift = "mañana";
		String categoryName = "category";
		String subCategoryName = "subcategory";
		String conceptName = "concept";
		List<PaymentType> payments = new ArrayList<PaymentType>();
		PettyCash pettyCash = new PettyCash(200);
		payments.add(pettyCash);
		CreditCard creditCard = new CreditCard(100);
		payments.add(creditCard);
		DebitCard debitCard = new DebitCard(300);
		payments.add(debitCard);
		
		operationService.saveOperation(date, payments, isIncome, shift, categoryName, subCategoryName, conceptName);
		
		writer.writeExcelFile();
	}

}
