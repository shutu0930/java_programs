import java.awt.Color;

/* 
 * Table - provides a basic interface for a Table which uses integers for keys 
 * and strings as the value to be looked up.  Noting the color and name are used 
 * for graphing the performance of a particular implementation,  these would not 
 * be typically part of such and interface.
 * 
 * Eric McCreath 2017
 */

public interface Table {
	void insert(int key, String value);  // add an element to the Table.  
	                                     // If an element with the same key is already 
	                                     // in the table then the value is just overwritten.
	String lookup(int key); // lookup an element of the table.  Returns null the element 
	                        // with that key is not in the table. 

	Color color(); // used for working out the color to make the graph

	String name(); // name of the approach used for implementing the table

}
