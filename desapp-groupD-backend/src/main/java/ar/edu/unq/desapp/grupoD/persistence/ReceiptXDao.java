package ar.edu.unq.desapp.grupoD.persistence;

import java.util.List;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeX;

public class ReceiptXDao extends HibernateGenericDAO<ReceiptTypeX> implements
		GenericRepository<ReceiptTypeX> {
	
	public ReceiptTypeX getReceiptByReceiptNumber(int id) {
		List<ReceiptTypeX> receipts = getHibernateTemplate().find(
				"from ReceiptTypeX where operationID = " + id);
		if (!receipts.isEmpty()) {
			return receipts.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected Class<ReceiptTypeX> getDomainClass() {
		return ReceiptTypeX.class;
	}

}
