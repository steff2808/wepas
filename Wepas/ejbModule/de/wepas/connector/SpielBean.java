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
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.counter.SpielBeanCounter;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;

@Stateless
public class SpielBean implements SpielBeanRemote, SpielBeanLocal, Serializable
{
	private static final long serialVersionUID = -4947725780640837600L;
	private static Log log = LogFactory.getLog(SpielBean.class);
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = SpielBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		SpielBeanCounter.destroy(beanId);
	}
	
	@Override
	public void addSpiel(EnSpiel spiel) throws SpielException
	{
		if (manager.find(EnSpiel.class, spiel.getIdSpiel()) != null)
		{
			throw new SpielException("Spiel bereits vorhanden");
		}
		manager.persist(spiel);
	}
	
	//
	// @Override
	// public void addSpiel(Vector<EnSpiel> spiele) throws SpielException
	// {
	// Iterator<EnSpiel> iter = spiele.iterator();
	// while(iter.hasNext())
	// {
	// EnSpiel spiel = iter.next();
	// if(manager.find(EnSpiel.class, spiel.getIdSpiel()) != null)
	// {
	// throw new SpielException("Spiele bereits vorhanden");
	// }
	// }
	// iter = spiele.iterator();
	// while(iter.hasNext())
	// {
	// manager.persist(iter.next());
	// }
	// }
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnSpiel[] getSpiele() throws SpielException
	{
		List<?> liste = manager.createNamedQuery("EnSpiel.findAll").getResultList();
		Vector<EnSpiel> erg = new Vector<EnSpiel>();
		for (Object o : liste)
		{
			erg.add((EnSpiel) o);
		}
		if (erg.size() == 0)
		{
			throw new SpielException("Nix da EnSpiel");
		}
		EnSpiel[] spielarray = new EnSpiel[erg.size()];
		Iterator<EnSpiel> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			spielarray[i] = iter1.next();
			i++;
		}
		return spielarray;
	}
	
	@Override
	public EnSpiel[] getSpiele(EnSpieltag spieltag) throws SpielException
	{
		if(spieltag == null)
		{
			return new EnSpiel[0];
		}
		try
		{
			Query query = manager.createNamedQuery("EnSpiel.findBySpieltag");
			query.setParameter("spieltag", spieltag.getIdSpieltag());
			List<?> liste = query.getResultList();
			Vector<EnSpiel> erg = new Vector<EnSpiel>();
			for (Object o : liste)
			{
				erg.add((EnSpiel)o);
			}
			EnSpiel[] spielarray = new EnSpiel[erg.size()];
			Iterator<EnSpiel> iter1 = erg.iterator();
			int i = 0;
			while (iter1.hasNext())
			{
				spielarray[i] = iter1.next();
				i++;
			}
			return spielarray;
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Ein Problem in der SpielBean");
		}
	}
	
	@Override
	public void changeSpiel(EnSpiel spielNeu) throws SpielException
	{
		manager.merge(spielNeu);
	}
	
	@Override
	public void deleteSpiele(EnSpieltag spieltag) throws SpielException
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public EnSpiel getSpiel(int spielId) throws SpielException
	{
		Query query = manager.createNamedQuery("EnSpiel.findById");
		query.setParameter("id", spielId);
		return (EnSpiel) query.getSingleResult();
	}
	
	@Override
	public EnSpiel[] getSpieleOffen() throws SpielException
	{
		List<?> liste = manager.createNamedQuery("EnSpiel.findOffene").getResultList();
		Vector<EnSpiel> erg = new Vector<EnSpiel>();
		for (Object o : liste)
		{
			EnSpiel spiel = (EnSpiel) o;
			if (!spiel.getAtSpielIsPlayed())
			{
				erg.add((EnSpiel) o);
			}
		}
		EnSpiel[] spielarray = new EnSpiel[erg.size()];
		Iterator<EnSpiel> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			spielarray[i] = iter1.next();
			i++;
		}
		return spielarray;
	}
}
