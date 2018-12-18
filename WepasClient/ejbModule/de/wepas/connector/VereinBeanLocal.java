package de.wepas.connector;

import javax.ejb.Local;

import de.wepas.jpa.EnVerein;

@Local
public interface VereinBeanLocal
{
	public EnVerein[] getVerein() throws VereinException;
	
	public EnVerein getVerein(int vereinID) throws VereinException;
	
	public void changeVerein(EnVerein vereinNeu) throws VereinException;
}
