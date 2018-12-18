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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.counter.WetteBeanCounter;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;

/**
 * Session Bean implementation class WetteBean
 */
@Stateless
@LocalBean
public class WetteBean implements WetteBeanRemote, WetteBeanLocal, Serializable
{
	private static final long serialVersionUID = -737224022508335374L;
	private int beanId;
	private static Log log = LogFactory.getLog(WetteBean.class);
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = WetteBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		WetteBeanCounter.destroy(beanId);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnWette[] getWette() throws WetteException
	{
		List<?> liste = manager.createNamedQuery("EnWette.findAll").getResultList();
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() == 0)
		{
			throw new WetteException("EnWette.findAll -->Keine Wette gefunden!");
		}
		EnWette[] wettearray = new EnWette[erg.size()];
		Iterator<EnWette> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			wettearray[i] = iter1.next();
			i++;
		}
		return wettearray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnWette[] getSieger() throws WetteException
	{
		List<?> liste = manager.createNamedQuery("EnWette.findSieger").getResultList();
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() == 0)
		{
			throw new WetteException("EnWette.findSieger -->Keinen Sieger gefunden! ");
		}
		EnWette[] wettearray = new EnWette[erg.size()];
		Iterator<EnWette> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			wettearray[i] = iter1.next();
			i++;
		}
		return wettearray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnWette[] getGewinner() throws WetteException
	{
		List<?> liste = manager.createNamedQuery("EnWette.findGewinner").getResultList();
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() == 0)
		{
			throw new WetteException("EnWette.findGewinner -->Keinen Gewinner gefunden! ");
		}
		EnWette[] wettearray = new EnWette[erg.size()];
		Iterator<EnWette> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			wettearray[i] = iter1.next();
			i++;
		}
		return wettearray;
	}
	
	@Override
	public EnWette[] getWetten(EnSpieltag spieltag) throws WetteException
	{
		Query query = manager.createNamedQuery("EnWette.findBySpieltag");
		query.setParameter("spieltag", spieltag.getIdSpieltag());
		List<?> liste = query.getResultList();
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() == 0)
		{
			throw new WetteException("EnWette.findBySpieltag -->Keine Wette gefunden");
		}
		EnWette[] wettearray = new EnWette[erg.size()];
		Iterator<EnWette> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			wettearray[i] = iter1.next();
			i++;
		}
		return wettearray;
	}
	
	@Override
	public EnWette[] getWetten(EnTipper tipper) throws WetteException
	{
		
		Query query = manager.createNamedQuery("EnWette.findByTipper");
		query.setParameter("tipper", tipper.getIdTipper());
		List<?> liste = query.getResultList();
		
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() == 0)
		{
			throw new WetteException("EnWette.findByTipper -->Keine Wette gefunden!");
		}
		EnWette[] wettearray = new EnWette[erg.size()];
		Iterator<EnWette> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			wettearray[i] = iter1.next();
			i++;
		}
		return wettearray;
	}
	
	@Override
	public EnWette getWette(EnTipper tipper, EnSpieltag spieltag) throws WetteException
	{
		Query query = manager.createNamedQuery("EnWette.findByTipperSpieltag");
		query.setParameter("spieltag", spieltag.getIdSpieltag());
		query.setParameter("tipper", tipper.getIdTipper());
		List<?> liste = query.getResultList();
		Vector<EnWette> erg = new Vector<EnWette>();
		for (Object o : liste)
		{
			erg.add((EnWette) o);
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		return null;
	}
	
	@Override
	public void addWette(EnWette[] wetten) throws WetteException
	{
		for(int i = 0; i < wetten.length; i++)
		{
			EnWette wette = wetten[i];
//			log.info("-->" + i);
			wette.setIdWette(wette.getIdSpieltag() * 100 + wette.getIdTipper());
//			log.info("-->vor manager.find " + wette.toString());
			if (manager.find(EnSpieltag.class, wette.getIdWette()) != null)
			{
				throw new WetteException("addWette(EnWette wette) -->Wette bereits vorhanden");
			}
			manager.persist(wette);
			manager.flush();
		}
	}
	
	@Override
	public void changeWette(EnWette wetteNeu) throws WetteException
	{
		manager.merge(wetteNeu);
	}
	
	@Override
	public void deleteWette(int wetteID) throws WetteException
	{
		EnWette alt = manager.find(EnWette.class, wetteID);
		if (alt == null)
		{
			throw new WetteException("deleteWette(int wetteID" + wetteID + ") -->Wette nicht gefunden");
		}
		manager.remove(alt);
	}
	
}
