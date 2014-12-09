package ar.edu.unq.desapp.grupoD.model.receipt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * @author Lucas
 */
public class ReceiptsTest {

	
	@Test
	public void newReceiptTypeBTest() throws InvalidReceiptNumberException{
		DateTime date = new DateTime();
		String receiptNumber = "1";
		String clientOrLegalEntityName = "The Coca Cola Company";
		String firmName = "Coka Cola";
		String cUIT = "20-33123123-2";
		String address = "Street n°123 State";
		int telephoneNumber = 41321234;
		double finalImport = 1000;

		Receipt receipt = new ReceiptTypeB(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, finalImport);
				
		assertSame( date , receipt.getDate());
		assertEquals( receiptNumber , receipt.getReceiptNumber());
		assertEquals( clientOrLegalEntityName, receipt.getClientOrLegalEntityName());
		assertEquals( firmName , receipt.getFirmName());
		assertEquals( cUIT , receipt.getCUIT());
		assertEquals( address , receipt.getAddress());
		assertEquals( telephoneNumber , receipt.getTelephoneNumber());
		assertEquals(finalImport, receipt.getFinalImport() , 0.000001);
	}

	@Test
	public void newReceiptTypeCTest() throws InvalidReceiptNumberException{
		DateTime date = new DateTime();
		String receiptNumber = "1";
		String clientOrLegalEntityName = "The Coca Cola Company";
		String firmName = "Coka Cola";
		String cUIT = "20-33123123-2";
		String address = "Street n°123 State";
		int telephoneNumber = 41321234;
		double finalImport = 1000;

		Receipt receipt = new ReceiptTypeC(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, finalImport);
				
		assertSame( date , receipt.getDate());
		assertEquals( receiptNumber , receipt.getReceiptNumber());
		assertEquals( clientOrLegalEntityName, receipt.getClientOrLegalEntityName());
		assertEquals( firmName , receipt.getFirmName());
		assertEquals( cUIT , receipt.getCUIT());
		assertEquals( address , receipt.getAddress());
		assertEquals( telephoneNumber , receipt.getTelephoneNumber());
		assertEquals(finalImport, receipt.getFinalImport() , 0.000001);
	}

	@Test
	public void newReceiptTypeXTest() throws InvalidReceiptNumberException{
		DateTime date = new DateTime();
		String receiptNumber = "1";
		String clientOrLegalEntityName = "The Coca Cola Company";
		String firmName = "Coka Cola";
		String cUIT = "20-33123123-2";
		String address = "Street n°123 State";
		int telephoneNumber = 41321234;
		double finalImport = 1000;

		Receipt receipt = new ReceiptTypeX(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, finalImport);
				
		assertSame( date , receipt.getDate());
		assertEquals( receiptNumber , receipt.getReceiptNumber());
		assertEquals( clientOrLegalEntityName, receipt.getClientOrLegalEntityName());
		assertEquals( firmName , receipt.getFirmName());
		assertEquals( cUIT , receipt.getCUIT());
		assertEquals( address , receipt.getAddress());
		assertEquals( telephoneNumber , receipt.getTelephoneNumber());
		assertEquals(finalImport, receipt.getFinalImport() , 0.000001);
	}

	


	
	@Test
	public void newReceiptTypeATest() throws InvalidReceiptNumberException{
		DateTime date = new DateTime();
		String receiptNumber = "1";
		String clientOrLegalEntityName = "The Coca Cola Company";
		String firmName = "Coka Cola";
		String cUIT = "20-33123123-2";
		String address = "Street n°123 State";
		int telephoneNumber = 41321234;
		double iVA = 10.5;
		double iIBB = 20;
		double gravado = 40;
		double noGravado = 50;
		double finalImport = iVA + iIBB + gravado + noGravado; 
		
		ReceiptTypeA receipt = new ReceiptTypeA(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT, address, telephoneNumber, iVA, iIBB, gravado, noGravado);
				
		assertSame( date , receipt.getDate());
		assertEquals( receiptNumber , receipt.getReceiptNumber());
		assertEquals( clientOrLegalEntityName, receipt.getClientOrLegalEntityName());
		assertEquals( firmName , receipt.getFirmName());
		assertEquals( cUIT , receipt.getCUIT());
		assertEquals( address , receipt.getAddress());
		assertEquals( telephoneNumber , receipt.getTelephoneNumber());
		assertEquals(iVA, receipt.getIVA(), 0.000001);
		assertEquals(iIBB, receipt.getIIBB(), 0.000001);
		assertEquals(gravado, receipt.getGravado(), 0.000001);
		assertEquals(noGravado, receipt.getNoGravado(), 0.000001);
		assertEquals(iVA, receipt.getIVA(), 0.000001);
		assertEquals(finalImport, receipt.getFinalImport(), 0.000001);
		
	}
	
	
}