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
	int numTracks;
	private static AtomicInteger currentLoc=new AtomicInteger(0);
	private boolean isPlaying;
	private boolean isFinished;
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
			//filename = "/Users/daniel/Downloads/freetts-1.2.2-bin/freetts-1.2/docs/audio/cookiecooks.wav";
			this.filename=filename;
			ais = AudioSystem.getAudioInputStream(new File(filename));
			numBytes = ais.available();
			aForm = ais.getFormat();
			framesPerSec = (int) aForm.getFrameRate();
			numTracks=ais.getFormat().getChannels();
			isFinished=false;
			System.out.println("Frame rate: " + framesPerSec);
		}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();System.exit(1);}

	}

	public void play() {
		new Thread() {
			public void run() {
				try {
					int nBytesRead=0;
					if(isFinished) {
						System.out.println("in isFinished block, about to reboot:\t"+currentLoc.get());
						reboot();
						ais.skip(currentLoc.get());
						isFinished=false;
					}
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
							//We want this called only once despite still being in the loop
							if(!isFinished) {
								isFinished=true;
								reboot();
								currentLoc.getAndSet(0);
							}
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

	public void pause() {
		System.out.println("Pause");
		isPlaying=false;
	}

	public void stop() {
		pause();
		currentLoc.getAndSet(0);
	}

	public synchronized void restart() {
		pause();
		reboot();
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

	/**Reloads the file so it can be played from a previous position*/
	private void reboot() {
		try{
			ais=AudioSystem.getAudioInputStream(new File(filename));
			numBytes = ais.available();
		}
		catch (Exception e) {e.printStackTrace();System.exit(1);}
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public int getSize() {
		return numBytes;
	}

	public int getCurrentPos() {
		return currentLoc.get();
	}

	public boolean isFinished() {
		return isFinished;
	}

	public int framesToSeconds(int frames) {
		return frames / framesPerSec / numTracks;
	}

	public int getCurrentPosInSeconds() {
		return framesToSeconds(getCurrentPos());
	}

	public int getSizeInSeconds() {
		return framesToSeconds(getSize());
	}

	public void setPosition(int posInFrames) {
		reboot();
		try {
			ais.skip(posInFrames);
		} catch (IOException e) {e.printStackTrace();}
		currentLoc.getAndSet(posInFrames);
	}
}



