package music;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;
//import javax.swing.Timer;

import structures.FlaggedThread;
import structures.ScrollerController;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class MusicScroller extends JPanel {

	private int totalBeats;
	private int currentBeat=0;
	private int scrollerHeight=3;
	private AudioFile myAudioFile;
	private boolean isRunning=false;
	private int width;
	private int height;
	private JLabel lblcurrentTime;
	private JLabel lblTimeLeft;
	private musicScrollerBar mBar;
	private Timer timer;
	Thread mainScroller;

	/**
	 * Create the panel.
	 */
	public MusicScroller(AudioFile af, int wid, int hei) {
		myAudioFile = af;
		this.totalBeats=af.getSize();
		this.width=wid;
		this.height=hei;
		this.setPreferredSize(new Dimension(400, 40));
		//this.setMinimumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(400, 40));
		setLayout(null);

		mBar = new musicScrollerBar(this.getWidth(), scrollerHeight);
		mBar.setBounds(0, 0, width, scrollerHeight);
		this.add(mBar);

		lblcurrentTime = new JLabel("New label");
		lblcurrentTime.setBounds(5, 5, 100, 20);
		this.add(lblcurrentTime);

		lblTimeLeft = new JLabel("Nljkhbj");
		lblTimeLeft.setBounds(219, -130, 225, 300);
		this.add(lblTimeLeft);
	}




	/**Reinitialize the mainThread
	 * Precondition: mainThread.start has never been called without this method being called first*/
	public void buildThread() {

		//		ActionListener Ttask = new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				System.out.println("IN SWING TIMER ACTION");
		//				int curPos = myAudioFile.getCurrentPos();
		//				if(isRunning && curPos>=0) {
		//					setPos(curPos);
		//					System.out.println("Current Time: " + myAudioFile.positionInSeconds());
		//					lblcurrentTime.setText("" + myAudioFile.positionInSeconds());
		//				}
		//				else {
		//					//If we hit the end of the file
		//					if(isRunning) {
		//						System.out.println("In MusicScroller.java in isRunning if statement");
		//						setPos(40000);
		//					}
		//					isRunning=false;
		//				}
		//			}
		//		};
		//		timer = new Timer(500, Ttask);
		//		//timer.setRepeats(false);

		mainScroller = new Thread(new Runnable() {

			@Override
			public void run() {
				Timer timer = new Timer();
				TimerTask Ttask = new TimerTask() {
					@Override
					public void run() {
						int curPos = myAudioFile.getCurrentPos();
						if(isRunning && curPos>=0) {


							setPos(curPos);
							System.out.println(myAudioFile.positionInSeconds());
							

						}
						else {
							//If we hit the end of the file
							if(isRunning) {
								System.out.println("In MusicScroller.java in isRunning if statement");
								setPos(40000);
							}
							isRunning=false;
							this.cancel(); 
						}
					}
				};
				timer.schedule(Ttask,500,500);
			}

		});
	}
	
	/**Starts the mainThead if it is not running*/
	public void start() {
		if(!isRunning) {
			isRunning=true;
			buildThread();
			mainScroller.start();
		}
	}

	/**Sets the isRunningFlag to false*/
	public void stop() {
		//mainScroller.interrupt();
		isRunning=false;
	}

	/**Returns the status of the isRunning flag to false*/
	public boolean isRunning() {
		return isRunning;
	}

	/**Set the position of the musicScoller*/
	public void setPos(int cb) {
		currentBeat=cb;
		//mBar.setBarPos((double)cb/(double)totalBeats);
		mBar.setBarPos((double)cb/(double)totalBeats);
		revalidate();
		repaint();
	}
	
	/**Get the width passed in*/
	public int getWidth() {
		return width;
	}

	/**Get the height passed in*/
	public int getHeight() {
		return height;
	}

}
