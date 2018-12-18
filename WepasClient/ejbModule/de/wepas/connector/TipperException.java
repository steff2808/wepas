package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class TipperException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public TipperException(String msg)
	{
		super(msg);
	}
}
