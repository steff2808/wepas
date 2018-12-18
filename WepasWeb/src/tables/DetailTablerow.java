package tables;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.util.HtmlTags;

public class DetailTablerow
{
	private static Log log = LogFactory.getLog(DetailTablerow.class);
	
	String cssjustl = ApplConstants.CSSJUSTL;
	String csshell = ApplConstants.CSSHELL;
	String csseigen = ApplConstants.CSSEIGEN;
	
	String nameS;
	EnTipp tippS;
	String nameU;
	EnTipp tippU;
	String nameN;
	EnTipp tippN;
	
	EnTipper user = null;
	
	public DetailTablerow(EnTipper user, TipperBeanRemote tipperStateless, EnTipp tippS, EnTipp tippU, EnTipp tippN)
	{
		this.user = user;
		this.tippS = null;
		nameS = HtmlTags.createNBSP(1);
		this.tippU = null;
		nameU = HtmlTags.createNBSP(1);
		this.tippN = null;
		nameN = HtmlTags.createNBSP(1);
		
		if(tippS != null)
		{
			try
			{
				EnTipper tipper = tipperStateless.getTipper(tippS.getIdTipper());
				this.tippS = tippS;
				this.nameS = tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname();
			}
			catch(TipperException e)
			{
				log.info("TipperException in Zeile 50 " + e.getMessage());
			}
		}
		if(tippU != null)
		{
			try
			{
				EnTipper tipper = tipperStateless.getTipper(tippU.getIdTipper());
				this.tippU = tippU;
				this.nameU = tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname();
			}
			catch(TipperException e)
			{
				log.info("TipperException in Zeile 63 " + e.getMessage());
			}
		}
		if(tippN != null)
		{
			try
			{
				EnTipper tipper = tipperStateless.getTipper(tippN.getIdTipper());
				this.tippN = tippN;
				this.nameN = tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname();
			}
			catch(TipperException e)
			{
				log.info("TipperException in Zeile 76 " + e.getMessage());
			}
		}
	}
	
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		if(this.tippS == null)
		{
			sb.append(HtmlTags.wrapTD(nameS, cssjustl, 3));	
		}
		else
		{
			if(nameS.equals(user.getAtTipperName() + ", " + user.getAtTipperVorname()))
			{
				sb.append(HtmlTags.wrapTD(nameS, csseigen, 2));	
			}
			else
			{
				sb.append(HtmlTags.wrapTD(nameS, csshell, 2));	
			}
			sb.append(HtmlTags.getTippHTML(tippS));
		}
		if(this.tippU == null)
		{
			sb.append(HtmlTags.wrapTD(nameU, cssjustl, 3));	
		}
		else
		{
			if(nameU.equals(user.getAtTipperName() + ", " + user.getAtTipperVorname()))
			{
				sb.append(HtmlTags.wrapTD(nameU, csseigen, 2));	
			}
			else
			{
				sb.append(HtmlTags.wrapTD(nameU, csshell, 2));	
			}
			sb.append(HtmlTags.getTippHTML(tippU));
		}
		if(this.tippN == null)
		{
			sb.append(HtmlTags.wrapTD(nameN, cssjustl, 3));	
		}
		else
		{
			if(nameN.equals(user.getAtTipperName() + ", " + user.getAtTipperVorname()))
			{
				sb.append(HtmlTags.wrapTD(nameN, csseigen, 2));	
			}
			else
			{
				sb.append(HtmlTags.wrapTD(nameN, csshell, 2));	
			}
			sb.append(HtmlTags.getTippHTML(tippN));
		}
		return HtmlTags.wrapTR(sb.toString(), null);
	}
}
