package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class OperationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private OperationService operationService;
	
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
	}
	
}
