package tables;

import java.util.Iterator;
import java.util.Vector;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.util.HtmlTags;

public class MainTablehead4
{
	String cssweiss = ApplConstants.CSSWEISSL;
	String csshead = ApplConstants.CSSHEAD;
	String cssnoborder = ApplConstants.CSSNOBORDER;
	String csssubmit1 = ApplConstants.CSSSUBMIT1;
	String csssubmit4 = ApplConstants.CSSSUBMIT4;
	
	String hinweis = "";
	EnSpieltag spieltag;
	Vector<EnSpiel> spiele;
	int sortButton;
	boolean tunier;
	boolean owngetippt = true;
	
	public MainTablehead4(boolean tunier, EnSpieltag spieltag, Vector<EnSpiel> spiele)
	{
		super();
		sortButton = -3;
		this.tunier = tunier;
		this.spieltag = spieltag;
		this.spiele = spiele;
	}
	
	public String getHtmlRow()
	{
		if (this.tunier)
		{
			return this.getHtmlRowTunier();
		}
		else
		{
			return this.getHtmlRowLiga();
		}
	}
	
	private String getHtmlRowLiga()
	{
		StringBuffer sb = new StringBuffer("");
		// sb.append(HtmlTags.wrapTH(hinweis, null,
		// spieltag.getAtSpieltagPaarungen()));
		
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHS, HtmlTags.createNBSP(1), cssweiss));
		if (sortButton == -4)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHX, HtmlTags.createSUBMITdisabled("sortname", "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHX, HtmlTags.createSUBMIT("sortname", "Sort", csssubmit1), cssweiss));
		}
		
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createNBSP(1), cssweiss));
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnSpiel spiel = iter1.next();
			if (sortButton == spiel.getIdSpiel())
			{
				if (spieltag.getAtSpieltagIsStarted())
				{
					sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
				}
				else
				{
					if(this.isOwngetippt())
					{
						sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//						HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//						+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
					}
					else
					{
						sb.append(HtmlTags.wrapTH(
								ApplConstants.WIDTHHM,
								HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
										, csshead));
//						HtmlTags.createSUBMITdisabled("info" + spiel.getIdSpiel(), "D", csssubmit4)
//						+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));

					}
				}
			}
			else
			{
				if (spieltag.getAtSpieltagIsStarted())
				{
					sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMIT("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
				}
				else
				{
					if(this.isOwngetippt())
					{
						
						sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//						HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//						+ HtmlTags.createSUBMIT("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
					}
					else
					{
						sb.append(HtmlTags.wrapTH(
								ApplConstants.WIDTHHM,
								HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONINFO  + spiel.getIdSpiel(), "Info", csssubmit1)
										, csshead));
//						HtmlTags.createSUBMITdisabled("info" + spiel.getIdSpiel(), "D", csssubmit4)
//						+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
					}
				}
			}
		}
		if (sortButton == -3)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONSORTPUNKTESPIELTAG, "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMIT(ApplConstants.BUTTONSORTPUNKTESPIELTAG, "Sort", csssubmit1), cssweiss));
		}
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL, HtmlTags.createNBSP(1), cssweiss));
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHS, HtmlTags.createNBSP(5), cssweiss));
		if (sortButton == -2)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONSORTPUNKTEGESAMT, "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMIT(ApplConstants.BUTTONSORTPUNKTEGESAMT, "Sort", csssubmit1), cssweiss));
		}
		if (sortButton == -1)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL, HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONSORTTIPPERKONTO, "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL, HtmlTags.createSUBMIT(ApplConstants.BUTTONSORTTIPPERKONTO, "Sort", csssubmit1), cssweiss));
		}
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL, HtmlTags.createNBSP(1), cssweiss));
		return HtmlTags.wrapTR(sb.toString() + '\n', csshead);
	}
	
	private String getHtmlRowTunier()
	{
		StringBuffer sb = new StringBuffer("");
		
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHS, HtmlTags.createNBSP(1), cssweiss));
		if (sortButton == -4)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHX, HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONSORTNAME, "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHX, HtmlTags.createSUBMIT(ApplConstants.BUTTONSORTNAME, "Sort", csssubmit1), cssweiss));
		}
		
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createNBSP(1), cssweiss));
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnSpiel spiel = iter1.next();
			if (sortButton == spiel.getIdSpiel())
			{
				if (spieltag.getAtSpieltagIsStarted())
				{
					sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
				}
				else
				{
					if(this.owngetippt)
					{
						sb.append(HtmlTags.wrapTH(
								ApplConstants.WIDTHHM,
								HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
										, csshead));
					}
					else
					{
						sb.append(HtmlTags.wrapTH(
								ApplConstants.WIDTHHM,
								HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
										, csshead));
					}
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMITdisabled("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
				}
			}
			else
			{
				if (spieltag.getAtSpieltagIsStarted())
				{
					sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMIT("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
				}
				else
				{
					if(this.owngetippt)
					{
						sb.append(HtmlTags.wrapTH(
							ApplConstants.WIDTHHM,
							HtmlTags.createSUBMIT(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
									, csshead));
//					HtmlTags.createSUBMIT("info" + spiel.getIdSpiel(), "D", csssubmit4)
//					+ HtmlTags.createSUBMIT("sort" + spiel.getIdSpiel(), "S", csssubmit4), csshead));
					}
					else
					{
						sb.append(HtmlTags.wrapTH(
								ApplConstants.WIDTHHM,
								HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONINFO + spiel.getIdSpiel(), "Info", csssubmit1)
										, csshead));
					}
				}
			}
		}
		// if (sortButton == -3)
		// {
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM,
		// HtmlTags.createSUBMITdisabled("sortpunktespieltag", "Sort",
		// csssubmit1), cssweiss));
		// }
		// else
		// {
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM,
		// HtmlTags.createSUBMIT("sortpunktespieltag", "Sort", csssubmit1),
		// cssweiss));
		// }
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL,
		// HtmlTags.createNBSP(1), cssweiss));
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHS,
		// HtmlTags.createNBSP(5), cssweiss));
		if (sortButton == -2)
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMITdisabled(ApplConstants.BUTTONSORTPUNKTEGESAMT, "Sort", csssubmit1), cssweiss));
		}
		else
		{
			sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createSUBMIT(ApplConstants.BUTTONSORTPUNKTEGESAMT, "Sort", csssubmit1), cssweiss));
		}
		sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHM, HtmlTags.createNBSP(1), cssweiss));
		// if (sortButton == -1)
		// {
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL,
		// HtmlTags.createSUBMITdisabled("sorttipperkonto", "Sort", csssubmit1),
		// cssweiss));
		// }
		// else
		// {
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL,
		// HtmlTags.createSUBMIT("sorttipperkonto", "Sort", csssubmit1),
		// cssweiss));
		// }
		// sb.append(HtmlTags.wrapTH(ApplConstants.WIDTHHL,
		// HtmlTags.createNBSP(1), cssweiss));
		return HtmlTags.wrapTR(sb.toString() + '\n', csshead);
	}
	
	public void setSortButton(int s)
	{
		sortButton = s;
	}

	public boolean isOwngetippt()
	{
		return owngetippt;
	}

	public void setOwngetippt(boolean owngetippt)
	{
		this.owngetippt = owngetippt;
	}
}
