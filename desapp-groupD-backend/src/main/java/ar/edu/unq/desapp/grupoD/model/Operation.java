package ar.edu.unq.desapp.grupoD.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
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
	
//	private int operationID;
	
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

	@OneToMany(cascade = CascadeType.ALL)
	private List<PaymentType> paymentTypes;
	
//	private static int next_operation_id = 1;
	/**
	 * Returns an instance of a money operation and it saves the transaction details.
	 * It is intended to be used when the transaction has already been done.  
	 * If you need to set an operation ID, use {@link #setOperationID(int)} to set it
	 * @param date
	 *            the date of the operation
	 * @param amountInCash
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
	public Operation(DateTime date, List<PaymentType> paymentTypes , boolean isIncome, String shift, Category category, SubCategory subCategory, Concept concept){
		setDate(date);
		setPaymentTypes(paymentTypes);
		setIncome(isIncome);
		setShift(shift);
		setCategory(category);
		setSubcategory(subCategory);
		setConcept(concept);
//		setOperationID();
	}
	
//	/**
//	 * Intended to be used as an adjustment operation where you need to correct the balance of one of the accounts.
//	 * It is like a normal operation but it lacks some information such as the shift and it needs to know which account needs to be adjusted.
//	 */
//	public Operation(DateTime date, double amount, boolean isIncome,
//			 Category category, Account account) throws InvalidAmountException {
//		this.setDate(date);
//		this.setIncome(isIncome);
//		this.setCategory(category);
//		
//		setOperationID();
//		account.bill( this);
//	}
	
	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
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

//	public int getOperationID() {
//		return operationID;
//	}

//	/**
//	 * Sets automatically the operation ID
//	 */
//	private synchronized void setOperationID(){
//		this.operationID = next_operation_id;
//		next_operation_id = next_operation_id + 1;
//		//TODO once we have the database we might want to consider to retrieve the last operation ID from it since any restart will make the counter to reset.
//	}
//	
//	/**
//	 * To be used ONLY for unit test
//	 */
//	public static void resetCounter(){
//		next_operation_id = 1;
//	}

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

//	public Operation(DateTime date, int operationID, boolean isIncome, String shift, Category category,
//					 SubCategory subcategory, Concept concept) throws InvalidAmountException {
//		this.date = date;
//		this.operationID = operationID;
//		this.isIncome = isIncome;
//		this.shift = shift;
//		this.category = category;
//		this.subcategory = subcategory;
//		this.concept = concept;
//	}

	/**
	 * Needed for frameworks - Do not delete.
	 */
	public Operation(){
	}
}
