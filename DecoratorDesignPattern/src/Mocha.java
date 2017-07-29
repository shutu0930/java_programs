
public class Mocha extends Decorator {

	public Mocha(Beverage b) {
		
		super(b);
	}

	@Override
	public String showDescription() {
		
		
		return this.beverage.showDescription()+" Add Mocha";
	}

	@Override
	public double showPrice() {
		
		return this.beverage.showPrice()+5.0;
	}

}
