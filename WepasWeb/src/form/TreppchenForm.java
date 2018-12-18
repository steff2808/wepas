package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.TreppchenTable;
import util.LookupRemoteService;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class TreppchenForm
{
	private static Log log = LogFactory.getLog(TreppchenForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonStatistik;
	private boolean ButtonHighscore;
	private boolean ButtonSupertreffer;
	private boolean ButtonTreffer;
	private boolean ButtonBlinde;
	private boolean ButtonSortname;
	private boolean ButtonSortspieltag;
	private boolean ButtonSortpunkte;
	private boolean ButtonSortgewinn;
	
	private TipperBeanRemote tipperStateless;
	private SpieltagBeanRemote spieltagStateless;
	private WetteBeanRemote wetteStateless;
	private HttpServletRequest req;
	
	public TreppchenForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonStatistik = req.getParameter("statistik") != null;
		this.ButtonHighscore = req.getParameter("highscore") != null;
		this.ButtonSupertreffer = req.getParameter("supertreffer") != null;
		this.ButtonTreffer = req.getParameter("treffer") != null;
		this.ButtonBlinde = req.getParameter("blinde") != null;
		this.ButtonSortname = req.getParameter("sortname") != null;
		this.ButtonSortspieltag = req.getParameter("sortspieltag") != null;
		this.ButtonSortpunkte = req.getParameter("sortpunkte") != null;
		this.ButtonSortgewinn = req.getParameter("sortgewinn") != null;
		
		if (this.ButtonNavigation || this.ButtonStatistik || this.ButtonHighscore || this.ButtonSupertreffer || this.ButtonTreffer || this.ButtonBlinde)
		{
			req.getSession().setAttribute(ApplConstants.TREPPCHENTABLE, null);
			return;
		}
		
		if (this.isButtonSortname())
		{
			TreppchenTable treppchentable = (TreppchenTable) req.getSession().getAttribute(ApplConstants.TREPPCHENTABLE);
			treppchentable.sortName();
		}
		else if (this.isButtonSortspieltag())
		{
			TreppchenTable treppchentable = (TreppchenTable) req.getSession().getAttribute(ApplConstants.TREPPCHENTABLE);
			treppchentable.sortSpieltag();
		}
		else if (this.isButtonSortpunkte())
		{
			TreppchenTable treppchentable = (TreppchenTable) req.getSession().getAttribute(ApplConstants.TREPPCHENTABLE);
			treppchentable.sortPunkte();
		}
		else if (this.isButtonSortgewinn())
		{
			TreppchenTable treppchentable = (TreppchenTable) req.getSession().getAttribute(ApplConstants.TREPPCHENTABLE);
			treppchentable.sortGewinn();
		}
		else
		{
			this.getResources();
			EnTipper user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
			req.getSession().setAttribute(ApplConstants.TREPPCHENTABLE, new TreppchenTable(user, wetteStateless, tipperStateless, spieltagStateless));
		}
	}
	
	private void getResources()
	{
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.wetteStateless = LookupRemoteService.lookupWetteBeanRemote();
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
	
	public boolean isButtonSupertreffer()
	{
		if(ButtonSupertreffer)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Supertreffer) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSupertreffer;
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
	
	private boolean isButtonSortname()
	{
		return ButtonSortname;
	}
	
	private boolean isButtonSortspieltag()
	{
		return ButtonSortspieltag;
	}
	
	private boolean isButtonSortpunkte()
	{
		return ButtonSortpunkte;
	}
	
	private boolean isButtonSortgewinn()
	{
		return ButtonSortgewinn;
	}
	
}
