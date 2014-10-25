package ar.edu.unq.desapp.grupoD.model.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

/**
 * A Concept brakes down the description of the operation to the lowest level, giving more detailed information of the operation soruce  
 * For example:
 * Income of U$$500 Category: Income, Subcategory: sells, Concept: 09/05 sells
 * Outcome of U$$200 Category: Suppliers, Subcategory: Oreo, Concept: cookies  
 * @author Lucas
 *
 */
@XmlRootElement(name = "concept")
@Entity
@Table(name="Concept")
@Proxy(lazy=true)
public class Concept {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String conceptName;

	public String getConceptName() {
		return conceptName;
	}

	public Concept(){};
	
	public Concept(String conceptName){
		this.conceptName = conceptName;
	}
	
}
