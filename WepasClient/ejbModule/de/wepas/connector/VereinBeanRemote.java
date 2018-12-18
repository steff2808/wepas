package de.wepas.connector;
import javax.ejb.Remote;

import de.wepas.jpa.EnVerein;

@Remote
public interface VereinBeanRemote
{
	public void addVerein(EnVerein verein) throws VereinException;
	
	public EnVerein[] getVerein() throws VereinException;
	
	public EnVerein getVerein(int vereinID) throws VereinException;
	
	public void changeVerein(EnVerein vereinNeu) throws VereinException;
	
	public void deleteVerein(int vereinID) throws VereinException;

}
