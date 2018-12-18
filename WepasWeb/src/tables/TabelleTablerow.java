package tables;

import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnVerein;
import de.wepas.util.HtmlTags;

public class TabelleTablerow
{
	String csstdgruenr = ApplConstants.CSSTDGRUENR;
	String csstdweissr = ApplConstants.CSSTDWEISSR;
	String csstdrotr = ApplConstants.CSSTDROTR;
	String csstdgelbr = ApplConstants.CSSTDGELBR;
	String csstdgraur = ApplConstants.CSSTDGRAUR;

	String cssweissr = ApplConstants.CSSWEISSR;
	String cssweissl = ApplConstants.CSSWEISSL;
	String cssdrotr = ApplConstants.CSSDROTR;
	String cssdrotl = ApplConstants.CSSDROTL;
	String cssrotr = ApplConstants.CSSROTR;
	String cssrotl = ApplConstants.CSSROTL;
	String cssdgruenr = ApplConstants.CSSDGRUENR;
	String cssdgruenl = ApplConstants.CSSDGRUENL;
	String cssgruenr = ApplConstants.CSSGRUENR;
	String cssgruenl = ApplConstants.CSSGRUENL;
	String cssdgraur = ApplConstants.CSSDGRAUR;
	String cssdgraul = ApplConstants.CSSDGRAUL;
	String cssgraur = ApplConstants.CSSGRAUR;
	String cssgraul = ApplConstants.CSSGRAUL;
	String cssgelbr = ApplConstants.CSSGELBR;
	String csstable0 = ApplConstants.CSSTABLE0;
	
	int platz;
	private EnVerein verein;
	int sp;	 int g;	 int u;	 int v;	 int tG;  int tK;  int p;
	int spH; int gH; int uH; int vH; int tGH; int tKH; int pH;
	int spA; int gA; int uA; int vA; int tGA; int tKA; int pA;
	int spHr; int gHr; int uHr; int vHr; int tGHr; int tKHr; int pHr;
	int spRr; int gRr; int uRr; int vRr; int tGRr; int tKRr; int pRr;
	int spT; int gT; int uT; int vT; int tGT; int tKT; int pT;
	String formG = null;
	String formH = null;
	String formA = null;
	String formGd = null;
	String formHd = null;
	String formAd = null;
	
	public TabelleTablerow(EnVerein verein, boolean tipptabelle)
	{
		this.verein = verein;
		this.sp = verein.getAtVereinSp();
		this.g = verein.getAtVereinSi();
		this.u = verein.getAtVereinUn();
		this.v = verein.getAtVereinNi();
		this.tG = verein.getAtVereinTg();
		this.tK = verein.getAtVereinTk();
		this.p = verein.getAtVereinPunkte();
		this.spH = verein.getAtVereinHSp();
		this.gH = verein.getAtVereinHSi();
		this.uH = verein.getAtVereinHUn();
		this.vH = verein.getAtVereinHNi();
		this.tGH = verein.getAtVereinHTg();
		this.tKH = verein.getAtVereinHTk();
		this.pH = verein.getAtVereinHPunkte();
		this.spA = verein.getAtVereinASp();
		this.gA = verein.getAtVereinASi();
		this.uA = verein.getAtVereinAUn();
		this.vA = verein.getAtVereinANi();
		this.tGA = verein.getAtVereinATg();
		this.tKA = verein.getAtVereinATk();
		this.pA = verein.getAtVereinAPunkte();
		this.spHr = verein.getAtVereinHRSp();
		this.gHr = verein.getAtVereinHRSi();
		this.uHr = verein.getAtVereinHRUn();
		this.vHr = verein.getAtVereinHRNi();
		this.tGHr = verein.getAtVereinHRTg();
		this.tKHr = verein.getAtVereinHRTk();
		this.pHr = verein.getAtVereinHRPunkte();
		this.spRr = verein.getAtVereinRRSp();
		this.gRr = verein.getAtVereinRRSi();
		this.uRr = verein.getAtVereinRRUn();
		this.vRr = verein.getAtVereinRRNi();
		this.tGRr = verein.getAtVereinRRTg();
		this.tKRr = verein.getAtVereinRRTk();
		this.pRr = verein.getAtVereinRRPunkte();
		if(tipptabelle)
		{
			return;
		}
		
		char[] formstring = this.verein.getAtVereinForm().toCharArray();
		
//GesamtAktiv		
		int fp = 0;
		StringBuffer sb = new StringBuffer();
		int i = this.verein.getAtVereinSp() * 2;
		boolean go = true;
		int success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 's' || formstring[i] == 'S')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgruenr));
				}
				if(formstring[i] == 'u' || formstring[i] == 'U')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgelbr));
				}
				if(formstring[i] == 'n' || formstring[i] == 'N')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdrotr));
				}
			}
			if(success == 5)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdweissr));
		formG = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssweissr), csstable0);
		
