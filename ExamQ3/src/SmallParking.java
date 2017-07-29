
public class SmallParking extends Parking {
	
	SmallParking(int no){
		this.spotsNo=no;
		
	}
	@Override
	public String assignParking() {
		//currentNo();
		if(this.spotsNo>=0){
			return "Welcome, please continue to park in a small spot";
		}else{
			return "Sorry, there are no spots available for your vehicle.";
		}
		
	}
	@Override
	int currentNo() {
		
		return this.spotsNo;
	}
	void minusNo(){
		spotsNo--;
	}

}
