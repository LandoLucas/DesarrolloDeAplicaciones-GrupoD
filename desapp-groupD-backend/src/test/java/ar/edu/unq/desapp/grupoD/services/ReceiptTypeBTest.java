package ar.edu.unq.desapp.grupoD.services;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;

@ContextConfiguration(locations = { "classpath:spring-base-context.xml" })
public class ReceiptTypeBTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ReceiptTypeBService receiptTypeBService;

	@Test
	public void saveAndGetReceipt() throws InvalidReceiptNumberException {
		DateTime date = new DateTime();
		ReceiptTypeB receiptTypeB = new ReceiptTypeB(date, 1, "telefonica", "telefonica", "20-12345678-2", "address", 123456798, 1200);

		receiptTypeBService.save(receiptTypeB);

		ReceiptTypeB result = receiptTypeBService.getReceiptByReceiptNumber(receiptTypeB.getOperationID());
		assertEquals( receiptTypeB , result);
	}

}
