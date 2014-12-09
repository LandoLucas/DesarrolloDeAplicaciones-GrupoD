package ar.edu.unq.desapp.grupoD.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeX;

public class ReceiptTypeXBuilder implements ArquitecturalTestBuilder<ReceiptTypeX>{

	@Override
	public ReceiptTypeX any() {
		ReceiptTypeX receipt = null;
		
		try {
			receipt = new ReceiptTypeX(new DateTime(), "1", "client", "firm", "21-21212121-2", "address", 123123123, 120);
		} catch (InvalidReceiptNumberException e) {
		}
		
		return receipt;
		
	}

	
}
