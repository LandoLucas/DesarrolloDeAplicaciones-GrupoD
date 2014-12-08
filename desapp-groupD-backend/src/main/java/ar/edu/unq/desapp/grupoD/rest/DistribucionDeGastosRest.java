package ar.edu.unq.desapp.grupoD.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.Operation;
import ar.edu.unq.desapp.grupoD.services.OperationService;

@Service
@Path("/gastos")
public class DistribucionDeGastosRest {

	private OperationService operationService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@GET
	@Path("/distribucionCategoria")
	@Produces("application/json")
	public Response distribucionDeGastosCategoria() {
		
		List<Operation> gastos = operationService.findAllOutcomes();
		Map<String , String> distribucionDeGastos = new HashMap<String, String>();
		
		for(Operation operation : gastos){
			Double amount =  operation.getTotalAmount();
			String category = operation.getCategory();
			
			distribucionDeGastos.put( category , amount.toString() );
		}
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(distribucionDeGastos).build();
	}
	
	@POST
	@Path("/distribucionTurno")
	@Produces("application/json")
	@Consumes({"application/json" , "application/x-www-form-urlencoded"})
	public Response distribucionDeGastosTurno(@FormParam("shift") String shift) {
		
		List<Operation> outcomes = operationService.findAllOutcomesByShift(shift);
		Map<String , String> outcomesByShift = new HashMap<String, String>();
		
		for(Operation operation : outcomes){
			Double amount =  operation.getTotalAmount();
			String category = operation.getCategory();
			
			outcomesByShift.put( category , amount.toString() );
		}
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(outcomesByShift).build();
	}
}
