package de.wepas.connector;

import javax.ejb.Remote;

import de.wepas.jpa.EnEinstellung;

@Remote
public interface EinstellungBeanRemote
{
	EnEinstellung[] getEinstellung(String schluessel) throws EinstellungException;

	EnEinstellung getEinstellung(int einstellungId) throws EinstellungException;
}
