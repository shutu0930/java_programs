
public class TickDemo implements MyObserver { //my program
	Tick t;//library
	
	TickDemo(){
		t=new Tick();
		t.regiserObserver(this);
		t.mainloop();
	}
	public static void main(String[] args){
		
		new TickDemo();
	}

	@Override
	public void update() {
		System.out.println("hello");
		
	}

}
