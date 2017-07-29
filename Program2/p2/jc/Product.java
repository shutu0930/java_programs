package p2.jc;

public class Product {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int product=1;
		int sum=0;
		for(int i=1;i<=20;++i)
		{
			
			product*=i;
			sum+=product;
		}
		System.out.print(sum);
	}

}
