package exercise2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVInsuranceCompaniesSortedByLastnameAndFirstname {
final static String home="C:\\Users\\19292\\Documents\\Interviews\\Availity\\";
final static String source=home+"Availity.csv";
final static String aetnaFile=home+"aetna.csv";
final static String cignaFile=home+"cigna.csv";
final static String humanaFile=home+"humana.csv";
 static List<String> headers=Arrays.asList(Arrays.stream(Headers.values()).map(Enum::name).toArray(String[]::new));
	public static void main(String[] args) throws IOException {
		Reader in = null;
		List<String> aetna= new ArrayList<>();
		List<String> humana= new ArrayList<>();
		List<String> cigna= new ArrayList<>();
		try {
			in = new FileReader(source);
			@SuppressWarnings("deprecation")
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
			Integer counter=0;
			
			for (CSVRecord record : records) {
			    if(counter==0) {
			    	counter++;
			    	continue;
			    }
				
			  
			    String lastName= record.get(Headers.LAST_NAME);
			    String firstName= record.get(Headers.FIRST_NAME);
			    String userId = record.get(Headers.USER_ID);
			    String version= record.get(Headers.VERSION);
			    String insuranceCompany= record.get(Headers.INSURANCE_COMPANY);
			    if(insuranceCompany.equalsIgnoreCase("Aetna")) {
			   
			    aetna.add(lastName+","+firstName+","+userId+","+version+","+insuranceCompany);	
			    }
			    
			    else if(insuranceCompany.equalsIgnoreCase("Humana")) {
			    	humana.add(lastName+","+firstName+","+userId+","+version+","+insuranceCompany);	
				    }
			    
			    else if(insuranceCompany.equalsIgnoreCase("Cigna")) {
			    	cigna.add(lastName+","+firstName+","+userId+","+version+","+insuranceCompany);	

				    }
			}
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collections.sort(aetna);
		
		for(String aetnaVal: aetna) {
			System.out.println(aetnaVal);
			
		}

		Collections.sort(humana);
		
		for(String humanaVal: humana) {
			System.out.println(humanaVal);
			
		}
		
		Collections.sort(cigna);
		
		for(String cignaVal: cigna) {
			System.out.println(cignaVal);
			
		}
		
		
		
		
		File cignaFilename= new File(cignaFile);
		cignaFilename.createNewFile();
		Path cignaPath = Paths.get(cignaFilename.getAbsolutePath());
		try (BufferedWriter writer = Files.newBufferedWriter(cignaPath)) {  
			 writer.write(headers.stream()
                     .reduce((sum,currLine) ->  sum + ","  + currLine)
                     .get());
			writer.newLine();
		    writer.write(cigna.stream()
		                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
		                      .get());
		} 
		
		File aetnaFilename= new File(aetnaFile);
		aetnaFilename.createNewFile();
		Path aetnaPath = Paths.get(aetnaFilename.getAbsolutePath());
		try (BufferedWriter writer = Files.newBufferedWriter(aetnaPath)) {  
			 writer.write(headers.stream()
                     .reduce((sum,currLine) ->  sum + ","  + currLine)
                     .get());
			writer.newLine();
		    writer.write(aetna.stream()
		                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
		                      .get());
		}
		
		File humanaFilename= new File(humanaFile);
		humanaFilename.createNewFile();
		Path humanaPath = Paths.get(humanaFilename.getAbsolutePath());
		try (BufferedWriter writer = Files.newBufferedWriter(humanaPath)) {  
			 writer.write(headers.stream()
                     .reduce((sum,currLine) ->  sum + ","  + currLine)
                     .get());
			writer.newLine();
		    writer.write(humana.stream()
		                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
		                      .get());
		}
		
		removeDuplicates();
	}
	
	public static void removeDuplicates() throws IOException {
		
		List<String> aetna= new ArrayList<>();
		List<String> humana= new ArrayList<>();
		List<String> cigna= new ArrayList<>();
		
		
		//humana
		Reader in = null;
		
		try {
			in = new FileReader(humanaFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> versionArray= new ArrayList<>();
		Map<String,Integer>map =new TreeMap<>();
		
	
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
		   if(map.containsKey(lastName+","+firstName+","+userId) && map.get(lastName+","+firstName+","+userId)<latestVersion ) {
			  
		    map.put(lastName+","+firstName+","+userId, latestVersion);
			  
			   
		   // System.out.println("I'm here");
		   }
		   else { 
			   
		   map.put(lastName+","+firstName+","+userId, Integer.parseInt(version));
		   }

		  
		    }
		
		 System.out.println("map"+map);
		 
		
		 map.forEach((k, v) -> humana.add(k+","+v+","+"Humana"));
		 
		 System.out.println(humana);
		 
		 File humanaFilename= new File(humanaFile);
			humanaFilename.createNewFile();
			Path humanaPath = Paths.get(humanaFilename.getAbsolutePath());
			try (BufferedWriter writer = Files.newBufferedWriter(humanaPath)) {  
				 writer.write(headers.stream()
	                     .reduce((sum,currLine) ->  sum + ","  + currLine)
	                     .get());
				writer.newLine();
			    writer.write(humana.stream()
			                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
			                      .get());
			}
			
			
			
			//Cigna
			
			
			 in = null;
			
			try {
				in = new FileReader(cignaFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 records = null;
			try {
				records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			map =new TreeMap<>();
			
		
			 counter=0;
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
			   if(map.containsKey(lastName+","+firstName+","+userId) && map.get(lastName+","+firstName+","+userId)<latestVersion ) {
				  
			    map.put(lastName+","+firstName+","+userId, latestVersion);
				  
				   
			   // System.out.println("I'm here");
			   }
			   else { 
				   
			   map.put(lastName+","+firstName+","+userId, Integer.parseInt(version));
			   }

			  
			    }
			
			 System.out.println("map"+map);
			 
			
			 map.forEach((k, v) -> cigna.add(k+","+v+","+"Cigna"));
			 
			 System.out.println(cigna);
			 
			 File cignaFilename= new File(cignaFile);
			 		cignaFilename.createNewFile();
				Path cignaPath = Paths.get(cignaFilename.getAbsolutePath());
				try (BufferedWriter writer = Files.newBufferedWriter(cignaPath)) {  
					 writer.write(headers.stream()
		                     .reduce((sum,currLine) ->  sum + ","  + currLine)
		                     .get());
					writer.newLine();
				    writer.write(cigna.stream()
				                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
				                      .get());
				}
			
			
			//Aetna
				
				
				
				 in = null;
				
				try {
					in = new FileReader(aetnaFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 records = null;
				try {
					records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				map =new TreeMap<>();
				
			
				 counter=0;
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
				   if(map.containsKey(lastName+","+firstName+","+userId) && map.get(lastName+","+firstName+","+userId)<latestVersion ) {
					  
				    map.put(lastName+","+firstName+","+userId, latestVersion);
					  
					   
				 //   System.out.println("I'm here");
				   }
				   else { 
					   
				   map.put(lastName+","+firstName+","+userId, Integer.parseInt(version));
				   }

				  
				    }
				
				 System.out.println("map"+map);
				 
				
				 map.forEach((k, v) -> aetna.add(k+","+v+","+"Aetna"));
				 
				 System.out.println(aetna);
				 
				 File aetnaFilename= new File(aetnaFile);
				 		aetnaFilename.createNewFile();
					Path aetnaPath = Paths.get(aetnaFilename.getAbsolutePath());
					try (BufferedWriter writer = Files.newBufferedWriter(aetnaPath)) {  
						 writer.write(headers.stream()
			                     .reduce((sum,currLine) ->  sum + ","  + currLine)
			                     .get());
						writer.newLine();
					    writer.write(aetna.stream()
					                      .reduce((sum,currLine) ->  sum + "\n"  + currLine)
					                      .get());
					}
			
	}

}
