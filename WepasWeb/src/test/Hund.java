package test;

public class Hund
{
	String rasse;
	int alter;
	public Hund(String rasse, int alter)
	{
		super();
		this.rasse = rasse;
		this.alter = alter;
	}
	@Override
	public String toString()
	{
		return "Hund [rasse=" + rasse + ", alter=" + alter + "]";
	}
}	

