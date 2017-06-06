package music;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import gui.WaveformPlot;
import javazoom.jl.player.advanced.AdvancedPlayer;
import structures.usefulMethods;
import sun.audio.AudioStream;

public class BeatFinder {

	AudioInputStream ais;
	AudioFormat aForm;
	int numBytes;
	int numChannels;
	int bitSize;


	public BeatFinder(String filename) {
		try {
			//filename = "/Users/daniel/Documents/Songs/Avicii -  Wake me up (Radio Edit).mp3";
			filename = "/Users/daniel/Documents/Jarvis/Jarvis Mark 3/Avicii Wake Me Up.wav";

			//ais = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(new File(filename)));
			ais = AudioSystem.getAudioInputStream(new File(filename));

			
			numBytes = ais.available();
			aForm = ais.getFormat();
			numChannels=aForm.getChannels();
			bitSize=aForm.getSampleSizeInBits();
			System.out.println(ais.getFormat().toString() + " Length: " + numBytes);

			byte[] b = new byte[numBytes];
			int[] audioData=null;
			ais.read(b, 0, numBytes);

			//	usefulMethods.print1DArrayPrim(b);

			//audioData = new int[numBytes];
			int[] audioSampling= new int[1000];
			int adIndex=0;
			if (aForm.getEncoding().toString().startsWith("PCM_SIGN")) {
				audioData = new int[numBytes/2];
				for (int i = 0; i < b.length; i=i+2) {
					audioData[adIndex] = getSixteenBitSample(b[i], b[i+1]) + (int)Math.pow(2, 15);
					
					if(adIndex>audioData.length-1000 && adIndex<audioData.length)
						audioSampling[adIndex-(audioData.length-1000 )]=audioData[adIndex];
					adIndex++;
				}

			} else {
				audioData = new int[numBytes];
				for (int i = 0; i < b.length; i++) {
					audioData[i] = b[i] + 128;
						
				} 
			}

			usefulMethods.print1DArray(audioSampling);
			
		
			
			JFrame demo = new JFrame("demo");
			demo.setSize(500, 500);



			//FOR DOUBLE CASE
			int[][] byChannel = new int[numChannels][numBytes/numChannels];
			int sampleIndex=0;
			for(int i=0; i<audioData.length;) {
				for(int channel=0; channel<numChannels;channel++) {
					byChannel[channel][sampleIndex]=audioData[i];
					i++;
				}
				sampleIndex++;
			}

			demo.setLayout(new GridLayout(numChannels,1));
			ArrayList<WaveformPlot> listPlots = new ArrayList<WaveformPlot>();
			for(int[] channel: byChannel)
				listPlots.add(new WaveformPlot(channel, bitSize));

			for(WaveformPlot wfp:listPlots) {
				wfp.setSize(wfp.getWidth(), demo.getHeight()/numChannels);
				wfp.setPreferredSize(new Dimension(wfp.getWidth(), wfp.getHeight()));
				JScrollPane scrollingPanel = new JScrollPane(wfp);
				scrollingPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				demo.add(scrollingPanel);
			}

			//FOR SINGLE CASE
			//			WaveformPlot wfp = new WaveformPlot(audioData);
			//			wfp.setSize(wfp.getWidth(), demo.getHeight());
			//			wfp.setPreferredSize(new Dimension(wfp.getWidth(), wfp.getHeight()));
			//			JScrollPane scrollingPanel = new JScrollPane(wfp);
			//			scrollingPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			//			demo.add(scrollingPanel);


			demo.setVisible(true);






			
			//ais = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(new File(filename)));
			ais = AudioSystem.getAudioInputStream(new File(filename));


			aForm = ais.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aForm);
			SourceDataLine sourceLine = null;
			try {
				sourceLine = (SourceDataLine) AudioSystem.getLine(info);
				sourceLine.open(aForm);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

			sourceLine.start();



			int nBytesRead = 0;
			int readSize=8000;
			byte[] abData = new byte[readSize]; //128000

			//FOR SINGLE CASE
			//wfp.setCursor(53);	

			//FOR DOUBLE CASE
			for(WaveformPlot wfp:listPlots) {
				wfp.setCursor(0);
			}

			while (nBytesRead != -1) {
				try {
					nBytesRead = ais.read(abData, 0, abData.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (nBytesRead >= 0) {
					@SuppressWarnings("unused")
					int nBytesWritten;


					nBytesWritten = sourceLine.write(abData, 0, nBytesRead); 



					//FOR SINGLE CASE

					//cursorPos+=(int)(((double)readSize)/numBytes * wfp.getWidth());	



					for(WaveformPlot wfp:listPlots) {
						int cursorPos= wfp.getCursorPos() + (int)(((double)readSize)/numBytes/(bitSize/8) * wfp.getWidth());
						wfp.setCursor(cursorPos);
					//	wfp.scrollRectToVisible(new Rectangle(cursorPos-250, 0, cursorPos+250, wfp.getHeight()));
					}
				}

			}
			sourceLine.drain();
			sourceLine.close();  
		}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();} 


	}

	private int getSixteenBitSample(int high, int low) {
		return (high << 8) + (low & 0x00ff);
	}



}
