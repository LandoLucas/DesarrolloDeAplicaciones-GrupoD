package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.DebitCard;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.model.payment.PettyCash;
import ar.edu.unq.desapp.grupoD.persistence.BankAccountDao;
import ar.edu.unq.desapp.grupoD.persistence.OperationDao;
import ar.edu.unq.desapp.grupoD.persistence.PettyCashAccountDao;

/**
 * @author JulianV
 */
public class OperationService {

	private OperationDao operationDao;
	private CategoryService categoryService;
	private SubCategoryService subCategoryService;
	private ConceptService conceptService;
	private PettyCashAccountDao pettyCashAccountDao;
	private BankAccountDao bankAccountDao;
	
	public void setOperationDao(OperationDao operationDao) {
		this.operationDao = operationDao;
	}
	
	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setConceptService(ConceptService conceptService) {
		this.conceptService = conceptService;
	}

	public void setPettyCashAccountDao(PettyCashAccountDao pettyCashAccountDao) {
		this.pettyCashAccountDao = pettyCashAccountDao;
	}
	
	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}

	@Transactional
	public void saveOperation(DateTime date, List<PaymentType> paymentTypes , boolean isIncome, String shift, String categoryName, String subCategoryName, String conceptName) throws InvalidAmountException {
		Category category = categoryService.findByName(categoryName);
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		Concept concept = conceptService.findByName(conceptName);
		
		double totalInPettyCash = pettyCashAccountDao.getAmount();
		double totalInBankAccount = bankAccountDao.getAmmount();
		
		//TODO esto es una negrada, lo se, pero quiero resolverlo rapido
		for( PaymentType paymentType : paymentTypes){
			if( paymentType instanceof PettyCash ){
				totalInPettyCash += paymentType.getAmount();
			}else{
				if( paymentType instanceof DebitCard ){
					System.out.println("if from debit card, amount is: " + paymentType.getAmount());
					totalInBankAccount += paymentType.getAmount();
				}
			}
		}
		
		pettyCashAccountDao.newAmmount(totalInPettyCash);
		bankAccountDao.newAmmount(totalInBankAccount);
		
		Operation operation = new Operation(date, paymentTypes, isIncome, shift, category, subCategory, concept, totalInPettyCash , totalInBankAccount);
		
		operationDao.save(operation);
	}

	@Transactional(readOnly=true)
	public List<Operation> findAll(){
		return operationDao.findAll();
	}
	
//	@Transactional
//	public void saveOperation(Operation operation) {
//		operationDao.save(operation);
//	}


}