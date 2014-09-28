package ar.edu.unq.desapp.grupoD.model.category;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Category is used to indicate if the operation is an income or an outcome.
 * For example:
 * Income of U$$500 Category: Income
 * Outcome of U$$200 Category: Supplier
 * @author Lucas
 */
@XmlRootElement(name="category")
public class Category {

	private String categoryName;
	private SubCategory subcategory;
	
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
	
	public Category(String name){
		categoryName = name;
	}
}
