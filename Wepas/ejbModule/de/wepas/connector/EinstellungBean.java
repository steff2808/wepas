package de.wepas.connector;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.wepas.counter.EinstellungBeanCounter;
import de.wepas.jpa.EnEinstellung;

/**
 * Session Bean implementation class EinstellungBean
 */
@Stateless
@LocalBean
public class EinstellungBean implements EinstellungBeanRemote, EinstellungBeanLocal, Serializable
{
	private static final long serialVersionUID = -6097510603325729739L;
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = EinstellungBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		EinstellungBeanCounter.destroy(beanId);
	}
	
	@Override
	public EnEinstellung[] getEinstellung(String schluessel) throws EinstellungException
	{
		Query query = manager.createNamedQuery("EnEinstellung.findBySchluessel");
		query.setParameter("schluessel", schluessel);
		List<?> liste = query.getResultList();
		Vector<EnEinstellung> erg = new Vector<EnEinstellung>();
		for (Object o : liste)
		{
			erg.add((EnEinstellung) o);
		}
		EnEinstellung[] einstellungarray = new EnEinstellung[erg.size()];
		Iterator<EnEinstellung> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			einstellungarray[i] = iter1.next();
			i++;
		}
		return einstellungarray;
	}
	
	@Override
	public EnEinstellung getEinstellung(int einstellungId) throws EinstellungException
	{
		Query query = manager.createNamedQuery("EnEinstellung.findById");
		query.setParameter("id", einstellungId);
		return (EnEinstellung) query.getSingleResult();
	}

	
}
