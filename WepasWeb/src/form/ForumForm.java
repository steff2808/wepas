package form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.LookupRemoteService;
import util.StringFormat;
import de.wepas.connector.ForumBeanRemote;
import de.wepas.connector.ForumException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnForum;
import de.wepas.jpa.EnTipper;

public class ForumForm
{
	private static Log log = LogFactory.getLog(ForumForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonSpeichern;
	private EnTipper user;
	private String Text1;
	private String Text2;
	
	private ForumBeanRemote forumStateless;
	
	public ForumForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonSpeichern = req.getParameter("speichern") != null;
		if(this.isButtonNavigation())
		{
			return;
		}
		
		this.user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
		if(this.user == null)
		{
			req.getSession().setAttribute("TIMEOUT", "Timeout, bitte neu anmelden");
			log.info("ACHTUNG - Timeout - Login-Formular wird angezeigt!");
			return;
		}
		this.getResources();
		
		this.Text1 = StringFormat.format(new Date()) + " <b>" + user.getAtTipperVorname() + " " + user.getAtTipperName() + ": </b>";
		this.Text2 = req.getParameter("text2");

		if(this.isButtonSpeichern() && Text2 != null && Text2.length() > 3)
		{
			EnForum beitrag = new EnForum();
			beitrag.setIdTipper(user.getIdTipper());
			beitrag.setAtForumType("F");
			beitrag.setAtForumText1(this.Text1);
			beitrag.setAtForumText2(this.Text2);
			try
			{
				forumStateless.addForum(beitrag);
			}
			catch (ForumException e)
			{
				log.error("ForumException in Zeile 80 " + e.getMessage()) ;
			}
		}
 	}
	
	private void getResources()
	{
		this.forumStateless = LookupRemoteService.lookupForumBeanRemote();
	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
	public boolean isButtonSpeichern()
	{
		return ButtonSpeichern;
	}
}
