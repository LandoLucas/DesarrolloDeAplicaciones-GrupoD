package ar.edu.unq.desapp.grupoD.model.report;

import org.joda.time.DateTime;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * @author JulianV
 *
 */
public abstract class Report {
	
	public abstract void report (DateTime initialDate, DateTime finalDate);
	
	public abstract void report (PaymentType paymentType);

}
