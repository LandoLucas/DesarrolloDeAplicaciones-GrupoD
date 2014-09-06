package ar.edu.unq.desapp.grupoD.model.category;

/**
 * A Category is used to indicate if the operation is an income or an outcome.
 * For example:
 * Income of U$$500 Category: Income
 * Outcome of U$$200 Category: Supplier
 * @author Lucas
 */
public class Category {

	private static String categoryName;
	private static SubCategory subcategory;
	
	public static String getCategoryName() {
		return categoryName;
	}
	public static void setCategoryName(String categoryName) {
		Category.categoryName = categoryName;
	}
	public static SubCategory getSubcategory() {
		return subcategory;
	}
	public static void setSubcategory(SubCategory subcategory) {
		Category.subcategory = subcategory;
	}
	
}
