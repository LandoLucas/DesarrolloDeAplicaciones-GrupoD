package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.persistence.PaymentTypeDao;

public class PaymentTypeService {

	private PaymentTypeDao categoryDao;

	public void setPaymentTypeDao(PaymentTypeDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Transactional(readOnly=true)
	public List<PaymentType> findAll(){
		return categoryDao.findAll();
	}

//	@Transactional
//	public void removePaymentType(String name) {
//		this.categoryDao.removePaymentTypeByName(name);
//	}

	@Transactional
	public void save(PaymentType category) {
		this.categoryDao.save(category);
	}
	
//	@Transactional
//	public void update(String name, Integer idCat) {
//		this.categoryDao.update(name, idCat);
//	}
//	@Transactional
//	public PaymentType findByName(String categoryName) {
//		return this.categoryDao.getByName(categoryName);
//	}
}
