package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class BestcaseForm
{
	private static Log log = LogFactory.getLog(BestcaseForm.class);
	
	private boolean ButtonWorstcase;
	private boolean ButtonLivegoals;
	private HttpServletRequest req;

	public BestcaseForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonWorstcase = req.getParameter("worstcase") != null;
		this.ButtonLivegoals = req.getParameter("livegoals") != null;
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
	public boolean isButtonLivegoals()
	{
		return ButtonLivegoals;
	}
	
}
