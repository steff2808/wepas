package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

@Local
public interface TippBeanLocal
{

	public EnTipp[] getTipps(EnSpiel spiel) throws TippException;
	
	public EnTipp[] getTipps(EnTipper tipper, EnSpieltag spieltag) throws TippException;
	
	public void changeTipp(EnTipp tippNeu) throws TippException;
	
}
