package de.wepas.connector;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.wepas.counter.TippBeanCounter;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

/**
 * Session Bean implementation class TippBean
 */
@Stateless
@LocalBean
public class TippBean implements TippBeanRemote, TippBeanLocal, Serializable 
{
	private static final long serialVersionUID = -8995173663183608033L;
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = TippBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		TippBeanCounter.destroy(beanId);
	}

	@Override
	public void addTipp(EnTipp tipp) throws TippException
	{
		if (manager.find(EnTipp.class, tipp.getIdTipp()) != null)
		{
			throw new TippException("Tipp bereits vorhanden");
		}
		manager.persist(tipp);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnTipp[] getTipps() throws TippException
	{
		List<?> liste = manager.createNamedQuery("EnTipp.findAll").getResultList();
		Vector<EnTipp> erg = new Vector<EnTipp>();
		for (Object o : liste)
		{
			erg.add((EnTipp) o);
		}
		EnTipp[] tipparray = new EnTipp[erg.size()];
		Iterator<EnTipp>iter1 = erg.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}
	
	@Override
	public EnTipp[] getSupertreffer() throws TippException
	{
		List<?> liste = manager.createNamedQuery("EnTipp.findSupertreffer").getResultList();
		Vector<EnTipp> erg = new Vector<EnTipp>();
		for (Object o : liste)
		{
			erg.add((EnTipp) o);
		}
		EnTipp[] tipparray = new EnTipp[erg.size()];
		Iterator<EnTipp>iter1 = erg.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}

	@Override
	public EnTipp[] getTreffer() throws TippException
	{
		List<?> liste = manager.createNamedQuery("EnTipp.findTreffer").getResultList();
		Vector<EnTipp> erg = new Vector<EnTipp>();
		for (Object o : liste)
		{
			erg.add((EnTipp) o);
		}
		EnTipp[] tipparray = new EnTipp[erg.size()];
		Iterator<EnTipp>iter1 = erg.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}

	@Override
	public EnTipp[] getBlinde() throws TippException
	{
		List<?> liste = manager.createNamedQuery("EnTipp.findBlinde").getResultList();
		Vector<EnTipp> erg = new Vector<EnTipp>();
		for (Object o : liste)
		{
			erg.add((EnTipp) o);
		}
		EnTipp[] tipparray = new EnTipp[erg.size()];
		Iterator<EnTipp>iter1 = erg.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}

	@Override
	public EnTipp[] getTipps(EnSpieltag spieltag) throws TippException
	{
		throw new RuntimeException("// TODO Auto-generated method stub");
	}

	@Override
	public EnTipp getTipp(int tippId) throws TippException
	{
		throw new RuntimeException("// TODO Auto-generated method stub");
	}

	@Override
	public void changeTipp(EnTipp tippNeu) throws TippException
	{
		manager.merge(tippNeu);
	}

	@Override
	public EnTipp getTipps(EnTipper tipper, EnSpiel spiel) throws TippException
	{
		Query query = manager.createNamedQuery("EnTipp.findByTipperSpiel");
		query.setParameter("tipper", tipper.getIdTipper());
		query.setParameter("spiel", spiel.getIdSpiel());
		List<?> liste = query.getResultList();
		Vector<EnTipp> erg = new Vector<EnTipp>();
		for (Object o : liste)
		{
			erg.add((EnTipp) o);
		}
		if(erg.size() > 0)
		{
			return erg.firstElement();
		}
		return null;
	}

	@Override
	public EnTipp[] getTipps(EnTipper tipper, EnSpieltag spieltag) throws TippException
	{
		Query spielquery = manager.createNamedQuery("EnSpiel.findBySpieltag");
		spielquery.setParameter("spieltag", spieltag.getIdSpieltag());
		List<?> spielliste = spielquery.getResultList();
		Vector<EnSpiel> erg1 = new Vector<EnSpiel>();
		for (Object o : spielliste)
		{
			erg1.add((EnSpiel) o);
		}
		Vector<EnTipp> erg2 = new Vector<EnTipp>(erg1.size());
		Iterator<EnSpiel> iter1 = erg1.iterator();
		while(iter1.hasNext())
		{
			Query tippquery = manager.createNamedQuery("EnTipp.findByTipperSpiel");
			tippquery.setParameter("tipper", tipper.getIdTipper());
			tippquery.setParameter("spiel", iter1.next().getIdSpiel());
			List<?> tippliste = tippquery.getResultList();
			for (Object o : tippliste)
			{
				erg2.add((EnTipp) o);
			}
		}
		EnTipp[] tipparray = new EnTipp[erg2.size()];
		Iterator<EnTipp>iter2 = erg2.iterator();
		int i = 0;
		while(iter2.hasNext())
		{
			tipparray[i] = iter2.next();
			i++;
		}
		return tipparray;
	}
	
	@Override
	public EnTipp[] getTipps(EnTipper tipper) throws TippException
	{
		Query spielquery = manager.createNamedQuery("EnTipp.findByTipper");
		spielquery.setParameter("tipper", tipper.getIdTipper());
		List<?> tippliste = spielquery.getResultList();
		Vector<EnTipp> erg1 = new Vector<EnTipp>();
		for (Object o : tippliste)
		{
			erg1.add((EnTipp) o);
		}
		if(erg1.size() == 0)
		{
			throw new TippException("Nix da EnTipp");
		}
		EnTipp[] tipparray = new EnTipp[erg1.size()];
		Iterator<EnTipp>iter1 = erg1.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}
	
	@Override
	public EnTipp[] getStatistikTipps(EnTipper tipper) throws TippException
	{
		Query spielquery = manager.createNamedQuery("EnTipp.findValidByTipper");
		spielquery.setParameter("tipper", tipper.getIdTipper());
		List<?> tippliste = spielquery.getResultList();
		Vector<EnTipp> erg1 = new Vector<EnTipp>();
		for (Object o : tippliste)
		{
			erg1.add((EnTipp) o);
		}
		if(erg1.size() == 0)
		{
			throw new TippException("Nix da EnTipp");
		}
		EnTipp[] tipparray = new EnTipp[erg1.size()];
		Iterator<EnTipp>iter1 = erg1.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}

	@Override
	public EnTipp[] getTipps(EnSpiel spiel) throws TippException
	{
		Query spielquery = manager.createNamedQuery("EnTipp.findBySpiel");
		spielquery.setParameter("spiel", spiel.getIdSpiel());
		List<?> tippliste = spielquery.getResultList();
		Vector<EnTipp> erg1 = new Vector<EnTipp>();
		for (Object o : tippliste)
		{
			erg1.add((EnTipp) o);
		}
		if(erg1.size() == 0)
		{
			throw new TippException("Nix da EnTipp");
		}
		EnTipp[] tipparray = new EnTipp[erg1.size()];
		Iterator<EnTipp>iter1 = erg1.iterator();
		int i = 0;
		while(iter1.hasNext())
		{
			tipparray[i] = iter1.next();
			i++;
		}
		return tipparray;
	}

}
