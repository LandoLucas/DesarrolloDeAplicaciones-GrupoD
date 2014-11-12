package ar.edu.unq.desapp.grupoD.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.model.payment.PaymentType;

@XmlRootElement(name = "operation")
@Entity
@Table(name = "Operation")
public class Operation {

	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private int operationID;
	
	@Column
	private double amount;
	@Column
	private boolean isIncome;
	
	@Column
	private String shift;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SubCategory subcategory;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Concept concept;

	@OneToOne(cascade = CascadeType.ALL)
	private PaymentType paymentType;
	
	private static int next_operation_id = 1;
	/**
	 * Returns an instance of a money operation and it saves the transaction details.
	 * It is intended to be used when the transaction has already been done.  
	 * If you need to set an operation ID, use {@link #setOperationID(int)} to set it
	 * @param date
	 *            the date of the operation
	 * @param amount
	 *            amount of the operation in users currency
	 * @param isIncome
	 *            true if it's an income, false if it's an outcome of money
	 * @param shift
	 *            shift when the operation was made
	 * @param category
	 *            used to identify if it's income or outcome
	 * @param paymentType
	 *            how the operation was payed
	 * @throws InvalidAmountException if the amount is equal or below 0
	 */
	public Operation(DateTime date, double amount, boolean isIncome, String shift,
			Category category, SubCategory subCategory, Concept concept, PaymentType paymentType) throws InvalidAmountException {
		this.setDate(date);
		this.setAmount(amount);
		this.setIncome(isIncome);
		this.setShift(shift);
		this.setCategory(category);
		this.setSubcategory(subCategory);
		this.setConcept(concept);
		this.setPaymentType(paymentType);
		setOperationID();
		this.bill();
	}
	
	/**
	 * Intended to be used as an adjustment operation where you need to correct the balance of one of the accounts.
	 * It is like a normal operation but it lacks some information such as the shift and it needs to know which account needs to be adjusted.
	 */
	public Operation(DateTime date, double amount, boolean isIncome,
			 Category category, Account account) throws InvalidAmountException {
		this.setDate(date);
		this.setAmount(amount);
		this.setIncome(isIncome);
		this.setCategory(category);
		
		setOperationID();
		account.bill( this);
	}
	
	
	
	public Integer getId() {
		return id;
	}

	@XmlTransient
	public DateTime getDate() {
		return date;
	}
	
	public String getParsedDate(){
		return this.getDate().toString("YYYY-MM-dd");
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public int getOperationID() {
		return operationID;
	}

	/**
	 * Sets automatically the operation ID
	 */
	private synchronized void setOperationID(){
		this.operationID = next_operation_id;
		next_operation_id = next_operation_id + 1;
		//TODO once we have the database we might want to consider to retrieve the last operation ID from it since any restart will make the counter to reset.
	}
	
	/**
	 * To be used ONLY for unit test
	 */
	public static void resetCounter(){
		next_operation_id = 1;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) throws InvalidAmountException {
		if( amount <= 0) throw new InvalidAmountException();
		this.amount = amount;
	}

	public boolean isIncome() {
		return isIncome;
	}

	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getCategory() {
		return category.getCategoryName();
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PaymentType returnPaymentType(){
		return this.paymentType;
	}
	

	public String getPaymentType(){
		String result;
		if(paymentType != null){
			result =this.paymentType.getClass().getSimpleName();
		}else{
			result = "No payment Type";
		}
		return result;
	}
	
	public Integer getPaymentCode(){
		switch (getPaymentType()) {
		case "PettyCash":
			return 0;
		case "BankTransfer":
			return 2;
		case "CreditCard":
			return 1;
		default:
			return -1;
		}
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	public String getSubcategory() {
		String result;
		if(subcategory != null){
			result =this.subcategory.getSubcategoryName();
		}else{
			result = "No Subcategory";
		}
		return result;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	
	public String getConcept() {
		String result;
		if(concept != null){
			result =this.concept.getConceptName();
		}else{
			result = "No Concept";
		}
		return result;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	/**
	 * Delegates the billing to the correspondent payment type.
	 */
	private void bill() {
		this.paymentType.bill(this);
	}
	
	
	
	public Operation(){
	}
}
