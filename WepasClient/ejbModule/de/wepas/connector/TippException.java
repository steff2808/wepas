package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TippException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public TippException(String msg)
	{
		super(msg);
	}
}
