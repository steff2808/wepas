package de.wepas.worker;

import javax.ejb.Remote;

import de.wepas.jpa.EnSpiel;

@Remote
public interface ErgebnispflegeBeanRemote
{
	String maintenanceResult(EnSpiel spiel) throws ErgebnispflegeException;
}
