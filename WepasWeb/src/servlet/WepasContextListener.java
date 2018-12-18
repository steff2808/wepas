package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import test.Hund;

public class WepasContextListener implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		System.out.println("ServletContextEvent--->Destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		System.out.println("ServletContextEvent--->Initialize");
		Hund h = new Hund("Dalmatiner", 4);
		ServletContext sc = event.getServletContext();
		sc.setAttribute("hund", h);
	}
}
