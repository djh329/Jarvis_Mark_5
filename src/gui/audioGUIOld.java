package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import music.MP3;

public class audioGUIOld {

	private boolean isOn=false;
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	AdvancedPlayer ap;
	MP3 mp3;

	public audioGUIOld(MP3 mp3) {
		this.mp3=mp3;
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(play, BorderLayout.NORTH);
		mainPanel.add(pause, BorderLayout.SOUTH);

		mainFrame.setVisible(true);
		mainFrame.setSize(400, 400);
		mainFrame.add(mainPanel);
		ap = mp3.getPlayer();

		/**When the window is closed, destructs the audioplayer */
		mainFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e)
			{
				pause();
			}
		});

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				play();
			}
		});

		pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				pause();
			}
		});



	}



	public void play() {
		if(!isOn) {
			System.out.println("Inside play action performed\t" + isOn);
			try {
				ap = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(mp3.getFileName())));
			} catch (FileNotFoundException | JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			isOn=true;
			new Thread() {
				public void run() {
					try {
						//playerApplet.setFileName(filename);
						//playerApplet.run();
						//System.out.println("running playerapplet thing");


						ap.play(mp3.getPausedLoc(), Integer.MAX_VALUE);
						mp3.setAp(ap);


					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}.start();

		}
	}



	public void pause() {
		isOn=false;

		//TODO get location of advanced player, some function of a buffered stream
		mp3.setPausedLoc((int) (Math.random() * 5000));
		System.out.println("inside pause button actionPerformed\n" + ap);

		ap.close();
		mp3.setAp(ap);

	}




}
