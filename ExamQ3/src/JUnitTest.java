import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

public class JUnitTest {
	
	@Test
	public void testCarPark(){
		//Your tests must account for statement, branch and path coverage.
		
		int small_parking_no = 1;
		int compact_parking_no = 1;
		int large_parking_no = 2;
		
		ParkingProducer pd=new ParkingProducer(small_parking_no,compact_parking_no,large_parking_no);
		
		//test branch
		Parking p1=pd.getParkingSpots("Car");
		assertTrue(p1.assignParking().contains("Compact"));
		
		Parking p2=pd.getParkingSpots("Minivan");
		assertTrue(p2.assignParking().contains("Large"));
		
		
		Parking p3=pd.getParkingSpots("motorcycle");
		assertTrue(p3.assignParking().contains("small"));
		
		Parking p4=pd.getParkingSpots("Car");
		assertTrue(p4.assignParking().contains("Large"));
		
		
		Parking p6=pd.getParkingSpots("Car");
		assertTrue(p6.assignParking().contains("Sorry"));
		
		Parking p7=pd.getParkingSpots("Minivan");
		assertTrue(p7.assignParking().contains("Sorry"));
		
		
		Parking p8=pd.getParkingSpots("motorcycle");
		assertTrue(p8.assignParking().contains("Sorry"));
		
		Parking p9=pd.getParkingSpots("nonCar");
		assertTrue(p9==null);

			
		
		
		
	}

}
