package music;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class audioGUI extends JFrame {

	private JPanel contentPane;
	private AudioFile myAudioFile;
	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					audioGUI frame = new audioGUI();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	JButton btnPause;
	JButton btnPlay;
	JButton btnResume;
	JButton btnStop;
	MusicScroller mscScroll;
	MusicScroller2 mscScroll2;
	MusicScrollerWrapper2 mscScroll3;
	JPanel musicImage;
	JLabel lblcurrentTime;
	JLabel lblTimeLeft;
	private boolean isPlaying=false;

	/**
	 * Represents a controller for an audiofile. Can play, pause or stop a file
	 * Precondition: af is not null
	 */
	public audioGUI(AudioFile af) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myAudioFile = af;
		setBounds(100, 700, 350, 300);
		//this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(250, 250, 250, 250));
		setContentPane(contentPane);

		btnPause = new JButton("Pause");
		btnPause.setBounds(241, 30, 80, 30);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});

		btnPlay = new JButton("Play");
		btnPlay.setBounds(36, 30, 80, 30);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnPlay);
		contentPane.add(btnPause);

		btnResume = new JButton("Restart");
		btnResume.setBounds(139, 30, 80, 30);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		contentPane.add(btnResume);


		//mscScroll = new MusicScroller(af, this.getWidth(), 40);
		//mscScroll.setLocation(0, 149);
		//contentPane.add(mscScroll);

		//		mscScroll2 = new MusicScroller2(af);
		//		mscScroll2.setBounds(0, 149, 350, 50);
		//		contentPane.add(mscScroll2);

		mscScroll3 = new MusicScrollerWrapper2(af,350,50);
		mscScroll3.setBounds(0, 149, 350, 50);
		contentPane.add(mscScroll3);

		musicImage = new JPanel();
		musicImage.setBounds(139, 72, 92, 70);
		contentPane.add(musicImage);

	}

	public void play() {
		if(myAudioFile.isFinished())
			myAudioFile.play();
		else if(!myAudioFile.isPlaying()) {
			myAudioFile.play();
			//mscScroll.start();
			mscScroll3.start();
		}

	}

	public void pause() {
		if(myAudioFile.isPlaying()) {
			myAudioFile.pause();
			//mscScroll.stop();

		}
	}

	/**Only to be called by an AudioPlayer or other parents*/
	protected void stop() {
		if(myAudioFile.isPlaying()) {
			pause();
			mscScroll3.stop();
		}

	}

	public void restart() {
		myAudioFile.restart();
		if(!mscScroll3.isRunning())
			mscScroll3.start();
	}

	public boolean isPlaying() {
		return myAudioFile.isPlaying();
	}

}
