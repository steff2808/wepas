package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.SupertrefferTable;
import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class SupertrefferForm
{
	private static Log log = LogFactory.getLog(SupertrefferForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonStatistik;
	private boolean ButtonHighscore;
	private boolean ButtonTreppchen;
	private boolean ButtonTreffer;
	private boolean ButtonBlinde;
    private boolean ButtonSortname;
	private boolean ButtonSortspieltag;
	
	private TipperBeanRemote tipperStateless;
	private TippBeanRemote tippStateless;
	private SpieltagBeanRemote spieltagStateless;
	private SpielBeanRemote spielStateless;
	private VereinBeanRemote vereinStateless;
	private HttpServletRequest req;
	
	public SupertrefferForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonStatistik = req.getParameter("statistik") != null;
		this.ButtonHighscore = req.getParameter("highscore") != null;
		this.ButtonTreppchen = req.getParameter("treppchen") != null;
		this.ButtonTreffer = req.getParameter("treffer") != null;
		this.ButtonBlinde = req.getParameter("blinde") != null;
		this.ButtonSortname = req.getParameter("sortname") != null;
		this.ButtonSortspieltag = req.getParameter("sortspieltag") != null;
		
		if (this.ButtonNavigation || this.ButtonStatistik 
				|| this.ButtonHighscore || this.ButtonTreppchen 
				|| this.ButtonTreffer || this.ButtonBlinde)
		{
			req.getSession().setAttribute(ApplConstants.SUPERTREFFERTABLE, null);
			return;
		}
		if (this.isButtonSortname())
		{
			SupertrefferTable supertreffertable = (SupertrefferTable) req.getSession().getAttribute(ApplConstants.SUPERTREFFERTABLE);
			supertreffertable.sortName();
		}
		else if (this.isButtonSortspieltag())
		{
			SupertrefferTable supertreffertable = (SupertrefferTable) req.getSession().getAttribute(ApplConstants.SUPERTREFFERTABLE);
			supertreffertable.sortSpieltag();
		}
		else
		{
			this.getResources();
			EnTipper user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
			req.getSession().setAttribute(ApplConstants.SUPERTREFFERTABLE, new SupertrefferTable(user, tippStateless, tipperStateless, spielStateless, spieltagStateless, vereinStateless));
		}
  	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}

	public boolean isButtonStatistik()
	{
		if(ButtonStatistik)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonStatistik;
	}

	public boolean isButtonHighscore()
	{
		if(ButtonHighscore)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Highscore) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonHighscore;
	}

	public boolean isButtonTreppchen()
	{
		if(ButtonTreppchen)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Treppchen) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTreppchen;
	}

	public boolean isButtonTreffer()
	{
		if(ButtonTreffer)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Treffer) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTreffer;
	}

	public boolean isButtonBlinde()
	{
		if(ButtonBlinde)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Blinde) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonBlinde;
	}

	public boolean isButtonSortname()
	{
		return ButtonSortname;
	}

	public boolean isButtonSortspieltag()
	{
		return ButtonSortspieltag;
	}
}
