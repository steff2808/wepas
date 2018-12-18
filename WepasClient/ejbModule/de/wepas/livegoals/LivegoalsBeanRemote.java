package de.wepas.livegoals;

import javax.ejb.Remote;

@Remote
public interface LivegoalsBeanRemote
{
	public void createLivegoalObject();	
	
	public void startLivegoals();
	
	public void stopLivegoals();	
	
	public void destroyLivegoalObject();
	
	public void loadTESTData();
	
	public void saveTESTData();
	
	public LivegoalsObject getLivegoalsObject();
}
