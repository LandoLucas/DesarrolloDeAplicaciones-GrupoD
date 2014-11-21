package ar.edu.unq.desapp.grupoD.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeC;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptCDao;

/*
 * @author JulianV
 */
public class ReceiptTypeCService {
	private ReceiptCDao receiptCDao = new ReceiptCDao();
	
	
	
	public void setReceiptCDao(ReceiptCDao receiptCDao) {
		this.receiptCDao = receiptCDao;
	}

	@Transactional
	public void save(ReceiptTypeC receipt) {
		receiptCDao.save(receipt);
	}
}

