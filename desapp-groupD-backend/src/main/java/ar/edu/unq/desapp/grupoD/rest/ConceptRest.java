package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.services.ConceptService;

/**
 * REST service for Concepts
 * @author Mauro
 */
@Service
@Path("/concept")
public class ConceptRest {
	
	private ConceptService conceptService;
	

	public void setConceptService(ConceptService conceptService) {
		this.conceptService = conceptService;
	}

	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllConcepts() {
		List<Concept>obs = conceptService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}

	@POST
	@Path("/delete")
	@Consumes("application/x-www-form-urlencoded")
	public Response deleteConcept(@FormParam("name") String name,@FormParam("idCategory") Integer idCat,
			@FormParam("idSubcategory") Integer idSub) throws Exception {
		conceptService.removeConceptByName(name,idCat,idSub);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateSubcategory(@FormParam("name") String name, 
			@FormParam("idSubcategory") Integer idSubcategory) {
		Concept concept= new Concept(name);
		conceptService.save(concept, idSubcategory);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/update")
	@Consumes("application/x-www-form-urlencoded")
	public Response updateConcept(@FormParam("name") String name, 
			@FormParam("idConcept") Integer idCon) {
		conceptService.update(name, idCon);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
}
