package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
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
		Category category = getOrCreateCategory(categoryName);
		SubCategory subCategory = getOrCreateSubCategory(subCategoryName);
		Concept concept = getOrCreateConcept(conceptName);
	
		double totalInPettyCash = pettyCashAccountDao.getAmount();
		double totalInBankAccount = bankAccountDao.getAmmount();
		double available = bankAccountDao.getAvailableAmount();
		double devengado = bankAccountDao.getDevengado();
		boolean devengada = true;
		
		for( PaymentType paymentType : paymentTypes){
			totalInPettyCash += paymentType.getAmountInCash();
			totalInBankAccount += paymentType.getAmountInBank();
			available += paymentType.getAvailable();
			pettyCashAccountDao.newAmmount(totalInPettyCash);
			bankAccountDao.updateAvailable(available);
			devengado += paymentType.getDevengado();
			bankAccountDao.updateDevengado(devengado);
			devengada = paymentType.isDevengada();
			bankAccountDao.newAmmount(totalInBankAccount);
		}	
		
		Operation operation = new Operation(date, paymentTypes, isIncome, shift, category, subCategory, concept, totalInPettyCash , totalInBankAccount , available , devengado, devengada);
		operationDao.save(operation);
	}

	private Concept getOrCreateConcept(String conceptName) {
		Concept concept = conceptService.findByName(conceptName);
		if(concept == null)concept = new Concept(conceptName);
		return concept;
	}

	private SubCategory getOrCreateSubCategory(String subCategoryName) {
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		if(subCategory == null)subCategory = new SubCategory(subCategoryName);
		return subCategory;
	}

	private Category getOrCreateCategory(String categoryName) {
		Category category = categoryService.findByName(categoryName);
		if(category == null)category = new Category(categoryName);
		return category;
	}

	@Transactional(readOnly=true)
	public List<Operation> findAll(){
		return operationDao.findAll();
	}

	public BankAccount devengar() {
		List<Operation> operations = operationDao.findOperationsWithCreditCardDebts();
		double devengadoTotal = calculateDevengado(operations);
		
		bankAccountDao.devengar(devengadoTotal);
		
		markOperationsAsDevengadas(operations);
		
		return bankAccountDao.getAccount();
	}

	private void markOperationsAsDevengadas(List<Operation> operations) {
		for(Operation operation : operations){
			operation.setDevengada(true);
			operationDao.save(operation);
		}
	}

	private double calculateDevengado(List<Operation> operations) {
		double result = 0;
		for(Operation operation : operations){
			result += operation.getAmountInCreditCard();
		}
		return result;
	}

	@Transactional
	public List<Operation> findAllOutcomes() {
		return operationDao.findAllOutcomes();
	}

	@Transactional
	public void saveOperation(Operation operation) {
		operationDao.save(operation);
	}

	@Transactional
	public List<Operation> findAllOutcomesByShift(String shift) {
		return operationDao.findAllOutcomesByShift(shift);
	}
	

}