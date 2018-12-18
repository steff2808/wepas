package de.wepas.connector;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ForumException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ForumException(String msg)
	{
		super(msg);
	}
}
