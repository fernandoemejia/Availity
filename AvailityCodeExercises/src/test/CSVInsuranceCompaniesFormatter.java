package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import exercise2.Headers;

public class CSVInsuranceCompaniesFormatter {

	public static void main(String[] args) {
		Reader in = null;
		List<String> aetna= new ArrayList<>();
		List<String> humana= new ArrayList<>();
		List<String> cigna= new ArrayList<>();
		try {
			in = new FileReader("C:\\Users\\19292\\Documents\\Interviews\\Availity\\InsuranceCompanies.csv");
			@SuppressWarnings("deprecation")
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
			Integer counter=0;
			
			for (CSVRecord record : records) {
			    if(counter==0) {
			    	counter++;
			    	continue;
			    }
				String userId = record.get(Headers.USER_ID);
			    String firstName= record.get(Headers.FIRST_NAME);
			    String lastName= record.get(Headers.LAST_NAME);
			    String version= record.get(Headers.VERSION);
			    String insuranceCompany= record.get(Headers.INSURANCE_COMPANY);
			    if(insuranceCompany.equalsIgnoreCase("Aetna")) {
			   
			    aetna.add(userId+","+firstName+","+lastName+","+version+","+insuranceCompany);	
//			    System.out.print(userId+" ");
//			    System.out.print(firstName+" ");
//			    System.out.print(lastName+" ");
//			    System.out.print(version+" ");
//			    System.out.print(insuranceCompany+" ");
//			    System.out.println();
			    }
			    
			    else if(insuranceCompany.equalsIgnoreCase("Humana")) {
			    	humana.add(userId+","+firstName+","+lastName+","+version+","+insuranceCompany);	
//				    System.out.print(userId+" ");
//				    System.out.print(firstName+" ");
//				    System.out.print(lastName+" ");
//				    System.out.print(version+" ");
//				    System.out.print(insuranceCompany+" ");
//				    System.out.println();
				    }
			    
			    else if(insuranceCompany.equalsIgnoreCase("Cigna")) {
			    	cigna.add(userId+","+firstName+","+lastName+","+version+","+insuranceCompany);	
//				    System.out.print(userId+" ");
//				    System.out.print(firstName+" ");
//				    System.out.print(lastName+" ");
//				    System.out.print(version+" ");
//				    System.out.print(insuranceCompany+" ");
//				    System.out.println();
				    }
			  
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			@SuppressWarnings("deprecation")
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(String aetnaVal: aetna) {
			System.out.println(aetnaVal);
			
		}
		
		for(String humanaVal: humana) {
			System.out.println(humanaVal);
			
		}
		
		for(String cignaVal: cigna) {
			System.out.println(cignaVal);
			
		}

	}

}
