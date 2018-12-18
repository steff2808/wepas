package de.wepas.connector;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.wepas.counter.ForumBeanCounter;
import de.wepas.jpa.EnForum;

/**
 * Session Bean implementation class ForumBean
 */
@Stateless
@LocalBean
public class ForumBean implements ForumBeanRemote
{
	private int beanId;
	
	@PersistenceContext(unitName = "WepasJPA")
	private EntityManager manager;
	
	@PostConstruct
	public void BeanConstruct()
	{
		beanId = ForumBeanCounter.construct();
	}
	
	@PreDestroy
	public void BeanDestroy()
	{
		ForumBeanCounter.destroy(beanId);
	}
	
	@Override
	public EnForum[] getForum() throws ForumException
	{
		List<?> liste = manager.createNamedQuery("EnForum.findAll").getResultList();
		Vector<EnForum> erg = new Vector<EnForum>();
		for (Object o : liste)
		{
			erg.add((EnForum) o);
		}
		if (erg.size() == 0)
		{
			throw new ForumException("Nix da EnForum");
		}
		EnForum[] forumarray = new EnForum[erg.size()];
		Iterator<EnForum> iter1 = erg.iterator();
		int i = 0;
		while (iter1.hasNext())
		{
			forumarray[i] = iter1.next();
			i++;
		}
		return forumarray;
	}

	@Override
	public void addForum(EnForum beitrag) throws ForumException
	{
		if (manager.find(EnForum.class, beitrag.getIdForum()) != null)
		{
			throw new ForumException("Beitrag bereits vorhanden");
		}
		manager.persist(beitrag);
	}
	
}
