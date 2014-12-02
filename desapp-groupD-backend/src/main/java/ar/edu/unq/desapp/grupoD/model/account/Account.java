package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author JulianV
 *
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account { 
	public String getName(){
		return this.getClass().getSimpleName();
	}
	
	public Boolean isPettyCashAccount(){
		return false;
	}
	
	public Boolean isDebitAccount(){
		return false;
	}
	
	public Boolean isBankAccount(){
		return false;
	}
}

