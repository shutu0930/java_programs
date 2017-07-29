import java.util.ArrayList;

public class Tick {//library
	ArrayList<MyObserver> observers;
	
	Tick(){
		
		observers=new ArrayList<MyObserver>();
	}

	public void regiserObserver(TickDemo td) {
		observers.add(td);
		
	}

	public void mainloop() {
		while(true){
			tickEvent();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private void tickEvent() {//notifyObservers
		for(MyObserver o:observers){
			
			o.update();
		}
		
	}
}
