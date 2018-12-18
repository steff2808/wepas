package de.wepas.livegoals;
import javax.ejb.Local;

@Local
public interface LivegoalsBeanLocal 
{
	public void createLivegoalObject();	
	
	public void startLivegoals();
	
	public void stopLivegoals();	
	
	public void destroyLivegoalObject();
	
	public void loadTESTData();
	
	public void saveTESTData();

	public void insertTESTData();
	
	public LivegoalsObject getLivegoalsObject();
}
