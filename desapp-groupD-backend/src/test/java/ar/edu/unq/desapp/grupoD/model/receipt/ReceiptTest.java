package ar.edu.unq.desapp.grupoD.model.receipt;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * @author Lucas
 */
public class ReceiptTest {

	
	@Test
	public void newReceiptTest() throws InvalidReceiptNumberException{
		DateTime date = new DateTime();
		int receiptNumber = 1;
		String clientOrLegalEntityName = "The Coca Cola Company";
		String firmName = "Coka Cola";
		String cUIT = "20-33123123-2";
		String address = "Street n°123 State";
		int telephoneNumber = 41321234;
		ReceiptType type = mock(ReceiptType.class);
		
		Receipt receipt = new Receipt(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, type);
		
		assertSame( date , receipt.getDate());
		assertEquals( receiptNumber , receipt.getReceiptNumber());
		assertEquals( clientOrLegalEntityName, receipt.getClientOrLegalEntityName());
		assertEquals( firmName , receipt.getFirmName());
		assertEquals( cUIT , receipt.getCUIT());
		assertEquals( address , receipt.getAddress());
		assertEquals( telephoneNumber , receipt.getTelephoneNumber());
		assertSame( type , receipt.getReceiptType());
	}

	
	@Test(expected = InvalidReceiptNumberException.class)
	public void invalidReceiptNumberTest() throws InvalidReceiptNumberException{
		ReceiptBuilder receiptBuilder = new ReceiptBuilder();
		receiptBuilder.withReceiptNumber(0);
		
		receiptBuilder.build();
	}
	
}
