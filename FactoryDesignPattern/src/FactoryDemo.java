
public class FactoryDemo {

	public static void main(String[] args) {
		ShapeFactory sf=new ShapeFactory();
		Shape s1=sf.getShape("Circle");
		s1.draw();
		
		Shape s2=sf.getShape("Rectangle");
		s2.draw();

	}

}
