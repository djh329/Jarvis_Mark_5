package music;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MusicScrollerWrapper2 extends JPanel {

	private MusicScroller2 mscScoll2;
	private boolean initializaed=false;

	/**Constructor for a MusicScroller Entity: bar and labels
	 * 
	 * @param af The audiofile being played
	 * @param width desired width of the component
	 * @param heigth desired height of the component
	 */
	public MusicScrollerWrapper2(AudioFile af, int width, int height) {
		mscScoll2 = new MusicScroller2(af);
		this.setLayout(new GridLayout(1, 1));		
		mscScoll2.setBounds(0,0,width,height);
		add(mscScoll2);	
		repaint();
	}


	public void start() {		
		mscScoll2.start();
	}

	public void stop() {
		mscScoll2.stop();
	}

	public boolean isRunning() {
		return mscScoll2.isRunning();
	}

}
