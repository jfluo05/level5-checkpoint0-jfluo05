import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Checkpoint {
	public static void main(String[] args) {
		
//		  The auto-mgp.data file contains the miles-per-gallon data on various different types of cars.
//		  Each line of the file provides the data for one CarMPGEntry object.
//		  (source: http://archive.ics.uci.edu/ml/datasets)
//		  
//		  Use the method provided to get an ArrayList of CarMPGEntry objects. Convert the ArrayList into a Stream.
//		  Using streams, perform the following:
		Stream <CarMPGEntry> cmpg= readCarMPGEntryDataFromFile().stream();
		Stream <CarMPGEntry> cmpg2= readCarMPGEntryDataFromFile().stream();
		Stream <CarMPGEntry> cmpg3= readCarMPGEntryDataFromFile().stream();
		Stream <CarMPGEntry> cmpg4= readCarMPGEntryDataFromFile().stream();
		Stream <CarMPGEntry> cmpg5= readCarMPGEntryDataFromFile().stream();

//		  1. Print the entire list.
		cmpg.forEach((l)->System.out.println(l));
		cmpg.close();
//		  2. Print the miles per gallon of each entry.
		cmpg4.forEach((l)->System.out.println(l.mpg));

//		  3. Print alphabetized car names of the list.
//		cmpg.sorted((CarMPGEntry car1, CarMPGEntry car2)->{
//			return car1.carName.compareTo(car2.carName);
//		});
		
		cmpg2.forEach((l)->System.out.println(l));
//		  
//		  4. Print the list with all 8 cylinder cars removed.
		cmpg5.filter((CarMPGEntry t)->{
			  CarMPGEntry car=t;
			  if (car.cylinders!=8) {
				  System.out.println(car.carName);
			  }
				return car.carName.contains("toyota");
			});
		  
//		  5. Print only the cars with "toyota" in the name.
		  cmpg3.filter((CarMPGEntry t)->{
			  CarMPGEntry car=t;
			  System.out.println(car.carName);
				return car.carName.contains("toyota");
				
			});
		  //cmpg.forEach((l)->System.out.println(l));
	}
	
	public static ArrayList<CarMPGEntry> readCarMPGEntryDataFromFile(){
		ArrayList<CarMPGEntry> carList = new ArrayList<CarMPGEntry>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("auto-mpg.data"));
			
			String line = br.readLine();
			while(line != null) {
				String entry = "";
				ArrayList<String> splitEntry = new ArrayList<String>();
				for(int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if(c == ' ' || c == '\t') {
						if(entry.length() > 0) {
							splitEntry.add(entry);
							entry = "";
						}
					}else if(c =='\"') {
						i++;
						c = line.charAt(i);
						while(c != '\"') {
							entry += c;
							c = line.charAt(++i);
						}
						splitEntry.add(entry);
					}else {
						entry += c;
					}
				}
				
				CarMPGEntry cmpg = new CarMPGEntry();
				
				cmpg.mpg = Float.parseFloat(splitEntry.get(0));
				cmpg.cylinders = Integer.parseInt(splitEntry.get(1));
				cmpg.displacement = Float.parseFloat(splitEntry.get(2));
				try {
					cmpg.horsePower = Float.parseFloat(splitEntry.get(3));
					
				}catch(NumberFormatException e) {
					cmpg.horsePower = -1;
				}
				cmpg.weight = Float.parseFloat(splitEntry.get(4));
				cmpg.acceleration = Float.parseFloat(splitEntry.get(5));
				cmpg.modelYear  = Integer.parseInt(splitEntry.get(6));
				cmpg.origin = Integer.parseInt(splitEntry.get(7));
				cmpg.carName = splitEntry.get(8);
				carList.add(cmpg);

				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return carList;
	}
}
