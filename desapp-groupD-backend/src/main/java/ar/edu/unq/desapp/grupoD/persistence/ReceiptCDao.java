package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeC;

public class ReceiptCDao extends HibernateGenericDAO<ReceiptTypeC> implements
GenericRepository<ReceiptTypeC>{

	public ReceiptTypeC getReceiptByReceiptNumber(int id) {
		List<ReceiptTypeC> receipts = getHibernateTemplate().find("from ReceiptTypeC where operationID = " + id);
		if( ! receipts.isEmpty()){
			return receipts.get(0);
		}else{
			return null;
		}
	}

	@Override
	protected Class<ReceiptTypeC> getDomainClass() {
		return ReceiptTypeC.class;
	}

}
