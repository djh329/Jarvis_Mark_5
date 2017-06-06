package music;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDeviceBase;
import javazoom.jl.player.Player;
import javazoom.jl.player.PlayerApplet;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/** Sourced from http://stackoverflow.com/questions/27695925/referencing-and-playing-a-mp3-file-from-within-a-java-project */
public class MP3 {



	private String filename;
	private AdvancedPlayer player;
	private PlayerApplet playerApplet;
	private boolean isPlaying=false;
	private int pausedFrame =0;
	private BufferedInputStream bis;


	//Variables for GUI
	private boolean isOn=false;
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	protected JButton play = new JButton("Play");
	protected JButton pause = new JButton("Pause");
	AdvancedPlayer ap;
	MP3 mp3;

	public MP3(String filename) {
		//		this.filename = filename;
		//		mainPanel.setLayout(new BorderLayout());
		//		mainPanel.add(play, BorderLayout.NORTH);
		//		mainPanel.add(pause, BorderLayout.SOUTH);
		//		mainFrame.setVisible(true);
		//		mainFrame.setSize(400, 400);
		//		mainFrame.add(mainPanel);
		//
		//
		//		/**When the window is closed, destructs the audioplayer */
		//		mainFrame.addWindowListener(new WindowAdapter() {
		//
		//			public void windowClosing(WindowEvent e)
		//			{
		//				close();
		//				System.out.println("IM TRYING TO CLOSE THE WINDOW");
		//			}
		//		});
		//
		//		play.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//
		//				resume();
		//			}
		//		});
		//
		//		pause.addActionListener(new ActionListener() {
		//
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//
		//				pause();
		//			}
		//		});
	}


	// play the MP3 file to the sound card
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			player = new AdvancedPlayer(bis);

		} catch (Exception e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		player.setPlayBackListener(new PlaybackListener() {
			@Override
			public void playbackFinished(PlaybackEvent event) {
				pausedFrame = event.getFrame();
			}
		});

		// run in new thread to play in background
		new Thread(new Runnable() {
			public void run() {
				try {
					player.play(pausedFrame, Integer.MAX_VALUE);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}).start(); 
	}

	/**Pauses the current track*/
	public void pause() {
		isOn=false;
		isPlaying=false;
		//TODO get location of advanced player, some function of a buffered stream
		setPausedLoc((int) (Math.random() * 5000));
		player.close();


	}

	/**Closes the MP3 file. Only called by the GUI*/
	private void close() {
		if (player != null) {
			player.close();
			isPlaying=false; 
		}
		pause();
	}


	public void resume() {

		if(!isPlaying) {

			try {
				player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(getFileName())));
			} catch (FileNotFoundException | JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			isPlaying=true;
			new Thread() {
				public void run() {
					try {
						player.play(pausedFrame, Integer.MAX_VALUE);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}.start();

		} 
	}



	public void setAp(AdvancedPlayer ap) {
		player = ap;
	}


	public void setPausedLoc(int i) {
		pausedFrame=i;
	}
	public AdvancedPlayer getPlayer() {
		return player;
	}

	public int getPausedLoc() {
		return pausedFrame;
	}

	public BufferedInputStream getBuffInputSteam() {
		return bis;
	}

	public String getFileName() {
		return filename;
	}

	public boolean isPlaying() {
		return isPlaying; }


}
