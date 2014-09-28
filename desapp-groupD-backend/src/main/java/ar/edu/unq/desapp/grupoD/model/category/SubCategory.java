package ar.edu.unq.desapp.grupoD.model.category;

/**
 * A Subcategory represents a more precise description within a category to
 * specify where the money is spent or where it is coming from. For example:
 * Income of U$$500 Category: Income, Subcategory: sells Outcome of U$$200
 * Category: Suppliers, Subcategory: Oreo
 * 
 * @author Lucas
 * 
 */
public class SubCategory {

	private String subcategoryName;
	private Concept concept;

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public SubCategory(String name){
		this.subcategoryName = name;
	}

}
