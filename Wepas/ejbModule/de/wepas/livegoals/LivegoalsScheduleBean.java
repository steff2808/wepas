package de.wepas.livegoals;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.livegoals.LivegoalsBeanLocal;
import de.wepas.util.LookupLocalService;

@Stateless
@LocalBean
public class LivegoalsScheduleBean
{
	private static Log log = LogFactory.getLog(LivegoalsScheduleBean.class);
	
	public LivegoalsScheduleBean()
	{
		super();
		log.info("Constructing " + this.getClass().getName());
	}

	@Schedules
	({
		@Schedule(hour = "20", minute = "00", dayOfWeek = "Fri", persistent = false)  
	})
	public void createLiveGoal()
	{
		LivegoalsBeanLocal livegoalsSingleton = LookupLocalService.lookupLivegoalsBeanLocal();
		livegoalsSingleton.createLivegoalObject();
	}
	
	@Schedules
	({
		@Schedule(hour = "20", minute = "01", dayOfWeek = "Fri", persistent = false)  
	})
	public void startLivegoals()
	{
		LivegoalsBeanLocal livegoalsSingleton = LookupLocalService.lookupLivegoalsBeanLocal();
		livegoalsSingleton.startLivegoals();
	}

	@Schedules
	({
		@Schedule(hour = "20", minute = "00", dayOfWeek = "Sun", persistent = false)  
	})
	public void stopLivegoals()
	{
		LivegoalsBeanLocal livegoalsSingleton = LookupLocalService.lookupLivegoalsBeanLocal();
		livegoalsSingleton.stopLivegoals();
	}

	@Schedules
	({
		@Schedule(hour = "20", minute = "01", dayOfWeek = "Sun", persistent = false)  
	})
	public void destroyLiveGoal()
	{	LivegoalsBeanLocal livegoalsSingleton = LookupLocalService.lookupLivegoalsBeanLocal();
		livegoalsSingleton.destroyLivegoalObject();
	}
}

//@Schedule(hour = "20", minute = "00", dayOfWeek = "Sun", persistent = false)  

