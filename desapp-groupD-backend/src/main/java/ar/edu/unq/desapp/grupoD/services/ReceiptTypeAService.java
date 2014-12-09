package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptADao;

/*
 * @author JulianV
 */
public class ReceiptTypeAService {

	private ReceiptADao receiptADao;
	
	public void setReceiptADao(ReceiptADao receiptADao) {
		this.receiptADao = receiptADao;
	}

	@Transactional
	public ReceiptTypeA getReceiptByReceiptNumber(int id) {
		return receiptADao.getReceiptByReceiptNumber(id);
	}

	@Transactional
	public void save(ReceiptTypeA receipt) {
		receiptADao.save(receipt);
	}

	@Transactional
	public List<ReceiptTypeA> findAll() {
		return receiptADao.findAll();
	}

}
