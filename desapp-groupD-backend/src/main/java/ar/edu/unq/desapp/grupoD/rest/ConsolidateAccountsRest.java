package ar.edu.unq.desapp.grupoD.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.model.account.BankAccount;
import ar.edu.unq.desapp.grupoD.services.ConsolidateAccountsService;

@Service
@Path("/consolidate")
public class ConsolidateAccountsRest {

	private ConsolidateAccountsService consolidateAccountsService;
	
	public void setConsolidateAccountsService(ConsolidateAccountsService consolidateAccountsService) {
		this.consolidateAccountsService = consolidateAccountsService;
	}

	@GET
	@Path("/consolidate")
	@Produces("application/json")
	public Response consolidateAccounts() {
		BankAccount account = consolidateAccountsService.consolidateAccounts();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(account).build();
	}
	
}
