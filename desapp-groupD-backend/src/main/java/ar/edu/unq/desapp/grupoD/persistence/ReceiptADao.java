package ar.edu.unq.desapp.grupoD.persistence;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;

public class ReceiptADao extends HibernateGenericDAO<ReceiptTypeA> implements
GenericRepository<ReceiptTypeA> {

	public ReceiptTypeA getReceiptByReceiptNumber(int id) {
		return (ReceiptTypeA) getHibernateTemplate().find("from ReceiptTypeA where operationID = " + id);
	}
	
	@Override
	protected Class<ReceiptTypeA> getDomainClass() {
		return ReceiptTypeA.class;
	}

}
