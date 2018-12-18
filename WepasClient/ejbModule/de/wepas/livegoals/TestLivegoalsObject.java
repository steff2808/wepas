package de.wepas.livegoals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestLivegoalsObject
{
	public static void main(String[] args)
	{
		System.out.println("-->TestLivegoalsObject.main(String[] args");
		FileInputStream fis= null;
		try
		{
			fis = new FileInputStream("/home/steff/lgo1.sav");
		}
		catch (FileNotFoundException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectInputStream ois = null;
		try
		{
			ois = new ObjectInputStream(fis);
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LivegoalsObject lgo = null;
		try
		{
			lgo = (LivegoalsObject) ois.readObject();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lgo.toString());
		System.out.println("-->ENDE TestLivegoalsObject.main(String[] args");
	}
}
