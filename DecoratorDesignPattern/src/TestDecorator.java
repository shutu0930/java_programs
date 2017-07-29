
public class TestDecorator {

	public static void main(String[] args) {
//		BaseBean1 bb1=new BaseBean1();
//		Milk milkCoffee= new Milk(bb1);
//		System.out.println(milkCoffee.showDescription());
		
		
		Beverage beverage = new BaseBean1();  
		
        Milk milkCoffee = new Milk(beverage);    
        System.out.println(milkCoffee.showDescription());  
        System.out.println(milkCoffee.showPrice());  
        
        Mocha mochaCoffee = new Mocha(beverage);    

        System.out.println(mochaCoffee.showDescription());  
        System.out.println(mochaCoffee.showPrice());  
	}

}
