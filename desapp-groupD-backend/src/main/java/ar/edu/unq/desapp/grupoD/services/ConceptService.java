package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;
import ar.edu.unq.desapp.grupoD.persistence.ConceptDao;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

public class ConceptService {
	
	private CategoryDao categoryDao;
	private SubcategoryDao subcategoryDao;
	private ConceptDao conceptDao;
	
	
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}


	public void setConceptDao(ConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}

	@Transactional(readOnly=true)
	public List<Concept> findAll() {
		return conceptDao.findAll();
	}

	@Transactional
	public void removeConceptByName(String name,Integer idCat, Integer idSub) throws Exception {
		Category target = categoryDao.findById(idCat);
		SubCategory subTarget = subcategoryDao.findById(idSub);
		List<SubCategory> subs = target.getSubcategory();
		List<Concept> cons = subTarget.getConcepts();
		if(cons.remove(conceptDao.getByName(name))){
			subTarget.setConcepts(cons);
		}else{
			throw new Exception("Removing concept failed");
		}
		subcategoryDao.save(subTarget);
	}

	@Transactional
	public void save(Concept concept, Integer idSubcategory) {
		SubCategory toUpdate = subcategoryDao.findById(idSubcategory);
		List<Concept> concepts = toUpdate.getConcepts();
		concepts.add(concept);
		subcategoryDao.save(toUpdate);
	}
	
	@Transactional
	public void update(String name, Integer idCon) {
		this.conceptDao.update(name, idCon);
	}

}
