package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import exercise2.Headers;

public class CSVFormatter {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

	Reader in = null;
	
	try {
		in = new FileReader("C:\\Users\\19292\\Documents\\Interviews\\Availity\\Availity.csv");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	@SuppressWarnings("deprecation")
	Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
	List<Integer> versionArray= new ArrayList<>();
	Map<String,Integer>map =new TreeMap<>();
	
	int highestVersion=0;
	int counter=0;
	for (CSVRecord record : records) {
	    String userId = record.get(Headers.USER_ID);
	    String firstName= record.get(Headers.FIRST_NAME);
	    String lastName= record.get(Headers.LAST_NAME);
	    String version= record.get(Headers.VERSION);
	    String insuranceCompany= record.get(Headers.INSURANCE_COMPANY);
	    if(counter==0) {
	    	counter++;
	    	continue;
	    }
	    
	   
	   Integer latestVersion=Integer.parseInt(version);
	   if(map.containsKey(userId+firstName) && map.get(userId+firstName+lastName)<latestVersion ) {
		  
	    map.put(userId+firstName+lastName, latestVersion);
		   
		   
	    System.out.println("I'm here");
	   }
	   else { 
		   
	   map.put(userId+firstName+lastName, Integer.parseInt(version));
	   }

	  
	    }
	
	 System.out.println("map"+map);
	
	
	
	}
	}


