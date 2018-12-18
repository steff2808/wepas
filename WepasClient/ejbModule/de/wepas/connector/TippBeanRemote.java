package de.wepas.connector;

import javax.ejb.Remote;

import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

@Remote
public interface TippBeanRemote
{
	public void addTipp(EnTipp spiel) throws SpielException, TippException;
	
	public EnTipp[] getTipps() throws TippException;
	
	public EnTipp[] getSupertreffer() throws TippException;
	
	public EnTipp[] getTreffer() throws TippException;
	
	public EnTipp[] getBlinde() throws TippException;
	
	public EnTipp[] getTipps(EnSpieltag spieltag) throws TippException;

	public EnTipp getTipps(EnTipper tipper, EnSpiel spiel) throws TippException;

	public EnTipp[] getTipps(EnTipper tipper, EnSpieltag spieltag) throws TippException;

	public EnTipp[] getTipps(EnTipper tipper) throws TippException;
	
	public EnTipp[] getStatistikTipps(EnTipper tipper) throws TippException;
	
	public EnTipp[] getTipps(EnSpiel spiel) throws TippException;
	
	public EnTipp getTipp(int tippId) throws TippException;
	
	public void changeTipp(EnTipp tippNeu) throws TippException;

}
