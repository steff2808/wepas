package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class WorstcaseForm
{
	private static Log log = LogFactory.getLog(WorstcaseForm.class);
	
	private boolean ButtonBestcase;
	private boolean ButtonLivegoals;
	private HttpServletRequest req;

	public WorstcaseForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonBestcase = req.getParameter("bestcase") != null;
		this.ButtonLivegoals = req.getParameter("livegoals") != null;
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
	public boolean isButtonLivegoals()
	{
		return ButtonLivegoals;
	}
	
}
