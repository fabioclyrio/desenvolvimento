package br.com.elo7.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DataUtils {

	protected static Logger logger = Logger.getLogger(DataUtils.class);
	
	private DataUtils(){
		
	}
	
	public static java.util.Date formatarDataString(String dataAformatar) {
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = null;
		try {
			date = (java.util.Date)fmt.parse(dataAformatar);
		} catch (ParseException e) {
			logger.error("DataUtils: formatarDataString: " + e.getMessage());
		}
		return date;
	}
	
	public static java.util.Date adicionarData(int valor){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, valor);
		return calendar.getTime();
	}
	
}
