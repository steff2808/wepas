package de.wepas.connector;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.wepas.counter.SpieltagBeanCounter;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.util.LookupLocalService;

@Stateless
public class SpieltagBean implements SpieltagBeanRemote, SpieltagBeanLocal, Serializable
{
	private static final long serialVersionUID = -1874784164703287744L;
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = SpieltagBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		SpieltagBeanCounter.destroy(beanId);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnSpieltag[] getSpieltag() throws SpieltagException
	{
		List<?> liste = manager.createNamedQuery("EnSpieltag.findAll").getResultList();
		Vector<EnSpieltag> erg = new Vector<EnSpieltag>();
		for (Object o : liste)
		{
			erg.add((EnSpieltag) o);
		}
		if (erg.size() == 0)
		{
			throw new SpieltagException("Nix da EnSpieltag");
		}
		EnSpieltag[] spieltagarray = new EnSpieltag[erg.size()];
		Iterator<EnSpieltag> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			spieltagarray[i] = iter1.next();
			i++;
		}
		return spieltagarray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnSpieltag getSpieltag(int spieltagID) throws SpieltagException
	{
		EnSpieltag erg = manager.find(EnSpieltag.class, spieltagID);
		if (erg == null)
		{
			throw new SpieltagException("Spieltag nach ID nicht gefunden");
		}
		else
		{
			return erg;
		}
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnSpieltag getCurrentSpieltag() throws SpieltagException
	{
		List<?> liste = manager.createNamedQuery("EnSpieltag.findCurrent").getResultList();
		Vector<EnSpieltag> erg = new Vector<EnSpieltag>();
		for (Object o : liste)
		{
			erg.add((EnSpieltag) o);
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		throw new SpieltagException("Spieltag current nicht gefunden");
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnSpieltag getNextSpieltag() throws SpieltagException
	{
		List<?> liste = manager.createNamedQuery("EnSpieltag.findNext").getResultList();
		Vector<EnSpieltag> erg = new Vector<EnSpieltag>();
		for (Object o : liste)
		{
			erg.add((EnSpieltag) o);
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		throw new SpieltagException("Spieltag next nicht gefunden");
	}
	
	@Override
	public void addSpieltag(EnSpieltag spieltag) throws SpieltagException
	{
		if (manager.find(EnSpieltag.class, spieltag.getIdSpieltag()) != null)
		{
			throw new SpieltagException("Spieltag bereits vorhanden");
		}
		manager.persist(spieltag);
	}
	
	@Override
	public void changeSpieltag(EnSpieltag spieltagNeu) throws SpieltagException
	{
		manager.merge(spieltagNeu);
	}
	
	@Override
	public void deleteSpieltag(int spieltagID) throws SpieltagException
	{
		EnSpieltag alt = manager.find(EnSpieltag.class, spieltagID);
		if (alt == null)
		{
			throw new SpieltagException("Spieltag delete nicht gefunden");
		}
		manager.remove(alt);
	}
	
	@Override
	public EnSpieltag getNextzuTippenDutySpieltag(EnTipper tipper) throws SpieltagException
	{
		List<?> liste = manager.createNamedQuery("EnSpieltag.findNext").getResultList();
		Vector<EnSpieltag> erg = new Vector<EnSpieltag>();
		TippBeanLocal tbl = LookupLocalService.lookupTippBeanLocal();
		
		for (Object o : liste)
		{
			EnSpieltag spieltag = (EnSpieltag) o;
			if(!spieltag.getAtSpieltagIsDuty())
			{
				try
				{
					tbl.getTipps(tipper, spieltag);
					erg.add((EnSpieltag) o);
				}
				catch (TippException e)
				{
					throw new SpieltagException("Duty-Spieltag für Tipper " + tipper.getAtTipperVorname() 
							+ " " + tipper.getAtTipperName() + " nicht gefunden!");
				}
			}
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		throw new SpieltagException("Duty-Spieltag nicht gefunden");
	}
	
	@Override
	public EnSpieltag getNextzuTippenFreeSpieltag(EnTipper tipper) throws SpieltagException
	{
		List<?> liste = manager.createNamedQuery("EnSpieltag.findNext").getResultList();
		Vector<EnSpieltag> erg = new Vector<EnSpieltag>();
		TippBeanLocal tbl = LookupLocalService.lookupTippBeanLocal();
		
		for (Object o : liste)
		{
			EnSpieltag spieltag = (EnSpieltag) o;
			if(!spieltag.getAtSpieltagIsDuty())
			{
				try
				{
					tbl.getTipps(tipper, spieltag);
					erg.add((EnSpieltag) o);
				}
				catch (TippException e)
				{
					throw new SpieltagException("Free-Spieltag für Tipper " + tipper.getAtTipperVorname() 
							+ " " + tipper.getAtTipperName() + " nicht gefunden!");
				}
			}
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		throw new SpieltagException("Free-Spieltag nicht gefunden");
	}
	
}
