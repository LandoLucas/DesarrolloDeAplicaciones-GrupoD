package ar.edu.unq.desapp.grupoD.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;
import ar.edu.unq.desapp.grupoD.persistence.OperationDao;

/**
 * @author JulianV
 */
public class OperationService {

	private OperationDao operationDao;
	private CategoryService categoryService;
	private SubCategoryService subCategoryService;
	
	public void setOperationDao(OperationDao operationDao) {
		this.operationDao = operationDao;
	}
	
	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Transactional
	public Operation getOperationByID(int id) {
		return operationDao.getOperationById(id);
	}

	@Transactional
	public void removeOperationByID(int id) {
		operationDao.deleteOperationByID(id);
	}

	@Transactional
	public void saveOperation(DateTime date, double amount, boolean isIncome, String shift, String categoryName, String subCategoryName,
			String conceptName, PaymentType paymentType) throws InvalidAmountException {
		
		Category category = categoryService.findByName(categoryName);
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		Concept concept = new Concept(conceptName);
				
		Operation operation = new Operation(date, amount, isIncome, shift, category, subCategory, concept, paymentType);
		
		operationDao.save(operation);
	}

	@Transactional
	public void saveOperation(int id, DateTime date, double amount, boolean isIncome, String shift, String categoryName,
			String subCategoryName, String conceptName, PaymentType paymentType) throws InvalidAmountException {

		Category category = categoryService.findByName(categoryName);
		SubCategory subCategory = subCategoryService.findByName(subCategoryName);
		Concept concept = new Concept(conceptName);
		
		Operation operation = getOperationByID(id);
		operation.setDate(date);
		operation.setAmount(amount);
		operation.setIncome(isIncome);
		List<Concept> concepts = new ArrayList<Concept>();
		concepts.add(concept);
		subCategory.setConcepts(concepts);
		List<SubCategory> subcategories = new ArrayList<SubCategory>();
		subcategories.add(subCategory);
		category.setSubcategory(subcategories);
		operation.setCategory(category);
		operation.setPaymentType(paymentType);
		
		operationDao.save(operation);
	}

	@Transactional
	public void saveOperation(Operation operation) {
		operationDao.save(operation);
	}


}