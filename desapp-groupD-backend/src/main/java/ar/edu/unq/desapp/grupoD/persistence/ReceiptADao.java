package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;

public class ReceiptADao extends HibernateGenericDAO<ReceiptTypeA> implements
GenericRepository<ReceiptTypeA> {

	public ReceiptTypeA getReceiptByReceiptNumber(int id) {
		List<ReceiptTypeA> receipts = getHibernateTemplate().find("from ReceiptTypeA where operationID = " + id);
		if( ! receipts.isEmpty()){
			return receipts.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	protected Class<ReceiptTypeA> getDomainClass() {
		return ReceiptTypeA.class;
	}

}
