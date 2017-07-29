import java.util.Scanner;

public class Carpark {
	
	public static void main(String[] args) {
		int small_parking_no = Integer.parseInt(args[0]);
		int compact_parking_no = Integer.parseInt(args[1]);
		int large_parking_no = Integer.parseInt(args[2]);
		

		
		String carType;
		ParkingProducer pd=new ParkingProducer(small_parking_no,compact_parking_no,large_parking_no);
		Scanner sc = new Scanner(System.in); 
		System.out.println("What vehicle would you like to park?"); 
		while(!(carType = sc.nextLine()).equals("bye")){
			
			
			Parking p1=pd.getParkingSpots(carType);
			if(p1!=null){
				System.out.println(p1.assignParking());
				
			}
			
			
			System.out.println("What vehicle would you like to park?"); 
			
		}
		
		

		

	}

}
