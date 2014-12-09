package ar.edu.unq.desapp.grupoD.model.builders;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;

public class ReceiptTypeABuilder implements ArquitecturalTestBuilder<ReceiptTypeA>{

	@Override
	public ReceiptTypeA any() {
		ReceiptTypeA receipt = null;
		try {
			receipt = new ReceiptTypeA(new DateTime(), "1", "telefonica", "telefonica", "12-34569782-9", "address", 123, 21d, 2d, 3d, 2d);
		} catch (InvalidReceiptNumberException e) {
		}
		return receipt;
	}

}
