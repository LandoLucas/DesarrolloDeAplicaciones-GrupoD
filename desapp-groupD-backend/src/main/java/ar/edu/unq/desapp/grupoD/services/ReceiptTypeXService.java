package ar.edu.unq.desapp.grupoD.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.receipt.Receipt;
import ar.edu.unq.desapp.grupoD.model.receipt.ReceiptTypeB;
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

