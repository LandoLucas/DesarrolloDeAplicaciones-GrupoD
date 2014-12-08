package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;

public class ReceiptBDao extends HibernateGenericDAO<ReceiptTypeB> implements
GenericRepository<ReceiptTypeB> {

	public ReceiptTypeB getReceiptByReceiptNumber(int id) {
		List<ReceiptTypeB> receipts = getHibernateTemplate().find("from ReceiptTypeB where id = " + id);
		if( ! receipts.isEmpty()){
			return receipts.get(0);
		}else{
			return null;
		}
	}

	@Override
	protected Class<ReceiptTypeB> getDomainClass() {
		return ReceiptTypeB.class;
	}

}
