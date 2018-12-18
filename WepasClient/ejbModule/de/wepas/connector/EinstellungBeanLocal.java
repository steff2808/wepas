package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnEinstellung;

@Local
public interface EinstellungBeanLocal
{
	EnEinstellung[] getEinstellung(String schluessel) throws EinstellungException;

	EnEinstellung getEinstellung(int einstellungId) throws EinstellungException;
}
