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

