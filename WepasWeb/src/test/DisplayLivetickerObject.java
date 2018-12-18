package test;

import java.text.DateFormat;
import java.util.Hashtable;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import de.wepas.constants.ApplConstants;
import de.wepas.livegoals.Livespiel;
import de.wepas.livegoals.LivegoalsBeanRemote;
import de.wepas.livegoals.LivegoalsObject;


public class DisplayLivetickerObject
{
	public static void main(String[] args)
	{
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Object ref = null;
		try
		{
			final Context ctx = new InitialContext(jndiProperties);
			ref = ctx.lookup(ApplConstants.JNDILIVEGOALSBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		LivegoalsBeanRemote lbr  = (LivegoalsBeanRemote) PortableRemoteObject.narrow(ref, LivegoalsBeanRemote.class);
		LivegoalsObject lgo = lbr.getLivegoalsObject();
		if(lgo == null)
		{
			System.out.println("Kein LivegoalsObject vorhanden");
		}
		else
		{
			System.out.println("--- A K T U E L L -----");
			Iterator<Livespiel> iter1 = lgo.getLivespiele().iterator();
			while(iter1.hasNext())
			{
				System.out.println(iter1.next().getTitle());
			}
			System.out.println("--- H I S T O R I E ---");
			Iterator<Livespiel> iter2 = lgo.getLivehistorie().iterator();
			while(iter2.hasNext())
			{
				DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
				DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
				
				Livespiel lg = iter2.next();

				String ts = dateFormat.format(lg.getTimestamp().getTime())
						+ " um " + timeFormat.format(lg.getTimestamp().getTime()) + " Uhr : ";
				System.out.println( ts + lg.getTitle());
			}
			
		}
	}
}
