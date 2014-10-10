package ar.edu.unq.desapp.grupoD.persistence;

/**
 * This class is the responsible to populate the database with fake information
 * in order to test the application
 * 
 * @author Lucas
 */
public class DatabaseInitializer {

	private CategoryDao categoryDao;
	private SubcategoryDao subcategoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}

	public void populateDatabase() {
		loadCategories();
		
		System.out.println("==========================");
		System.out.println("POPULATED");
		System.out.println("==========================");
	}

	private void loadCategories() {
		System.out.println("Loaded categories");
	}

}
