package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class VereinException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public VereinException(String msg)
	{
		super(msg);
	}
}
