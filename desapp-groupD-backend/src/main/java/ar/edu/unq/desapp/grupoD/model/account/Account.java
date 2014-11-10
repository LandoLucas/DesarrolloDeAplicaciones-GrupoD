package ar.edu.unq.desapp.grupoD.model.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ar.edu.unq.desapp.grupoD.model.Operation;

/**
 * @author JulianV
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account { 

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	public abstract void bill(Operation operation);

}

