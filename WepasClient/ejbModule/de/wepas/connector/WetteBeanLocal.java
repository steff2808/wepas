package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;

@Local
public interface WetteBeanLocal
{
	void addWette(EnWette[] wette) throws WetteException;
	
	EnWette[] getWette() throws WetteException;
	
	EnWette[] getSieger() throws WetteException;
	
	EnWette[] getGewinner() throws WetteException;
	
	EnWette[] getWetten(EnTipper tipper) throws WetteException;
	
	EnWette[] getWetten(EnSpieltag spieltag) throws WetteException;
	
	EnWette getWette(EnTipper tipper, EnSpieltag spieltag) throws WetteException;
	
	void changeWette(EnWette wetteNeu) throws WetteException;
}
