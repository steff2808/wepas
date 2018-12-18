package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnTipper;

@Local
public interface TipperBeanLocal
{
	public EnTipper[] getTipper() throws TipperException;
	
	public EnTipper getTipper(int tipperID) throws TipperException;
	
	public void changeTipper(EnTipper tipperNeu) throws TipperException;
	
}
