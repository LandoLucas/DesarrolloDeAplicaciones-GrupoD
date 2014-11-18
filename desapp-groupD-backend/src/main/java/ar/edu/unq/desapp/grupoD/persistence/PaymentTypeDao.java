package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

/**
 * 
 * @author eiroa
 *
 */
public class PaymentTypeDao extends HibernateGenericDAO<PaymentType> implements
GenericRepository<PaymentType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182139123108254032L;

	@Override
	protected Class<PaymentType> getDomainClass() {
		return PaymentType.class;
	}

}
