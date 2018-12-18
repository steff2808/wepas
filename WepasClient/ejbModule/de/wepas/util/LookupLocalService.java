package de.wepas.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import de.wepas.connector.EinstellungBeanLocal;
import de.wepas.connector.SpielBeanLocal;
import de.wepas.connector.SpieltagBeanLocal;
import de.wepas.connector.TippBeanLocal;
import de.wepas.connector.TipperBeanLocal;
import de.wepas.connector.VereinBeanLocal;
import de.wepas.connector.WetteBeanLocal;
import de.wepas.constants.ApplConstants;
import de.wepas.livegoals.LivegoalsBeanLocal;
import de.wepas.worker.ErgebnispflegeBeanLocal;

public class LookupLocalService
{
	public static EinstellungBeanLocal lookupEinstellungBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIEINSTELLUNGBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (EinstellungBeanLocal) PortableRemoteObject.narrow(ref, EinstellungBeanLocal.class);
	}
	
	public static SpielBeanLocal lookupSpielBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDISPIELBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (SpielBeanLocal) PortableRemoteObject.narrow(ref, SpielBeanLocal.class);
	}
	
	public static VereinBeanLocal lookupVereinBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIVEREINBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (VereinBeanLocal) PortableRemoteObject.narrow(ref, VereinBeanLocal.class);
	}
	
	public static LivegoalsBeanLocal lookupLivegoalsBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDILIVEGOALSBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (LivegoalsBeanLocal) PortableRemoteObject.narrow(ref, LivegoalsBeanLocal.class);
	}
	
	public static SpieltagBeanLocal lookupSpieltagBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (SpieltagBeanLocal) PortableRemoteObject.narrow(ref, SpieltagBeanLocal.class);
	}
	
	public static TipperBeanLocal lookupTipperBeanLocal() 
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPERBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (TipperBeanLocal) PortableRemoteObject.narrow(ref, TipperBeanLocal.class);
	}
	
	public static TippBeanLocal lookupTippBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (TippBeanLocal) PortableRemoteObject.narrow(ref, TippBeanLocal.class);
	}
	
	public static WetteBeanLocal lookupWetteBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIWETTEBEANLOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (WetteBeanLocal) PortableRemoteObject.narrow(ref, WetteBeanLocal.class);
	}
	
	public static ErgebnispflegeBeanLocal lookupErgebnispflegeBeanLocal()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIERGEBNISPFLEGELOCAL);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (ErgebnispflegeBeanLocal) PortableRemoteObject.narrow(ref, ErgebnispflegeBeanLocal.class);
	}
	
}
