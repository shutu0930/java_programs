
public class Milk extends Decorator {

	public Milk(Beverage b) {
		super(b);
		
	}

	@Override
	public String showDescription() {
		
		return this.beverage.showDescription()+" Add Milk";
	}

	@Override
	public double showPrice() {
		
		return this.beverage.showPrice()+1.0;
	}

}
