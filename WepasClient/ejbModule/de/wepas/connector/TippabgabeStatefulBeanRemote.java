package de.wepas.connector;

import javax.ejb.Remote;

import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

@Remote
public interface TippabgabeStatefulBeanRemote
{
//	public EnTipp[] getTipps();
	
	public EnSpiel[] getSpiele();
	
	public void setTipper(EnTipper tipper);
	
	public EnTipper getTipper();
	
	public void setSpieltag(EnSpieltag spieltag);
	
	public EnSpieltag getSpieltag();
	
	public void remove();
	
	public void addTipp(EnTipp tipp) throws TippException;
}
