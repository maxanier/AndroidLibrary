package de.maxgb.android.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.text.format.DateFormat;
import android.util.Log;

public class Logger {

	private static boolean debug=false;
	private static File logFile;
	private static File logFile2;
	private static String directory;
	private static final String log_file_name="log.txt";
	private static final String log_file_name2="log.old.txt";
	private Logger(){

		
	}
	public static void init(String pdirectory){
		if(logFile==null){
			directory=pdirectory;
			(new File(directory)).mkdir();
			logFile=new File(directory+log_file_name);
			logFile2=new File(directory+log_file_name2);
			logFile2.delete();
			logFile.renameTo(logFile2);
			logFile=new File(directory+log_file_name);
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			log("\n Neustart \n");
		}
	}
	
	
	public static  void setDebugMode(boolean mode){
		debug=mode;
	}
	
	public static boolean getDebugMode(){
		return debug;
	}
	public static void i(String tag,String msg){
		Log.i(tag,msg);
		log("INFO "+tag+": "+msg);
	}
	public static void w(String tag,String msg){
		Log.w(tag,msg);
		log("WARNING "+tag+": "+msg);
	}
	public static void e(String tag,String msg){
		Log.e(tag, msg);
		log("ERROR "+tag+": "+msg);
	}
	public static void e(String tag,String msg,Throwable t){
		Log.e(tag, msg, t);
		log("ERROR "+tag+": "+msg+ "\nERROR-MESSAGE: "+t.getMessage());
	}
	
	private static void log(String msg){
		if(debug&&logFile!=null){
			try {
				
				BufferedWriter output=new BufferedWriter(new FileWriter(logFile,true));

				output.append(DateFormat.format("MM-dd hh:mm:ss", new java.util.Date())+" "+msg+"\n");
				output.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	public static File getLogFile(){
		return logFile;
	}
	public static File getOldLogFile(){
		return logFile2;
	}
	

}
