package ar.edu.unq.desapp.grupoD.model.receipt;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;

/**
 * @author Lucas
 */
public class ReceiptTypeA extends Receipt {

	private double IVA;
	private double IIBB;
	private double gravado;
	private double noGravado;
	
	/**
	 * A type A receipt whose final import is calculated based on its taxes
	 * @param date
	 * @param receiptNumber
	 * @param clientOrLegalEntityName
	 * @param firmName
	 * @param cUIT
	 * @param address
	 * @param telephoneNumber
	 * @param iVA
	 * @param iIBB
	 * @param gravado
	 * @param noGravado
	 * @throws InvalidReceiptNumberException if the receipt number is equal or less than 0
	 */
	public ReceiptTypeA(DateTime date, int receiptNumber,
			String clientOrLegalEntityName, String firmName, String cUIT,
			String address, int telephoneNumber,
			double iVA, double iIBB, double gravado,
			double noGravado) throws InvalidReceiptNumberException {
	
		//Since the final import is not yet calculated it is set as 0 and set at the end of this constructor once is known.
		super(date, receiptNumber, clientOrLegalEntityName, firmName, cUIT,
				address, telephoneNumber, 0);
		
		this.setIVA(iVA);
		this.setIIBB(iIBB);
		this.setGravado(gravado);
		this.setNoGravado(noGravado);

		this.setFinalImport( calculateBaseImport() );
	}

	public double getIVA() {
		return IVA;
	}

	public void setIVA(double iVA) {
		IVA = iVA;
	}

	public double getIIBB() {
		return IIBB;
	}

	public void setIIBB(double iIBB) {
		IIBB = iIBB;
	}

	public double getGravado() {
		return gravado;
	}

	public void setGravado(double gravado) {
		this.gravado = gravado;
	}

	public double getNoGravado() {
		return noGravado;
	}

	public void setNoGravado(double noGravado) {
		this.noGravado = noGravado;
	}
	
	private double calculateBaseImport() {
		return IVA + IIBB + gravado + noGravado;
	}

}
