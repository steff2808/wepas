package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EinstellungException extends Exception
{
	private static final long serialVersionUID = 1L;

	public EinstellungException(String msg)
	{
		super(msg);
	}
}
