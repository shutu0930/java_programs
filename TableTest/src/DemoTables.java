
public class DemoTables {

	/*
	 * DemoTable - a simple class that runs some tests on the different 
	 * table implementations.  So it basically creates some small tables, adding a 
	 * number of elements into table then checks that the values are correct when looked up.
	 * 
	 *  Eric McCreath 2017
	 */
	public static void main(String[] args) {
		int t = 0;
		
		
		Table table;
		while ((table = RunTests.getTable(t)) != null)  {
			System.out.println("**************   " + table.name()  + "    ***************");
			addentry(table, 7, "John");
			addentry(table, 5, "Hugh");
			addentry(table, 9, "Matthew");
			addentry(table, 8, "Isaac");
			addentry(table, 9, "Eric");
			addentry(table, 3, "Matthew");
			checkentry(table, 7, "John");
			checkentry(table, 5, "Hugh");
			checkentry(table, 9, "Eric");
			checkentry(table, 3, "Matthew");
			checkentry(table, 1, null);
			t++;
		}

	}

	private static void checkentry(Table table, int key, String expectedValue) {
		String tv = table.lookup(key);
		if ((tv == null && expectedValue == null) ||
				(tv != null && expectedValue != null && tv.equals(expectedValue))) {
			System.out.println("lookup " + key + " and returned " + (tv==null? "null" : tv) + " as expected.");
		} else {
			System.out.println("!!!!!!!!problem!!!!!!!!! lookup " + key + " expected " + (expectedValue==null? "null" : expectedValue) + " yet returned " + (tv==null? "null" : tv) + ".");
		}
		
		
	}

	private static void addentry(Table table, int key, String value) {
		System.out.println("inserting mapping " + key + " => " + value);
		table.insert(key, value);
	}

}
