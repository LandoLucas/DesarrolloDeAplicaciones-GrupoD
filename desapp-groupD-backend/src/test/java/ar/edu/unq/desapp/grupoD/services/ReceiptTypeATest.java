package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;

@ContextConfiguration(locations = { "classpath:spring-base-context.xml" })
public class ReceiptTypeATest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ReceiptTypeAService receiptTypeAService;

	@Test
	public void saveAndGetReceipt() throws InvalidReceiptNumberException {
		DateTime date = new DateTime();
		ReceiptTypeA receiptTypeA = new ReceiptTypeA(date, 1, "telefonica", "telefonica", "12-34569782-9", "address", 123, 21d, 2d, 3d, 2d);

		receiptTypeAService.save(receiptTypeA);

		ReceiptTypeA result = receiptTypeAService.getReceiptByReceiptNumber(receiptTypeA.getOperationID());
		assertEquals( receiptTypeA , result);
	}

}
