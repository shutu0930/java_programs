
public class ParkingProducer {
	int num1;
	int num2;
	int num3;
	ParkingProducer(int num1,int num2,int num3){
		this.num1=num1;
		this.num2=num2;
		this.num3=num3;
	}
	
	public Parking getParkingSpots(String carType){
		if(carType.equalsIgnoreCase("Car")){
			num2--;
			if(num2>=0){
				return new CompactParking(num2);
			}else{
				num3--;
				return new LargeParking(num3);
			}
			
		}
		
		if(carType.equalsIgnoreCase("Minivan")){
			num3--;
			return new LargeParking(num3);
		}
		if(carType.equalsIgnoreCase("Motorcycle")){
			num1--;
			return new SmallParking(num1);
		}
		
		return null;
		
	}

}
