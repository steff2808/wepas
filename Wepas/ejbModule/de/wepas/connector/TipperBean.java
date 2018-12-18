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

import de.wepas.counter.TipperBeanCounter;
import de.wepas.jpa.EnTipper;

@Stateless
public class TipperBean implements TipperBeanRemote, TipperBeanLocal, Serializable
{
	private static final long serialVersionUID = -9118348341459665626L;
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = TipperBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		TipperBeanCounter.destroy(beanId);
	}
	
	@Override
	public void addTipper(EnTipper tipper) throws TipperException
	{
		if (manager.find(EnTipper.class, tipper.getIdTipper()) != null)
		{
			throw new TipperException("Tipper bereits vorhanden");
		}
		manager.persist(tipper);
	}
	
	@Override
	public EnTipper getTipper(int tipperID) throws TipperException
	{
		EnTipper erg = manager.find(EnTipper.class, tipperID);
		if (erg == null)
		{
			throw new TipperException("Tipper nicht gefunden");
		}
		else
		{
			return erg;
		}
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnTipper[] getTipper() throws TipperException
	{
		List<?> liste = manager.createNamedQuery("EnTipper.findAll").getResultList();
		Vector<EnTipper> erg = new Vector<EnTipper>();
		for (Object o : liste)
		{
			erg.add((EnTipper) o);
		}
		if (erg.size() == 0)
		{
			throw new TipperException("Nix da EnTipper");
		}
		EnTipper[] tipperarray = new EnTipper[erg.size()];
		Iterator<EnTipper> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			tipperarray[i] = iter1.next();
			i++;
		}
		return tipperarray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnTipper[] getTeamTipper(String team) throws TipperException
	{
		Query query = manager.createNamedQuery("EnTipper.findByTeam");
		query.setParameter("team", team);
		List<?> liste = query.getResultList();
		Vector<EnTipper> erg = new Vector<EnTipper>();
		for (Object o : liste)
		{
			erg.add((EnTipper) o);
		}
		EnTipper[] tipperarray = new EnTipper[erg.size()];
		Iterator<EnTipper> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			tipperarray[i] = iter1.next();
			i++;
		}
		return tipperarray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnTipper getTipper(String tipperLogin) throws TipperException
	{
		Query query = manager.createNamedQuery("EnTipper.findByLogin");
		query.setParameter("login", tipperLogin);
		List<?> liste = query.getResultList();
		Vector<EnTipper> erg = new Vector<EnTipper>();
		for (Object o : liste)
		{
			erg.add((EnTipper) o);
		}
		if (erg.size() > 0)
		{
			return erg.firstElement();
		}
		throw new TipperException("Kein Tipper mit diesem Loginnamen gefunden!");
	}
	
	@Override
	public void changeTipper(EnTipper tipperNeu) throws TipperException
	{
		manager.merge(tipperNeu);
	}
	
	@Override
	public void deleteTipper(int tipperID) throws TipperException
	{
		EnTipper alt = manager.find(EnTipper.class, tipperID);
		if (alt == null)
		{
			throw new TipperException("Tipper nicht gefunden");
		}
		manager.remove(alt);
	}
}
