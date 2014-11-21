package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author JulianV
 *
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account { 

//	public abstract void bill(Operation operation);
	
}

