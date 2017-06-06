package music;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3_v2 implements AudioFile {


	AudioInputStream ais;
	AudioFormat aForm;
	int numBytes;
	int framesPerSec;
	private static AtomicInteger currentLoc=new AtomicInteger(0);
	private boolean isPlaying;
	DataLine.Info info = new DataLine.Info(SourceDataLine.class, aForm);
	SourceDataLine sourceLine = null;
	int readSize=8000;
	byte[] abData = new byte[readSize];
	String filename;
	Lock l = new Lock() {

		@Override
		public void unlock() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean tryLock() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Condition newCondition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			// TODO Auto-generated method stub

		}

		@Override
		public void lock() {
			// TODO Auto-generated method stub

		}
	};



	public MP3_v2(String filename) {
		try {

			System.out.println("in mp3v2 constructor");
			//TODO for trial purposes, leave below line
			filename = "/Users/daniel/Documents/Jarvis/Jarvis Mark 3/Avicii Wake Me Up.wav";
			filename = "/Users/daniel/Downloads/freetts-1.2.2-bin/freetts-1.2/docs/audio/cookiecooks.wav";
			this.filename=filename;
			ais = AudioSystem.getAudioInputStream(new File(filename));
			numBytes = ais.available();
			aForm = ais.getFormat();
			framesPerSec = (int) aForm.getFrameRate();
			System.out.println("Frame rate: " + framesPerSec);
		}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();System.exit(1);}

	}

	/**Starts the audiotrack from the beginning of the file*/
	public void play() {
		new Thread() {
			public void run() {
				try {
					int nBytesRead=0;
					if(currentLoc.get()==-1)
						currentLoc.getAndSet(0);
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(aForm);
					sourceLine.start();
					isPlaying=true;
					while(nBytesRead>=0  && isPlaying) {
						nBytesRead = ais.read(abData, 0, abData.length);
						if(nBytesRead>0) {
							currentLoc.addAndGet(sourceLine.write(abData, 0, nBytesRead));
						}
						else {
							currentLoc.getAndSet(-1);
						}
					}
					sourceLine.drain();
					sourceLine.close(); 
				}
				catch (IOException e) {e.printStackTrace();}
				catch (LineUnavailableException e) {e.printStackTrace();System.exit(1);}
			}
		}.start();

	}

	/**Pauses the audiotrack and marks the current location*/
	public void pause() {
		System.out.println("Pause");
		isPlaying=false;
	}

	/**Pauses the AudioFile and restores values to default*/
	public void stop() {
		pause();
		currentLoc.getAndSet(0);
	}

	/**If the audiotrack is paused, plays the audiotrack from the current position*/
	public synchronized void restart() {
		pause();
		try{
			ais=AudioSystem.getAudioInputStream(new File(filename));
			numBytes = ais.available();
		}
		catch (Exception e) {e.printStackTrace();System.exit(1);}

		currentLoc.getAndSet(0);
		System.out.println("Current Location is: " + currentLoc.get());

		Timer t1 = new Timer();
		t1.schedule(
				new TimerTask() 
				{
					boolean noMisfire = true;
					public void run() 
					{
						play();
					}}, 1000);
	}

	/**Returns the current frame of the song*/ 
	public int getCurrentFrame() {
		return currentLoc.get();
	}

	/**Returns true if the song is playing*/
	public boolean isPlaying() {
		return isPlaying;
	}

	/**Returns the size of the file in beats*/
	public int getSize() {
		return numBytes;
	}

	/**Returns the currentPosition of the song in frames*/ 
	public int getCurrentPos() {
		return currentLoc.get();
	}

	/**Returns true if the audiofile is at the end*/
	public boolean isFinished() {
		return currentLoc.get()==-1;
	}

	/**Converts frames to seconds for the current song*/ 
	public int framesToSeconds(int frames) {
		return frames / framesPerSec;
	}
	
	/**Returns the position of the song in seconds*/
	public int positionInSeconds() {
		return framesToSeconds(getCurrentPos());
	}
}



