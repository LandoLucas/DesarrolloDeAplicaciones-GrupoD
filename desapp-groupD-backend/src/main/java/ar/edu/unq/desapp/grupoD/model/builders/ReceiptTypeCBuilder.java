package ar.edu.unq.desapp.grupoD.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeC;

public class ReceiptTypeCBuilder implements ArquitecturalTestBuilder<ReceiptTypeC>{

	@Override
	public ReceiptTypeC any() {
		ReceiptTypeC receipt = null;
		
		try {
			receipt = new ReceiptTypeC(new DateTime(), 1, "client", "firm", "1212121212", "address", 123456678, 123);
		} catch (InvalidReceiptNumberException e) {
		}
		
		return receipt;
	}

}
