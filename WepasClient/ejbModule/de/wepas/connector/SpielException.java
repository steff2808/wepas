package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SpielException extends Exception
{
	private static final long serialVersionUID = 1L;

	public SpielException(String msg)
	{
		super(msg);
	}
}
