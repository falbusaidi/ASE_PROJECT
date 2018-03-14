package Singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log
{
	private static Log instance;
	private int number;
	private String nameFile = "Log.txt";
	private File logFile = new File(nameFile);
	private StringBuffer StringLog = new StringBuffer();
	
	private Log()
	{
		this.number = 0;
	}
	
	public int getCurrent()
	{
		return number;
	}
	
	public int getNext()
	{
		number++;
		return number;
	}
	
	public static synchronized Log getInstance()
	{
		synchronized(Log.class)
		{
			if(instance == null)
			{
				instance = new Log();
			}
		}
		return instance;
	}
	
	public synchronized void addEvent(String Event)
	{
		String timelog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		int Lognumber = getNext();
		StringLog.append(Lognumber+" "+timelog+" "+Event);
		StringLog.append(System.getProperty("line.separator"));
	}
	
	public synchronized void writeLog()
	{
		BufferedWriter WritingFile = null;
		try
		{
			WritingFile = new BufferedWriter(new FileWriter(logFile));
			WritingFile.write(StringLog.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				WritingFile.close();
			}
			catch(Exception e)
			{}
		}
	}
}