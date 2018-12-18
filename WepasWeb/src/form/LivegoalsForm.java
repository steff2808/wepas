package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class LivegoalsForm
{
	private static Log log = LogFactory.getLog(LivegoalsForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonWorstcase;
	private boolean ButtonBestcase;
	private HttpServletRequest req;

	public LivegoalsForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonWorstcase = req.getParameter("worstcase") != null;
		this.ButtonBestcase = req.getParameter("bestcase") != null;
		if(this.isButtonNavigation())
		{
			return;
		}
 	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	public boolean isButtonWorstcase()
	{
		if(ButtonWorstcase)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Worstcase) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonWorstcase;
	}
	public boolean isButtonBestcase()
	{
		if(ButtonBestcase)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Bestcase) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonBestcase;
	}
	

}
