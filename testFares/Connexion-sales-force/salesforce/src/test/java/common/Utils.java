package common;

import java.io.File;
import java.util.Date;
import java.time.ZoneId;
import java.util.TimeZone;
import java.io.InputStream;
import java.util.Properties;
import java.text.DateFormat;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.zip.ZipOutputStream;

public class Utils {
	
	public static String getProperty(String property) throws Exception{
		InputStream input = new FileInputStream("src/test/resources/properties/properties-"+getClient());
        Properties properties = new Properties();
        properties.load(input);
        return(properties.getProperty(property));
	}
	
	public static String geDateOfTimeZone(String timeZone,String format) throws Exception{
		DateFormat dateFormater= new SimpleDateFormat(format);
		dateFormater.setTimeZone(TimeZone.getTimeZone(timeZone));
		return dateFormater.format(new Date());
	}
	
	public static String geDateOfCurrentTimeZone(String format) throws Exception{
		DateFormat dateFormater= new SimpleDateFormat(format);
		return dateFormater.format(new Date());
	}
	
	public static String convertDateToTimeZone(String date,String timeZoneInput,String inputFormat,String outputFormat) throws Exception{
		DateFormat InFormatter= new SimpleDateFormat(inputFormat);
		InFormatter.setTimeZone(TimeZone.getTimeZone(timeZoneInput));
		Date inputDate = InFormatter.parse(date);	
		DateFormat outFormater= new SimpleDateFormat(outputFormat);
		outFormater.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault().toString()));
		return outFormater.format(inputDate);
	}
	
	public static String convertDateFormat(String date,String inputFormat,String outputFormat) throws Exception{
		DateFormat InFormatter= new SimpleDateFormat(inputFormat);
		Date inputDate = InFormatter.parse(date);	
		DateFormat outFormater= new SimpleDateFormat(outputFormat);
		return outFormater.format(inputDate);
	}
	
	public static Date convertStringToDate(String date,String inputFormat) throws Exception{
		DateFormat InFormatter= new SimpleDateFormat(inputFormat);
		return(InFormatter.parse(date));
	}
	
	public static void putFolderFilesInZip(String source,String destination,String name) throws Exception{
		byte[] buffer = new byte[1024];	 
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destination+"/"+name));
        File sourceDir = new File(source);
        File[] files = sourceDir.listFiles();
        for (int i = 0; i < files.length; i++) {
        FileInputStream fis = new FileInputStream(files[i]);
        zos.putNextEntry(new ZipEntry(files[i].getName())); int length;
        while ((length = fis.read(buffer)) > 0) { zos.write(buffer, 0, length); }
        zos.closeEntry(); fis.close(); } zos.close();
	}
		
	public static String getClient() throws Exception{
		InputStream input = new FileInputStream("src/test/resources/properties/client");
        Properties properties = new Properties(); properties.load(input);
        return(properties.getProperty("ClientId"));
	}

}
