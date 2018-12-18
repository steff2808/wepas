package de.wepas.connector;

import javax.ejb.Remote;

import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;

@Remote
public interface SpieltagBeanRemote
{
	
	public EnSpieltag[] getSpieltag() throws SpieltagException;
	
	public EnSpieltag getSpieltag(int spieltagID) throws SpieltagException;
	
	public EnSpieltag getCurrentSpieltag() throws SpieltagException;
	
	public EnSpieltag getNextSpieltag() throws SpieltagException;
	
	public EnSpieltag getNextzuTippenDutySpieltag(EnTipper tipper) throws SpieltagException;

	public EnSpieltag getNextzuTippenFreeSpieltag(EnTipper tipper) throws SpieltagException;
	
	public void addSpieltag(EnSpieltag spieltag) throws SpieltagException;

	public void changeSpieltag(EnSpieltag spieltagNeu) throws SpieltagException;
	
	public void deleteSpieltag(int spieltagID) throws SpieltagException;

}
