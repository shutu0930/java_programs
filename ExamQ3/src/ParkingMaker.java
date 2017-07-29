
public class ParkingMaker {
	SmallParking sp;
	CompactParking cp;
	LargeParking lp;
	
	
	ParkingMaker(int num1, int num2, int num3){
		sp=new SmallParking(num1);
		cp=new CompactParking(num2);
		lp=new LargeParking(num3);
	}
	
	public void getParkingSpots(String type){
		if(type.equalsIgnoreCase("Car")){
			if(cp.currentNo()>0){
				cp.minusNo();
				System.out.println(cp.assignParking());
			}else if(lp.currentNo()>0){
				lp.minusNo();
				System.out.println(lp.assignParking());
			}else{
				System.out.println("Sorry, there are no spots available for your vehicle.");
			}
		}
		if(type.equalsIgnoreCase("Motorcycle")){
			if(sp.currentNo()>0){
				sp.minusNo();
				System.out.println(sp.assignParking());
			}else{
				System.out.println("Sorry, there are no spots available for your vehicle.");
			}
		}
		
		if(type.equalsIgnoreCase("Minivan")){
			if(lp.currentNo()>0){
				System.out.println(lp.assignParking());
			}else{
				System.out.println("Sorry, there are no spots available for your vehicle.");
			}
		}
		
	}
}
