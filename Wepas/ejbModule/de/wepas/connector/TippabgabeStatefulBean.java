package de.wepas.connector;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.counter.TippabgabeStatefulBeanCounter;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.util.LookupLocalService;

/**
 * Session Bean implementation class TippabgabeStatefulBean
 */
@Stateful
@StatefulTimeout(value=96,unit=TimeUnit.HOURS)
public class TippabgabeStatefulBean implements TippabgabeStatefulBeanRemote, Serializable
{
	private static final long serialVersionUID = 1078775370307940594L;
	private static Log log = LogFactory.getLog(TippabgabeStatefulBean.class);
	private int beanId;
	private String name;
	
	protected EnTipper tipper;
	protected EnSpieltag spieltag;
	protected Vector<EnSpiel> spiele;
	protected Vector<EnTipp> tipps;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = TippabgabeStatefulBeanCounter.construct(this);
	}
	
	@PrePassivate
	public void BeanPassivate()
	{
		TippabgabeStatefulBeanCounter.passivate(beanId, this);
	}
	
	@PostActivate
	public void BeanActivate()
	{
		TippabgabeStatefulBeanCounter.activate(beanId, this);
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		TippabgabeStatefulBeanCounter.destroy(beanId, name);
	}
	
	@Remove
	public void remove()
	{
//		log.info("---> TippabgabeStatefulBean.remove()");
	}
	
	public EnSpiel[] getSpiele()
	{
		EnSpiel[] spielarray = new EnSpiel[this.spiele.size()];
		Iterator<EnSpiel> iter2 = this.spiele.iterator();
		int i = 0;
		while (iter2.hasNext())
		{
			spielarray[i] = iter2.next();
			i++;
		}
		return spielarray;
	}
	
	public void setTipper(EnTipper tipper)
	{
		this.tipper = tipper;
		this.name = getTipper().getAtTipperVorname() + " " + getTipper().getAtTipperName();
	}
	
	public EnTipper getTipper()
	{
		return tipper;
	}
	
	public void setSpieltag(EnSpieltag spieltag)
	{
		this.spieltag = spieltag;
		SpielBeanLocal spielStateless = LookupLocalService.lookupSpielBeanLocal();
		try
		{
//			log.info("Tippabgabe für " + this.spieltag);
			EnSpiel[] spielarray = spielStateless.getSpiele(this.spieltag);
			this.spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			log.error("Exception in Zeile 118 " + e.getMessage());
		}
	}
	
	@Override
	public EnSpieltag getSpieltag()
	{
		return this.spieltag;
	}
	
	@Override
	public void addTipp(EnTipp tipp) throws TippException
	{
		manager.persist(tipp);
	}
}
