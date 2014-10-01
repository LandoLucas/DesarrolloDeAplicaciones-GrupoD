package ar.edu.unq.desapp.grupoD.persistence;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoD.model.category.Category;

public class GetCategoriesOperation implements TransactionalOperation {

	private List<Category> categories;
	private HibernateManager manager;
	
	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public void execute() {
		this.categories = new ArrayList<Category>();
		this.categories = manager.getSession().createQuery("FROM Category").list();
	}

	public void setManager(HibernateManager manager) {
		this.manager = manager;
	}

	public GetCategoriesOperation(){};
	
	
}
