package de.wepas.worker;

import javax.ejb.Local;

import de.wepas.jpa.EnSpiel;

@Local
public interface ErgebnispflegeBeanLocal
{
	String maintenanceResult(EnSpiel spiel) throws ErgebnispflegeException;
}
