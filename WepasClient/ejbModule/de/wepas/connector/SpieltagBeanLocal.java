package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnSpieltag;

@Local
public interface SpieltagBeanLocal
{
	
	public EnSpieltag getSpieltag(int spieltagID) throws SpieltagException;
	
	public EnSpieltag getCurrentSpieltag() throws SpieltagException;
	
	public EnSpieltag getNextSpieltag() throws SpieltagException;
	
	public void changeSpieltag(EnSpieltag spieltagNeu) throws SpieltagException;
	
}
