package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;

@Local
public interface SpielBeanLocal
{
	public EnSpiel[] getSpiele(EnSpieltag spieltag) throws SpielException;
	
	public void changeSpiel(EnSpiel spielNeu) throws SpielException;
	
	public EnSpiel getSpiel(int spielId) throws SpielException;

}
