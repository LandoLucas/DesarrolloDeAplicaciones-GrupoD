package ar.edu.unq.desapp.grupoD.model.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * A Concept brakes down the description of the operation to the lowest level, giving more detailed information of the operation soruce  
 * For example:
 * Income of U$$500 Category: Income, Subcategory: sells, Concept: 09/05 sells
 * Outcome of U$$200 Category: Suppliers, Subcategory: Oreo, Concept: cookies  
 * @author Lucas
 *
 */
@Entity
@Table(name="Concept")
@Proxy(lazy=true)
public class Concept {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String conceptName;

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public Concept(){};
	
	public Concept(String conceptName){
		this.conceptName = conceptName;
	}
	
}
