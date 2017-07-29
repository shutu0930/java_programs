
public class LargeParking extends Parking {
	
	LargeParking(int no){
		this.spotsNo=no;
	}

	@Override
	String assignParking() {
		
		//currentNo();
		if(this.spotsNo>=0){
			return "Welcome, please continue to park in a Large spot";
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
