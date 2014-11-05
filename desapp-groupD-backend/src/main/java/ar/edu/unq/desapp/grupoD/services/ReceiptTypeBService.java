package ar.edu.unq.desapp.grupoD.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptBDao;

/*
 * @author JulianV
 */
public class ReceiptTypeBService {
	private ReceiptBDao receiptBDao = new ReceiptBDao();

	public void setReceiptBDao(ReceiptBDao receiptBDao) {
		this.receiptBDao = receiptBDao;
	}

	@Transactional
	public ReceiptTypeB getReceiptByReceiptNumber(int id) {
		return receiptBDao.getReceiptByReceiptNumber(id);
	}

	@Transactional
	public void save(ReceiptTypeB receipt) {
		receiptBDao.save(receipt);
	}

	@Transactional
	public void editReceiptB(ReceiptTypeB receipt){
		receiptBDao.save(receipt);
	}
}
