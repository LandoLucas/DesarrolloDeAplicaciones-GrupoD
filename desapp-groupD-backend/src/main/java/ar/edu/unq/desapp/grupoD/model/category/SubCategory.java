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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * A Subcategory represents a more precise description within a category to
 * specify where the money is spent or where it is coming from. For example:
 * Income of U$$500 Category: Income, Subcategory: sells Outcome of U$$200
 * Category: Suppliers, Subcategory: Oreo
 * 
 * @author Lucas
 * 
 */
@XmlRootElement(name = "subcategory")
@Entity
@Table(name="SubCategory")
public class SubCategory {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private String subcategoryName;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Concept> concepts;

	public String getSubcategoryName() {
		return subcategoryName;
	}
	

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
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
