package form;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.jpa.EnSpiel;

public class AdminForm
{
	private static Log log = LogFactory.getLog(AdminForm.class);
	
	//-->Welche Buttons können in der AdminNavi.jsp geklickt worden sein?
	private boolean ButtonTipperVerw;
	private boolean ButtonVereinVerw;
	private boolean ButtonSpieltagVerw;
	private boolean ButtonStorno;
	private boolean ButtonNavigation;
	
	private boolean ButtonCreate;
	private boolean ButtonStartLG;
	private boolean ButtonStoppLG;
	private boolean ButtonDestroy;
	private boolean ButtonLoadLG;
	private boolean ButtonSaveLG;
	private boolean ButtonLivegoalsStatus;
	
	Vector<EnSpiel> offenespiele;
	EnSpiel zupflegendesspiel;

	public AdminForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
//		log.info("SessioID: " + req.getSession().getId());
//		EnTipper debug = (EnTipper) req.getSession().getAttribute("LoginTipper");
//		log.info("TipperID: " + debug.getIdTipper() + " (" + debug.getAtTipperName() + ", " + debug.getAtTipperVorname() + ")");
		this.ButtonTipperVerw  		= req.getParameter("tipperverwaltung") != null;
		this.ButtonVereinVerw  		= req.getParameter("vereinverwaltung") != null;
		this.ButtonSpieltagVerw		= req.getParameter("spieltagverwaltung") != null;
		this.ButtonStorno      		= req.getParameter("storno") != null;
		this.ButtonNavigation 		= req.getParameter("navigation") != null;
		this.ButtonCreate			= req.getParameter("create") != null;
		this.ButtonStartLG     		= req.getParameter("startlg") != null;
		this.ButtonStoppLG  		= req.getParameter("stopplg") != null;
		this.ButtonDestroy     		= req.getParameter("destroy") != null;
		this.ButtonLoadLG      		= req.getParameter("loadlg") != null;
		this.ButtonSaveLG			= req.getParameter("savelg") != null;
		this.ButtonLivegoalsStatus	= req.getParameter("livegoalsstatus") != null;		
		
		if(this.ButtonNavigation || this.ButtonSpieltagVerw || this.ButtonStorno || this.ButtonTipperVerw || this.ButtonVereinVerw)
		{
			return;
		}

		SpielBeanRemote spielStateless = LookupRemoteService.lookupSpielBeanRemote();
	
		try
		{
//			offenespiele = spbr.getSpieleOffen();
			EnSpiel[] spielarray = spielStateless.getSpieleOffen();
			offenespiele = new Vector<EnSpiel>(spielarray.length);
			for(int i = 0; i < spielarray.length; i++)
			{
				offenespiele.add(spielarray[i]);
			}

		}
		catch (SpielException e)
		{
			System.out.println("--->AdminForm / 60");
			e.printStackTrace();
		}
		
		Iterator<EnSpiel> iter1 = this.offenespiele.iterator();
		while(iter1.hasNext())
		{
			EnSpiel spiel = iter1.next();
			if(req.getParameter("zupflegendesspiel" + spiel.getIdSpiel()) != null)
			{
				this.zupflegendesspiel = spiel;
				return;
			}
		}
	}
	
	public Vector<EnSpiel> getSpieleOffen()
	{
		return this.offenespiele;
	}

	public boolean isButtonTipperVerw()
	{
		return ButtonTipperVerw;
	}

	public boolean isButtonVereinVerw()
	{
		return ButtonVereinVerw;
	}

	public boolean isButtonSpieltagVerw()
	{
		return ButtonSpieltagVerw;
	}

	public boolean isButtonStorno()
	{
		return ButtonStorno;
	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}

	public boolean isButtonCreate()
	{
		return ButtonCreate;
	}

	public boolean isButtonStartLG()
	{
		return ButtonStartLG;
	}

	public boolean isButtonStoppLG()
	{
		return ButtonStoppLG;
	}

	public boolean isButtonDestroy()
	{
		return ButtonDestroy;
	}

	public boolean isButtonLoadLG()
	{
		return ButtonLoadLG;
	}

	public boolean isButtonSaveLG()
	{
		return ButtonSaveLG;
	}

	public boolean isButtonLivegoalsStatus()
	{
		return ButtonLivegoalsStatus;
	}

	public Vector<EnSpiel> getOffenespiele()
	{
		return offenespiele;
	}

	public EnSpiel getZupflegendesspiel()
	{
		return zupflegendesspiel;
	}
	
}
