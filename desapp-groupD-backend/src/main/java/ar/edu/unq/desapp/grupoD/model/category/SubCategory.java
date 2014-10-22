package ar.edu.unq.desapp.grupoD.model.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * A Subcategory represents a more precise description within a category to
 * specify where the money is spent or where it is coming from. For example:
 * Income of U$$500 Category: Income, Subcategory: sells Outcome of U$$200
 * Category: Suppliers, Subcategory: Oreo
 * 
 * @author Lucas
 * 
 */
@Entity
@Table(name="SubCategory")
@Proxy(lazy=true)
public class SubCategory {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String subcategoryName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Concept> concepts;

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public SubCategory(String name ){
		this.subcategoryName = name;
	}
	
	public List<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

	public SubCategory(){}

	public Integer getId() {
		return id;
	}

	
}
