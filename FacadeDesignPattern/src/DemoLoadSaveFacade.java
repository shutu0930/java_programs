
public class DemoLoadSaveFacade {
/*
 * DemoLoadSaveFacade - this code is just a simple test of using
 * the LoadSaveFacade class.
 * Eric McCreath 2015 GPL
 */
	
	private static final String HELLO_TXT = "hello.txt";

	public static void main(String[] args) {
	   LoadSaveFacade.save(HELLO_TXT, "Hello\nWorld\n");
	   String text = LoadSaveFacade.load(HELLO_TXT);
	   System.out.println(text);
	}

}
