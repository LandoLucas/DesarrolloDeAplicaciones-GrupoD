package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account { 

	public abstract void bill(Operation operation);
	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName();
	}

}

