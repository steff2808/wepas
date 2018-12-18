package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.TabelleTable;
import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class TabelleForm
{
	private static Log log = LogFactory.getLog(TabelleForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonHeimtabelle;
	private boolean ButtonGesamttabelle;
	private boolean ButtonAuswaertstabelle;
	private boolean ButtonTippertabelle;
	
	private EnTipper user;
	private VereinBeanRemote vereinStateless;
	private SpielBeanRemote spielStateless;
	private TippBeanRemote tippStateless;
	
	public TabelleForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonGesamttabelle = req.getParameter("gesamttabelle") != null;
		this.ButtonHeimtabelle = req.getParameter("heimtabelle") != null;
		this.ButtonAuswaertstabelle = req.getParameter("auswaertstabelle") != null;
		this.ButtonTippertabelle = req.getParameter("tippertabelle") != null;
		
		if (this.ButtonNavigation)
		{
			req.getSession().setAttribute(ApplConstants.TABELLETABLE, null);
			return;
		}
		
		if (this.isButtonHeimtabelle())
		{
			TabelleTable tabelletable = (TabelleTable) req.getSession().getAttribute(ApplConstants.TABELLETABLE);
			tabelletable.sortHeim();
			return;
		}
		else if (this.isButtonAuswaertstabelle())
		{
			TabelleTable tabelletable = (TabelleTable) req.getSession().getAttribute(ApplConstants.TABELLETABLE);
			tabelletable.sortAuswaerts();
			return;
		}
		else if (this.isButtonGesamttabelle())
		{
			TabelleTable tabelletable = (TabelleTable) req.getSession().getAttribute(ApplConstants.TABELLETABLE);
			tabelletable.sortGesamt();
			return;
		}
		else if (this.isButtonTippertabelle())
		{
			TabelleTable tabelletable = (TabelleTable) req.getSession().getAttribute(ApplConstants.TABELLETABLE);
			tabelletable.sortTipper();
			return;
		}
		
		// -->Kein Button geklickt - Erstellen Tabelle
		this.user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
		if (this.user == null)
		{
			req.getSession().setAttribute("TIMEOUT", "Timeout, bitte neu anmelden");
			log.info("ACHTUNG - Timeout - Login-Formular wird angezeigt!");
			return;
		}
		this.getResources();
		req.getSession().setAttribute(ApplConstants.TABELLETABLE, new TabelleTable(user, vereinStateless, tippStateless, spielStateless));
	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}
	
	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
	public boolean isButtonGesamttabelle()
	{
		return ButtonGesamttabelle;
	}
	
	public boolean isButtonHeimtabelle()
	{
		return ButtonHeimtabelle;
	}
	
	public boolean isButtonAuswaertstabelle()
	{
		return ButtonAuswaertstabelle;
	}
	
	public boolean isButtonTippertabelle()
	{
		return ButtonTippertabelle;
	}
}
