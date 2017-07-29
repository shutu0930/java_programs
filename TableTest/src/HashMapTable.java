import java.awt.Color;
import java.util.HashMap;


// HashMapTable - this uses the standard HahMap class to implement the table. 

public class HashMapTable extends HashMap<Integer,String> implements Table {

	
	@Override
	public void insert(int key, String value) {
        this.put(key, value);
	}

	@Override
	public String lookup(int key) {
		return this.get(key);
	}

	@Override
	public Color color() {	
		return Color.BLACK;
	}

	@Override
	public String name() {
		return "HashMap";
	}

}
