package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SpieltagException extends Exception
{
	private static final long serialVersionUID = 1L;

	public SpieltagException(String msg)
	{
		super(msg);
	}
}
