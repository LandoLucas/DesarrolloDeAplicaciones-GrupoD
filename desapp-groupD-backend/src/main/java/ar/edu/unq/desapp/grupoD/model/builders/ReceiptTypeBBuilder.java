package ar.edu.unq.desapp.grupoD.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;

public class ReceiptTypeBBuilder implements ArquitecturalTestBuilder<ReceiptTypeB>{

	@Override
	public ReceiptTypeB any() {
		ReceiptTypeB receiptTypeB = null;
		
		try {
			receiptTypeB = new ReceiptTypeB(new DateTime(), "1", "telefonica", "telefonica", "20-12345678-2", "address", 123456798, 1200);
		} catch (InvalidReceiptNumberException e) {
		}
		
		return receiptTypeB;
	}

	
}
