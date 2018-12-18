package de.wepas.webservice;

import javax.ejb.Remote;

@Remote
public interface SayhiRemote
{
	String sayHi(String name) throws Exception;
}
