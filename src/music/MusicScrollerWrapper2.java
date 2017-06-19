package music;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.miginfocom.layout.Grid;
import structures.FlaggedTimer;

public class MusicScrollerWrapper2 extends JPanel {

	private MusicScroller2 mscScoll2;
	private boolean initializaed=false;
	private JLabel timeStart;
	private JLabel timeLeft;
	private FlaggedTimer timerLabel;

	/**Constructor for a MusicScroller Entity: bar and labels
	 * 
	 * @param af The audiofile being played
	 * @param width desired width of the component
	 * @param heigth desired height of the component
	 */
	public MusicScrollerWrapper2(AudioFile af, int width, int height) {
		mscScoll2 = new MusicScroller2(af);
		timeStart = new JLabel("0:00");
		timeLeft = new JLabel("" + af.framesToSeconds(af.getSize()));
		//		this.setLayout(new GridLayout(1, 1));		
		//		mscScoll2.setBounds(0,0,width,height);
		//		add(mscScoll2);	


		this.setLayout(new GridBagLayout());
		mscScoll2.setBounds(0,0,width,height);
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridwidth=2;
		c.ipadx=width;
		c.gridx=0;
		c.gridy=0;
		add(mscScoll2, c);

		c.fill=GridBagConstraints.NONE;
		c.gridwidth=1;
		c.ipadx=50;
		c.gridx=0;
		c.gridy=1;
		add(timeStart, c);

		c.anchor=GridBagConstraints.LINE_END;
		c.ipadx=0;
		c.gridx=1;
		c.gridy=1;
		add(timeLeft, c);

		timerLabel = new FlaggedTimer(500) {
			@Override
			public void behavior() {
				timeStart.setText("" + af.getCurrentPosInSeconds());
				timeLeft.setText("");
			}
		};

		repaint();
	}


	public void start() {		
		mscScoll2.start();
		timerLabel.start();

	}

	public void stop() {
		mscScoll2.stop();
		timerLabel.stop();
	}

	public boolean isRunning() {
		return mscScoll2.isRunning();
	}



}
