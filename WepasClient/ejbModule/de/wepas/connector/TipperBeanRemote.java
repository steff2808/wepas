package de.wepas.connector;
import javax.ejb.Remote;
import de.wepas.jpa.EnTipper;


@Remote
public interface TipperBeanRemote 
{
	public void addTipper(EnTipper tipper) throws TipperException;
	
	public EnTipper[] getTipper() throws TipperException;
	
	public EnTipper[] getTeamTipper(String team) throws TipperException;

	public EnTipper getTipper(int tipperID) throws TipperException;
	
	public void changeTipper(EnTipper tipperNeu) throws TipperException;
	
	public void deleteTipper(int tipperID) throws TipperException;

	public EnTipper getTipper(String tipperLogin) throws TipperException;
}
