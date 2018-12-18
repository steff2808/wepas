package de.wepas.admin;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import de.wepas.livegoals.LivegoalsBeanRemote;

public class StartLifegoalsClient
{
	public static void main(String[] args)
	{
		try
		{
			Properties p = new Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
			p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
			Context ctx = new InitialContext(p);
			Object ref = ctx.lookup("WepasEAR/Lifegoals/remote");
			LivegoalsBeanRemote lg = (LivegoalsBeanRemote) PortableRemoteObject.narrow(ref, LivegoalsBeanRemote.class);
//			lg.startLifegoals();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
}
