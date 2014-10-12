package ar.edu.unq.desapp.grupoD.services;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.receipt.Receipt;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeA;
import ar.edu.unq.desapp.grupoD.persistence.ReceiptADao;

/*
 * @author JulianV
 */
@Service
@Path("/ReceiptA")
public class ReceiptTypeAService {

	private ReceiptADao ReceiptADao = new ReceiptADao();

	public void setReceiptADao(ReceiptADao receiptADao) {
		ReceiptADao = receiptADao;
	}

	@Transactional
	public Receipt getReceiptByReceiptNumber(int id) {
		return ReceiptADao.getReceiptByReceiptNumber(id);
	}

	@Transactional
	public void save(ReceiptTypeA receipt) {
		ReceiptADao.save(receipt);
	}

}
