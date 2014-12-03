package ar.edu.unq.desapp.grupoD.transaction;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;

public class CategoryBrokenDao extends CategoryDao{

	@Override
	public void save(Category entity) {
		super.save(entity);
		throw new RuntimeException("Where is your god now?");
	}

	
}
