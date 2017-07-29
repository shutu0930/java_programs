import java.awt.Color;


/*
 * ListTable - This implementation uses a list of KeyValues to store the table.  
 * This is one of the simplest approaches for storing such a table.  However,
 * it is not particular fast in terms of adding element or looking them up.    
 */


public class ListTable implements Table {
	
	// add fields here for storing the list of key-value elements
	
	public ListTable() {
		// add code here for setting up an empty table
	}
	
	
	// insert - with a list table in most cases you can just add the new
	//          KeyValue to the end of the list.  However, the key is 
	//          already in the table you need to overwrite the value for that KeyValue. 
	@Override
	public void insert(int key, String value) {
	
		// add your code here for inserting an element into the table
		
	}

	// lookup - this involved going through the list if a matching key is found 
	// the value is output.  If no match is found null is returned. 
	@Override
	public String lookup(int key) {
		
        // add your code here for looking up the table.
		
		return null;
	}

	@Override
	public Color color() {
		return Color.blue;
	}

	@Override
	public String name() {
		return "KeyValueList";
	}

}
