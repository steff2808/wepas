package de.wepas.connector;
import javax.ejb.Remote;

import de.wepas.jpa.EnForum;

@Remote
public interface ForumBeanRemote 
{
	EnForum[] getForum() throws ForumException;
	
	public void addForum(EnForum beitrag) throws ForumException;
}
