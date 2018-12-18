package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class WetteException extends Exception
{
	private static final long serialVersionUID = 1L;

	public WetteException(String msg)
	{
		super(msg);
	}
}
