package ar.edu.unq.desapp.grupoD.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoD.exceptions.InvalidAmountException;
import ar.edu.unq.desapp.grupoD.exceptions.InvalidReceiptNumberException;
import ar.edu.unq.desapp.grupoD.model.account.Account;
import ar.edu.unq.desapp.grupoD.services.AccountService;

/**
 * 
 * @author eiroa
 *
 */
@Service
@Path("/account")
public class AccountRest {

	private AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllAccounts() throws InvalidAmountException, InvalidReceiptNumberException {
		List<Account> obs =  accountService.findAll();
		return Response.ok().header("Access-Control-Allow-Origin", "*").entity(obs).build();
	}
	


//	@POST
//	@Path("/delete")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response deleteAccount(@FormParam("name") String name) {
//		accountService.removeAccount(name);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}

//	@POST
//	@Path("/save")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response saveOrUpdateAccount(@FormParam("name") String name) {
//		Account account = new Account(name);
//		accountService.save(account);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}
	
//	@POST
//	@Path("/update")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response updateAccount(@FormParam("name") String name,@FormParam("idAccount") Integer idCat) {
//		accountService.update(name,idCat);
//		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
//	}

}
