import java.util.Scanner;

public class CarPark2 {

	
	
	public static void main(String[] args) {
		int small_parking_no = Integer.parseInt(args[0]);
		int compact_parking_no = Integer.parseInt(args[1]);
		int large_parking_no = Integer.parseInt(args[2]);
		
		
		String carType;
		ParkingMaker pm=new ParkingMaker(small_parking_no,compact_parking_no,large_parking_no);
		Scanner sc = new Scanner(System.in); 
		System.out.println("What vehicle would you like to park?"); 
		while(!(carType = sc.nextLine()).equals("bye")){
			
			
			
			pm.getParkingSpots(carType);
			
			System.out.println("What vehicle would you like to park?"); 
			
		}
		
		
		
		
		
				
				
				
				
				
				
	}

}