//GesamtPassiv		
		fp = 0;
		sb = new StringBuffer();
		i = this.verein.getAtVereinSp() * 2;
		go = true;
		success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 's' || formstring[i] == 'S')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgraur));
				}
				if(formstring[i] == 'u' || formstring[i] == 'U')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgraur));
				}
				if(formstring[i] == 'n' || formstring[i] == 'N')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdgraur));
				}
			}
			if(success == 5)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdgraur));
		formGd = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssdgraur), csstable0);
		
//HeimAktiv
		fp = 0;
		sb = new StringBuffer();
		i = this.verein.getAtVereinSp() * 2;
		go = true;
		success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 's')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgruenr));
				}
				if(formstring[i] == 'u')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgelbr));
				}
				if(formstring[i] == 'n')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdrotr));
				}
			}
			if(success == 3)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdweissr));
		formH = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssweissr), csstable0);
		
//HeimPassiv
		fp = 0;
		sb = new StringBuffer();
		i = this.verein.getAtVereinSp() * 2;
		go = true;
		success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 's')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgraur));
				}
				if(formstring[i] == 'u')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgraur));
				}
				if(formstring[i] == 'n')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdgraur));
				}
			}
			if(success == 3)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdgraur));
		formHd = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssdgraur), csstable0);
		
//AuswärtsAktiv
		fp = 0;
		sb = new StringBuffer();
		i = this.verein.getAtVereinSp() * 2;
		go = true;
		success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 'S')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgruenr));
				}
				if(formstring[i] == 'U')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgelbr));
				}
				if(formstring[i] == 'N')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdrotr));
				}
			}
			if(success == 3)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdweissr));
		formA = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssweissr), csstable0);

