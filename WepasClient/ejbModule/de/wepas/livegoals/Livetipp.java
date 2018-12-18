package de.wepas.livegoals;

import de.wepas.jpa.EnTipp;

public class Livetipp
{
	private EnTipp tipp;
	private int livepunkte;
	private int livepunktehist;
	
	public Livetipp(EnTipp tipp)
	{
		super();
		this.tipp = tipp;
		this.livepunkte = 0;
		this.livepunktehist = 0;
	}

	public int getLivepunkte()
	{
		return livepunkte;
	}

	public void setLivepunkte(int livepunkte)
	{
		this.livepunkte = livepunkte;
	}

	public int getLivepunktehist()
	{
		return livepunktehist;
	}

	public void setLivepunktehist(int livepunktehist)
	{
		this.livepunktehist = livepunktehist;
	}

	public EnTipp getTipp()
	{
		return tipp;
	}
}
