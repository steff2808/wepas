package de.wepas.connector;

import javax.ejb.Remote;

import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;

@Remote
public interface SpielBeanRemote
{
//	public void addSpiel(Vector<EnSpiel> spiele) throws SpielException;

	public void addSpiel(EnSpiel spiel) throws SpielException;
	
	public EnSpiel[] getSpiele() throws SpielException;
	
	public EnSpiel[] getSpiele(EnSpieltag spieltag) throws SpielException;
	
	public EnSpiel getSpiel(int spielId) throws SpielException;
	
	public void changeSpiel(EnSpiel spielNeu) throws SpielException;
	
	public void deleteSpiele(EnSpieltag spieltag) throws SpielException;

	public EnSpiel[] getSpieleOffen() throws SpielException;
}
