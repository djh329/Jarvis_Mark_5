package music;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**Class containing an audio file and an audioGUI. This class
 *  will control synchronized behavior for both
 * @author daniel
 */
public class AudioPlayer extends audioGUI{
	
	private AudioFile af;
	
	public AudioPlayer(String filename) {
		super(new MP3_v2(filename));
		this.addWindowListener(new winListener());
		this.setVisible(true);
	}
	
	private class winListener implements WindowListener {
		@Override
		public void windowOpened(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
		@Override
		public void windowClosing(WindowEvent e) {
			stop();
		}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
	}
}
