/*
 * NameTable - a simple interface for a table for peoples names. 
 * This table is indexed via an id. 
 * Eric McCreath - 2015
 */


public interface StrTable {
	String lookup(String id);
	void insert(String id, String name);
}
