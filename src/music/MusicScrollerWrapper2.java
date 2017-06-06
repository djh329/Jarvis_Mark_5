package music;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		
		
//		this.setLayout(new GridLayout(1, 1));		
//		mscScoll2.setBounds(0,0,width,height);
//		add(mscScoll2);	


		this.setLayout(new GridBagLayout());
		mscScoll2.setBounds(0,0,width,height);
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.ipadx=width;
		c.gridx=0;
		c.gridy=0;
		add(mscScoll2, c);

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
