package structures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**Class that makes it easier to safely run and stop timers with specific behaviors
 * The user must override the behavior() method
 * */
public abstract class FlaggedTimer {

	private boolean isRunning=false;
	private Timer t;
	
	/**Takes in a period in milliseeconds with which to perform a behavior*/
	public FlaggedTimer(int waitTime) {
		
		ActionListener TTask = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isRunning) {
					behavior();
				}
				else
					t.stop();
			}
		};
		t = new Timer(waitTime,TTask);

	}
	public void start() {
		if(!isRunning) {
			isRunning=true;
			t.start();
		}
		
		
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	public void stop() {
		if(isRunning) {
			isRunning=false;
		}
	}

	/**The action to be performed every wait period*/
	public abstract void behavior();

}
