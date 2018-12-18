package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.ForumBeanRemote;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippabgabeStatefulBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.livegoals.LivegoalsBeanRemote;
import de.wepas.worker.ErgebnispflegeBeanRemote;

//java:global/WepasEAR/Wepas/SpielBean!de.wepas.connector.SpielBeanRemote
//java:app/Wepas/SpielBean!de.wepas.connector.SpielBeanRemote
//java:module/SpielBean!de.wepas.connector.SpielBeanRemote
//java:jboss/exported/WepasEAR/Wepas/SpielBean!de.wepas.connector.SpielBeanRemote


public class LookupRemoteService
{
	public static void main(String[] args)
	{
		System.out.println("Huhu");
		TipperBeanRemote tbr = LookupRemoteService.lookupTipperBeanRemote();
		try
		{
			System.out.println(tbr.getTipper(1).getAtTipperName());
		}
		catch (TipperException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SpielBeanRemote lookupSpielBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDISPIELBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (SpielBeanRemote) PortableRemoteObject.narrow(ref, SpielBeanRemote.class);
	}
	
	public static SpieltagBeanRemote lookupSpieltagBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDISPIELTAGBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (SpieltagBeanRemote) PortableRemoteObject.narrow(ref, SpieltagBeanRemote.class);
	}
	
	public static TippBeanRemote lookupTippBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (TippBeanRemote) PortableRemoteObject.narrow(ref, TippBeanRemote.class);
	}
	
	public static TipperBeanRemote lookupTipperBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPERBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (TipperBeanRemote) PortableRemoteObject.narrow(ref, TipperBeanRemote.class);
	}
	
	public static VereinBeanRemote lookupVereinBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIVEREINBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (VereinBeanRemote) PortableRemoteObject.narrow(ref, VereinBeanRemote.class);
	}
	
	public static ForumBeanRemote lookupForumBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIFORUMBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (ForumBeanRemote) PortableRemoteObject.narrow(ref, ForumBeanRemote.class);
	}
	
	public static EinstellungBeanRemote lookupEinstellungBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIEINSTELLUNGBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (EinstellungBeanRemote) PortableRemoteObject.narrow(ref, EinstellungBeanRemote.class);
	}
	
	public static WetteBeanRemote lookupWetteBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIWETTEBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (WetteBeanRemote) PortableRemoteObject.narrow(ref, WetteBeanRemote.class);
	}
	
	public static LivegoalsBeanRemote lookupLivegoalsSingletonRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDILIVEGOALSBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (LivegoalsBeanRemote) PortableRemoteObject.narrow(ref, LivegoalsBeanRemote.class);
	}
	
	public static TippabgabeStatefulBeanRemote lookupTippabgabeStatefulRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPABGABEBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (TippabgabeStatefulBeanRemote) PortableRemoteObject.narrow(ref, TippabgabeStatefulBeanRemote.class);
	}
	
	public static ErgebnispflegeBeanRemote lookupErgebnispflegeRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDIERGEBNISPFLEGEBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (ErgebnispflegeBeanRemote) PortableRemoteObject.narrow(ref, ErgebnispflegeBeanRemote.class);
	}
	
	public static LivegoalsBeanRemote lookupLivegoalsBeanRemote()
	{
		Object ref = null;
		try
		{
			Context ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDILIVEGOALSBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		return (LivegoalsBeanRemote) PortableRemoteObject.narrow(ref, LivegoalsBeanRemote.class);
	}
}
