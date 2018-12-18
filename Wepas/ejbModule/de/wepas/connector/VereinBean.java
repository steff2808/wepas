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

import de.wepas.counter.VereinBeanCounter;
import de.wepas.jpa.EnVerein;

@Stateless
public class VereinBean implements VereinBeanRemote, VereinBeanLocal, Serializable
{
	private static final long serialVersionUID = 6657650713328169393L;
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = VereinBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		VereinBeanCounter.destroy(beanId);
	}
	
	@Override
	public void addVerein(EnVerein verein) throws VereinException
	{
		if (manager.find(EnVerein.class, verein.getIdVerein()) != null)
		{
			throw new VereinException("Verein bereits vorhanden");
		}
		manager.persist(verein);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnVerein[] getVerein() throws VereinException
	{
		List<?> liste = manager.createNamedQuery("EnVerein.findAll").getResultList();
		Vector<EnVerein> erg = new Vector<EnVerein>();
		for (Object o : liste)
		{
			erg.add((EnVerein) o);
		}
		if (erg.size() == 0)
		{
			throw new VereinException("Nix da EnVerein");
		}
		EnVerein[] vereinarray = new EnVerein[erg.size()];
		Iterator<EnVerein> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			vereinarray[i] = iter1.next();
			i++;
		}
		return vereinarray;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnVerein getVerein(int vereinID) throws VereinException
	{
		EnVerein erg = manager.find(EnVerein.class, vereinID);
		if (erg == null)
		{
			throw new VereinException("Verein nicht gefunden");
		}
		else
		{
			return erg;
		}
	}
	
	@Override
	public void changeVerein(EnVerein vereinNeu) throws VereinException
	{
		manager.merge(vereinNeu);
	}
	
	@Override
	public void deleteVerein(int vereinID) throws VereinException
	{
		EnVerein alt = manager.find(EnVerein.class, vereinID);
		if (alt == null)
		{
			throw new VereinException("Verein nicht gefunden");
		}
		manager.remove(alt);
	}
}
