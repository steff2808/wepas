package form;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.LookupRemoteService;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class LoginForm
{
	private static Log log = LogFactory.getLog(LoginForm.class);
	
	private char statusflag;
	private String message;
	private String tipperLogin;
	private String tipperPass;
	private int tipperID;
	private TipperBeanRemote tipperStateless = null;
	
	public LoginForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		// log.info("SessioID: " + req.getSession().getId());
		// try
		// {
		// EnTipper debug = (EnTipper)
		// req.getSession().getAttribute(ApplConstants.USER);
		// log.info("TipperID: " + debug.getIdTipper() + " (" +
		// debug.getAtTipperName() + ", " + debug.getAtTipperVorname() + ")");
		// }
		// catch(Exception e)
		// {
		// //kann schon mal sein
		// }
		req.getSession().removeAttribute(ApplConstants.USER);
		if (req.getParameter("login") == null)
		{
			// --> Erster Aufruf, es konnte noch kein Button geklickt werden!
			this.statusflag = '1';
			return;
		}
		
		if (req.getSession().getAttribute("TIMEOUT") != null)
		{
			this.message = (String) req.getSession().getAttribute("TIMEOUT");
			return;
		}
		
		// --> Hier wurde die JSP schon gerendert und mit dem Login-Button
		// zurückgeschickt
		tipperID = (Integer.parseInt(req.getParameter("DropTipper")));
		tipperLogin = req.getParameter("TXT_Tip_Log");
		tipperPass = req.getParameter("TXT_Tip_Pass");
		
		this.tipperStateless = (TipperBeanRemote) req.getSession().getAttribute(ApplConstants.SAVETIPPERREMOTE);
		
		if (this.tipperStateless == null)
		{
			this.getResources();
			req.getSession().setAttribute(ApplConstants.SAVETIPPERREMOTE, this.tipperStateless);
		}
		
		EnTipper tipper = null;
		if (tipperID > 0)
		{
			try
			{
				tipper = this.tipperStateless.getTipper(tipperID);
			}
			catch (TipperException e)
			{
				this.statusflag = 'F';
				this.message = e.getMessage();
				return;
			}
		}
		else if (tipperID == 0 && tipperLogin.length() > 5)
		{
			try
			{
				tipper = this.tipperStateless.getTipper(tipperLogin.toLowerCase());
			}
			catch (TipperException e)
			{
				this.statusflag = 'F';
				this.message = e.getMessage();
				return;
			}
		}
		else
		{
			this.statusflag = 'F';
			this.message = "Keinen Benutzer in der Auswahlbox ausgewählt und keinen Loginnamen angegeben";
			return;
		}
		
		if (this.tipperPass.equals(tipper.getAtTipperPassword()))
		{
			req.getSession().setAttribute(ApplConstants.USER, tipper);
			this.statusflag = 'O';
			log.info("--> LOGIN " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			// Bugfix.saveLoginTipper(tipper);
			return;
		}
		else
		{
			this.statusflag = 'L';
			this.message = "Passwort ist falsch!";
			return;
		}
	}
	
	public void action(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		if (this.getStatusflag() == 'O')
		{
			// res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			// String newLocn = "jsp/tipper/Navigation.jsp";
			// res.setHeader("Location",newLocn);
			
			// res.sendRedirect("jsp/tipper/Navigation.jsp");
		}
	}
	
	private void getResources()
	{
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
	}
	
	public char getStatusflag()
	{
		return statusflag;
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
