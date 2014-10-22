package ar.edu.unq.desapp.grupoD.model.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(unique=false)
	private String categoryName;

	@OneToMany(cascade = CascadeType.ALL)
	private List<SubCategory> subcategory;

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public List<SubCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

	public Category(String name) {
		categoryName = name;
	}
	
	public Category(){}
}
