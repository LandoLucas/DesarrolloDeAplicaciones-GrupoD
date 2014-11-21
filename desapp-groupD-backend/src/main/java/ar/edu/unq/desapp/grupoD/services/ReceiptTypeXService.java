package ar.edu.unq.desapp.grupoD.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeX;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptXDao;

/*
 * @author JulianV
 */
public class ReceiptTypeXService {
	private ReceiptXDao receiptXDao = new ReceiptXDao();
	
	
	
	public void setReceiptXDao(ReceiptXDao receiptXDao) {
		this.receiptXDao = receiptXDao;
	}

	@Transactional
	public void save(ReceiptTypeX receipt) {
		receiptXDao.save(receipt);
	}
}