//AuswärtsPassiv		
		fp = 0;
		sb = new StringBuffer();
		i = this.verein.getAtVereinSp() * 2;
		go = true;
		success = 0;
		while(go)
		{
			i--;
			if(i < 0)
			{
				go = false;
			}
			else
			{
				if(formstring[i] == 'S')
				{
					success++;
					fp = fp + 3;
					sb.append(HtmlTags.wrapTD("S", csstdgraur));
				}
				if(formstring[i] == 'U')
				{
					success++;
					fp++;
					sb.append(HtmlTags.wrapTD("U", csstdgraur));
				}
				if(formstring[i] == 'N')
				{
					success++;
					sb.append(HtmlTags.wrapTD("N", csstdgraur));
				}
			}
			if(success == 3)
			{
				go = false;
			}
		}
		sb.append(HtmlTags.wrapTD(Integer.toString(fp), csstdgraur));
		formAd = HtmlTags.wrapTABLE(HtmlTags.wrapTR(sb.toString(), cssdgraur), csstable0);
	}
	
	public void countGame(EnSpiel spiel)
	{
		if(!spiel.getAtSpielIsPlayed())
		{
			return;
		}
		if(verein.getIdVerein() == spiel.getFkSpielVereinHeim())
		{
			sp++;
			spH++;
			//Heimspiel
			if(spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast())
			{
				p++;	p++;	p++;
				pH++;	pH++;	pH++;
				g++;
				gH++;
			}
			if(spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast())
			{
				p++;
				pH++;
				u++;
				uH++;
			}
			if(spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
			{
				v++;
				vH++;
			}
			tG = tG + spiel.getAtSpielToreHeim();
			tK = tK + spiel.getAtSpielToreGast();
			tGH = tGH + spiel.getAtSpielToreHeim();
			tKH = tKH + spiel.getAtSpielToreGast();
		}
		if(verein.getIdVerein() == spiel.getFkSpielVereinGast())
		{
			sp++;
			spA++;
			//Auswärtspiel
			if(spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
			{
				p++;	p++;	p++;
				pA++;	pA++;	pA++;
				g++;
				gA++;
			}
			if(spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast())
			{
				p++;
				pA++;
				u++;
				uA++;
			}
			if(spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast())
			{
				v++;
				vA++;
			}
			tG = tG + spiel.getAtSpielToreGast();
			tK = tK + spiel.getAtSpielToreHeim();
			tGA = tGA + spiel.getAtSpielToreGast();
			tKA = tKA + spiel.getAtSpielToreHeim();
		}
	}
	public void countTipp(EnTipp tipp, EnSpiel spiel)
	{
		if(verein.getIdVerein() == spiel.getFkSpielVereinHeim())
		{
			spT++;
			//Heimspiel
			if(tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
			{
				pT++;	pT++;	pT++;
				gT++;
			}
			if(tipp.getAtTippTorHeim( )== tipp.getAtTippTorGast())
			{
				pT++;
				uT++;
			}
			if(tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
			{
				vT++;
			}
			tGT = tGT + tipp.getAtTippTorHeim();
			tKT = tKT + tipp.getAtTippTorGast();
		}
		if(verein.getIdVerein() == spiel.getFkSpielVereinGast())
		{
			spT++;
			//Auswärtspiel
			if(tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
			{
				pT++;	pT++;	pT++;
				gT++;
			}
			if(tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
			{
				pT++;
				uT++;
			}
			if(tipp.getAtTippTorHeim()> tipp.getAtTippTorGast())
			{
				vT++;
			}
			tGT = tGT + tipp.getAtTippTorGast();
			tKT = tKT + tipp.getAtTippTorHeim();
		}
	}
	
	public String getHtmlRowG()
	{
		StringBuffer sb = new StringBuffer("");
		if(platz < 4)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdgruenl));
		}
		else if(platz < 7)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgruenl));
		}
		else if(platz > 16)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdrotl));
		}
		else if(platz > 15)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssrotl));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgraur));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgraur));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgraul));
		}
		sb.append(HtmlTags.wrapTD(String.valueOf(formG), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(sp), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(g), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(u), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(v), cssweissr));
		sb.append(HtmlTags.wrapTD(tG + HtmlTags.createDOPPELPUNKT() + tK, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tG - tK), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(p), cssgelbr));
		
		sb.append(HtmlTags.wrapTD(String.valueOf(formHd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(spH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(gH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(uH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(vH), cssdgraur));
		sb.append(HtmlTags.wrapTD(tGH + HtmlTags.createDOPPELPUNKT() + tKH, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGH - tKH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(pH), cssdgraur));
	
		sb.append(HtmlTags.wrapTD(String.valueOf(formAd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(spA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(gA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(uA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(vA), cssdgraur));
		sb.append(HtmlTags.wrapTD(tGA + HtmlTags.createDOPPELPUNKT() + tKA, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGA - tKA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(pA), cssdgraur));
	
		return HtmlTags.wrapTR(sb.toString(), null);
	}

	public String getHtmlRowH()
	{
		StringBuffer sb = new StringBuffer("");
		if(platz < 4)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdgruenl));
		}
		else if(platz < 7)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgruenl));
		}
		else if(platz > 16)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdrotl));
		}
		else if(platz > 15)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssrotl));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgraur));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgraur));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgraul));
		}
		sb.append(HtmlTags.wrapTD(String.valueOf(formGd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(sp), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(g), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(u), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(v), cssdgraur));
		sb.append(HtmlTags.wrapTD(tG + HtmlTags.createDOPPELPUNKT() + tK, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tG - tK), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(p), cssdgraur));
		
		sb.append(HtmlTags.wrapTD(String.valueOf(formH), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(spH), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(gH), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(uH), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(vH), cssweissr));
		sb.append(HtmlTags.wrapTD(tGH + HtmlTags.createDOPPELPUNKT() + tKH, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGH - tKH), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(pH), cssgelbr));
	
		sb.append(HtmlTags.wrapTD(String.valueOf(formAd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(spA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(gA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(uA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(vA), cssdgraur));
		sb.append(HtmlTags.wrapTD(tGA + HtmlTags.createDOPPELPUNKT() + tKA, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGA - tKA), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(pA), cssdgraur));
	
		return HtmlTags.wrapTR(sb.toString(), null);
	}
	public String getHtmlRowA()
	{
		StringBuffer sb = new StringBuffer("");
		if(platz < 4)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdgruenl));
		}
		else if(platz < 7)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgruenr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgruenr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgruenl));
		}
		else if(platz > 16)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssdrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssdrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssdrotl));
		}
		else if(platz > 15)
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssrotr));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssrotr));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssrotl));
		}
		else
		{
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssgraur));
			sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssgraur));
			sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssgraul));
		}
		sb.append(HtmlTags.wrapTD(String.valueOf(formGd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(sp), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(g), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(u), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(v), cssdgraur));
		sb.append(HtmlTags.wrapTD(tG + HtmlTags.createDOPPELPUNKT() + tK, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tG - tK), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(p), cssdgraur));
		
		sb.append(HtmlTags.wrapTD(String.valueOf(formHd), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(spH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(gH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(uH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(vH), cssdgraur));
		sb.append(HtmlTags.wrapTD(tGH + HtmlTags.createDOPPELPUNKT() + tKH, cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGH - tKH), cssdgraur));
		sb.append(HtmlTags.wrapTD(String.valueOf(pH), cssdgraur));
	
		sb.append(HtmlTags.wrapTD(String.valueOf(formA), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(spA), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(gA), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(uA), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(vA), cssweissr));
		sb.append(HtmlTags.wrapTD(tGA + HtmlTags.createDOPPELPUNKT() + tKA, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGA - tKA), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(pA), cssgelbr));
	
		return HtmlTags.wrapTR(sb.toString(), null);
	}
	public String getHtmlRowHr()
	{
//		log.info("public String getHtmlRow()");
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssweissr));
		sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssweissl));
		sb.append(HtmlTags.wrapTD(String.valueOf(spHr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(gHr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(uHr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(vHr), cssweissr));
		sb.append(HtmlTags.wrapTD(tGHr + HtmlTags.createDOPPELPUNKT() + tKHr, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGHr - tKHr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(pHr), cssgelbr));
		return HtmlTags.wrapTR(sb.toString(), null);
	}
	public String getHtmlRowRr()
	{
//		log.info("public String getHtmlRow()");
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssweissr));
		sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssweissl));
		sb.append(HtmlTags.wrapTD(String.valueOf(spRr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(gRr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(uRr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(vRr), cssweissr));
		sb.append(HtmlTags.wrapTD(tGRr + HtmlTags.createDOPPELPUNKT() + tKRr, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGRr - tKRr), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(pRr), cssgelbr));
		return HtmlTags.wrapTR(sb.toString(), null);
	}

	public String getHtmlRowT()
	{
//		log.info("public String getHtmlRowT()");
		StringBuffer sb = new StringBuffer("");
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapB(platz + "."), cssweissr));
		sb.append(HtmlTags.wrapTD(HtmlTags.wrapIMG35(verein.getAtVereinKurz(), "vereine/gross/"), cssweissr));
		sb.append(HtmlTags.wrapTD(verein.getAtVereinName(), cssweissl));
		sb.append(HtmlTags.wrapTD(String.valueOf(spT), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(gT), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(uT), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(vT), cssweissr));
		sb.append(HtmlTags.wrapTD(tGT + HtmlTags.createDOPPELPUNKT() + tKT, cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(tGT - tKT), cssweissr));
		sb.append(HtmlTags.wrapTD(String.valueOf(pT), cssgelbr));
		return HtmlTags.wrapTR(sb.toString(), null);
	}

	public EnVerein getVerein()
	{
		return verein;
	}

	public int getG()
	{
		return g;
	}

	public int getU()
	{
		return u;
	}

	public int getV()
	{
		return v;
	}

	public int gettG()
	{
		return tG;
	}

	public int gettK()
	{
		return tK;
	}

	public int getP()
	{
		return p;
	}

	public int getgH()
	{
		return gH;
	}

	public int getuH()
	{
		return uH;
	}

	public int getvH()
	{
		return vH;
	}

	public int gettGH()
	{
		return tGH;
	}

	public int gettKH()
	{
		return tKH;
	}

	public int getpH()
	{
		return pH;
	}

	public int getgA()
	{
		return gA;
	}

	public int getuA()
	{
		return uA;
	}

	public int getvA()
	{
		return vA;
	}

	public int gettGA()
	{
		return tGA;
	}

	public int gettKA()
	{
		return tKA;
	}

	public int getpA()
	{
		return pA;
	}
	
	public int getSp()
	{
		return sp;
	}

	public void setSp(int sp)
	{
		this.sp = sp;
	}

	public int getSpH()
	{
		return spH;
	}

	public void setSpH(int spH)
	{
		this.spH = spH;
	}

	public int getSpA()
	{
		return spA;
	}

	public void setSpA(int spA)
	{
		this.spA = spA;
	}

	public int getSpHr()
	{
		return spHr;
	}

	public void setSpHr(int spHr)
	{
		this.spHr = spHr;
	}

	public int getgHr()
	{
		return gHr;
	}

	public void setgHr(int gHr)
	{
		this.gHr = gHr;
	}

	public int getuHr()
	{
		return uHr;
	}

	public void setuHr(int uHr)
	{
		this.uHr = uHr;
	}

	public int getvHr()
	{
		return vHr;
	}

	public void setvHr(int vHr)
	{
		this.vHr = vHr;
	}

	public int gettGHr()
	{
		return tGHr;
	}

	public void settGHr(int tGHr)
	{
		this.tGHr = tGHr;
	}

	public int gettKHr()
	{
		return tKHr;
	}

	public void settKHr(int tKHr)
	{
		this.tKHr = tKHr;
	}

	public int getpHr()
	{
		return pHr;
	}

	public void setpHr(int pHr)
	{
		this.pHr = pHr;
	}

	public int getSpRr()
	{
		return spRr;
	}

	public void setSpRr(int spRr)
	{
		this.spRr = spRr;
	}

	public int getgRr()
	{
		return gRr;
	}

	public void setgRr(int gRr)
	{
		this.gRr = gRr;
	}

	public int getuRr()
	{
		return uRr;
	}

	public void setuRr(int uRr)
	{
		this.uRr = uRr;
	}

	public int getvRr()
	{
		return vRr;
	}

	public void setvRr(int vRr)
	{
		this.vRr = vRr;
	}

	public int gettGRr()
	{
		return tGRr;
	}

	public void settGRr(int tGRr)
	{
		this.tGRr = tGRr;
	}

	public int gettKRr()
	{
		return tKRr;
	}

	public void settKRr(int tKRr)
	{
		this.tKRr = tKRr;
	}

	public int getpRr()
	{
		return pRr;
	}

	public void setpRr(int pRr)
	{
		this.pRr = pRr;
	}

	public int getSpT()
	{
		return spT;
	}

	public void setSpT(int spT)
	{
		this.spT = spT;
	}

	public int getPlatz()
	{
		return platz;
	}

	public void setVerein(EnVerein verein)
	{
		this.verein = verein;
	}

	public void setG(int g)
	{
		this.g = g;
	}

	public void setU(int u)
	{
		this.u = u;
	}

	public void setV(int v)
	{
		this.v = v;
	}

	public void settG(int tG)
	{
		this.tG = tG;
	}

	public void settK(int tK)
	{
		this.tK = tK;
	}

	public void setP(int p)
	{
		this.p = p;
	}

	public void setgH(int gH)
	{
		this.gH = gH;
	}

	public void setuH(int uH)
	{
		this.uH = uH;
	}

	public void setvH(int vH)
	{
		this.vH = vH;
	}

	public void settGH(int tGH)
	{
		this.tGH = tGH;
	}

	public void settKH(int tKH)
	{
		this.tKH = tKH;
	}

	public void setpH(int pH)
	{
		this.pH = pH;
	}

	public void setgA(int gA)
	{
		this.gA = gA;
	}

	public void setuA(int uA)
	{
		this.uA = uA;
	}

	public void setvA(int vA)
	{
		this.vA = vA;
	}

	public void settGA(int tGA)
	{
		this.tGA = tGA;
	}

	public void settKA(int tKA)
	{
		this.tKA = tKA;
	}

	public void setpA(int pA)
	{
		this.pA = pA;
	}

	public void setgT(int gT)
	{
		this.gT = gT;
	}

	public void setuT(int uT)
	{
		this.uT = uT;
	}

	public void setvT(int vT)
	{
		this.vT = vT;
	}

	public void settGT(int tGT)
	{
		this.tGT = tGT;
	}

	public void settKT(int tKT)
	{
		this.tKT = tKT;
	}

	public void setpT(int pT)
	{
		this.pT = pT;
	}

	public int getgT()
	{
		return gT;
	}

	public int getuT()
	{
		return uT;
	}

	public int getvT()
	{
		return vT;
	}

	public int gettGT()
	{
		return tGT;
	}

	public int gettKT()
	{
		return tKT;
	}

	public int getpT()
	{
		return pT;
	}
	
	public void setPlatz(int platz)
	{
		this.platz = platz;
	}
}
