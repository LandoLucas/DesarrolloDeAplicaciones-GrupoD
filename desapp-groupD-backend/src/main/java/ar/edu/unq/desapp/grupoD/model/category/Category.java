package ar.edu.unq.desapp.grupoD.model.category;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

/**
 * A Category is used to indicate if the operation is an income or an outcome.
 * For example: Income of U$$500 Category: Income Outcome of U$$200 Category:
 * Supplier
 * 
 * @author Lucas
 */
@XmlRootElement(name = "category")
@Entity
@Table(name = "Category")
@Proxy(lazy=true)
public class Category {

	@Id
	@GeneratedValue
	private Integer id;

	private String categoryName;

	@OneToOne(cascade = CascadeType.ALL)
	private SubCategory subcategory;

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category(String name , SubCategory subCategory) {
		categoryName = name;
		this.subcategory = subCategory;
	}
	
	public Category(String name) {
		categoryName = name;
	}
	
	public Category(){}
}
