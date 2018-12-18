package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.DetailTable;
import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnTipper;

public class DetailForm
{
	private static Log log = LogFactory.getLog(DetailForm.class);
	
	private boolean ButtonNavigation;
	private EnTipper user;
	private EnSpiel spiel;
	
	private SpielBeanRemote spielStateless;
	private TipperBeanRemote tipperStateless;
	private TippBeanRemote tippStateless;
	private VereinBeanRemote vereinStateless;
	
	public DetailForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.ButtonNavigation = req.getParameter("navigation") != null;
		if(this.isButtonNavigation())
		{
			return;
		}
		this.getResources();
		this.user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
		if(this.user == null)
		{
			req.getSession().setAttribute("TIMEOUT", "Timeout, bitte neu anmelden");
			log.info("ACHTUNG - Timeout - Login-Formular wird angezeigt!");
			return;
		}
		int spielId = (Integer) req.getSession().getAttribute(ApplConstants.DETAILSPIEL);
		this.spiel = null;
		try
		{
			spiel = spielStateless.getSpiel(spielId);
		}
		catch (SpielException e)
		{
			log.error("SpielException in Zeile 68 " + e.getMessage());
		}		
		req.getSession().setAttribute(ApplConstants.DETAILTABLE, new DetailTable(user, spiel, tippStateless, tipperStateless, vereinStateless));
	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}

	public EnSpiel getSpiel()
	{
		return spiel;
	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}

}
