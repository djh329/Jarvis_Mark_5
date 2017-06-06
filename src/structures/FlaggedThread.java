package structures;

public class FlaggedThread extends Thread {

	private boolean isRunning=false;
	
	
	public void start() {
		super.start();
		isRunning=true;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	public void terminate() {
		isRunning=false;
	}
	

}
