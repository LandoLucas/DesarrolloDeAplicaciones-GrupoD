package ar.edu.unq.desapp.grupoD.rest;

import static org.mockito.Matchers.doubleThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
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
	@Path("/distribucion")
	@Produces("application/json")
	public Response consolidateAccounts() {
		
		List<Operation> gastos = operationService.findAllOutcomes();
		Map<String , String> distribucionDeGastos = new HashMap<String, String>();
		
		for(Operation operation : gastos){
			Double amount =  operation.getTotalAmount();
			String category = operation.getCategory();
			
//			if(distribucionDeGastos.containsKey(category))	amount += Double.parseDouble( distribucionDeGastos.get(category) );
			
			
			distribucionDeGastos.put( "label" , category );
			distribucionDeGastos.put( "value" , amount.toString() );
			
		}
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(distribucionDeGastos).build();
	}
	
}
