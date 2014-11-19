package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.providers.Provider;
import ar.edu.unq.desapp.grupoD.services.ProviderService;

/**
 * REST service for Provider
 * 
 * @author JulianV
 */
@Service
@Path("/provider")
public class ProviderRest {
	private ProviderService providerService;
	
	public void setProviderService(ProviderService providerService){
		this.providerService = providerService;
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllProviders(){
		List<Provider> providers = providerService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(providers).build();
	}
	
	@POST
	@Path("/new")
	public Response addProvider(@FormParam("providerId") Integer providerId, @FormParam("name") String name,@FormParam("tradeName") String tradeName,
			@FormParam("direction") String direction, @FormParam("cuit") Integer cuit,
			@FormParam("telephone") Integer telephone){
		providerService.saveProvider(providerId, name, tradeName, direction, cuit, telephone);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/edit/{providerId}")
	public Response editProvider(@FormParam("providerId") Integer providerId, @FormParam("name") String name,@FormParam("tradeName") String tradeName,
			@FormParam("direction") String direction, @FormParam("cuit") Integer cuit,
			@FormParam("telephone") Integer telephone){
		providerService.saveProvider(providerId, name, tradeName, direction, cuit, telephone); 
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/{providerId}")
	@Produces("application/json")
	public Provider getProviderByProviderId(@PathParam("providerId") Integer id){
		return providerService.findByProviderId(id);
	}
}
