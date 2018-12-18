package de.wepas.worker;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ErgebnispflegeException extends Exception
{
	private static final long serialVersionUID = 941036882994495178L;

	public ErgebnispflegeException(String msg)
	{
		super(msg);
	}
}
