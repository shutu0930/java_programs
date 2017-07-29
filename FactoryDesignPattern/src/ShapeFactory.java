
public class ShapeFactory {
	public Shape getShape(String shapeName){
		
		
		
		if(shapeName.equalsIgnoreCase("circle")){
			return new Circle();
		}
		if(shapeName.equalsIgnoreCase("rectangle")){
			
			return new Rectangle();
		}
		return null;
	}

}
