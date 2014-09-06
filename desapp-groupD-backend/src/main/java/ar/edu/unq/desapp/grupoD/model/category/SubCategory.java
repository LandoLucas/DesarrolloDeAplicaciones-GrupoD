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

	private static String subcategoryName;
	private static Concept concept;

	public static String getSubcategoryName() {
		return subcategoryName;
	}

	public static void setSubcategoryName(String subcategoryName) {
		SubCategory.subcategoryName = subcategoryName;
	}

	public static Concept getConcept() {
		return concept;
	}

	public static void setConcept(Concept concept) {
		SubCategory.concept = concept;
	}

}
