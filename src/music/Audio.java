package music;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import sun.audio.*;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.management.RuntimeErrorException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import com.sun.jndi.toolkit.url.Uri;

import FileParsing.fileListener;
import FileParsing.parseFiles;
import main.MainWin;
import gui.audioGUIOld;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

/**Represent an Audio FileListener and a GUI*/
public class Audio implements fileListener{

	static boolean isPlaying=false;	//If a song is currently playing
	static boolean isLive; //If the audio is listeneing for any files chosen by the user
	
	

	
	public Audio() {
		
	}
	
	
	/** Plays a song given its file location*/
	//TODO add argument implementation
	public void playSong(String location)
	{
		System.out.println(location);
		System.out.println("inside playSong(title); method");

		if(location!=null) {
			main.MainWin.listFileListeners.remove(this);
			isLive=false;
			MP3 mp3 = new MP3 (location);
			mp3.play();
			isLive=false;
			
			//TODO re add the above line when you want music
		}
		

	}

	
	/** Given name of a song, returns the file name of that song. Returns null if no file could be played */ 
	public String getLocofSong(String title) {
		main.MainWin.listFileListeners.add(this);
		String[][] setOfParams = new String[1][2];
		String[] searchParams0 = {title, "mp3"};
		setOfParams[0] = searchParams0;
		parseFiles pf = new parseFiles();
		String location = pf.getLocOf(setOfParams, true);
		//dummyAd();
		isLive=true;
		return location;
		

	}

	/**Used to force revalidation of GIU*/
//	public static void dummyAd() {
//		Main.MainWin.model.add(0, "feat");
//		Main.MainWin.model.remove(0);
//	}

	@Override
	public void fileChanged(String passedFile) {
		if(isLive)
			playSong(passedFile);


	}
	
	
	




}


