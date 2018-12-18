package de.wepas.webservice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Sayhi
 */
@Stateless
@LocalBean
public class Sayhi implements SayhiRemote
{
	
	public Sayhi()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHi(String name) throws Exception
	{
		return "Hi " + name + " from Wepas-Webservice!";
	}
	
}
