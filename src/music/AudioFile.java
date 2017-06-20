package music;

import java.util.concurrent.atomic.AtomicInteger;

public interface AudioFile {
	
	/**Starts the audiotrack from the beginning of the file*/
	public void play();
	
	/**Pauses the audiotrack and marks the current location*/
	public void pause();
	
	/**If the audiotrack is paused, plays the audiotrack from the current position*/
	public void restart();
	
	/**Pauses the AudioFile and restores values to default*/
	public void stop();
	
	/**Returns true if the song is playing*/
	public boolean isPlaying();
	
	/**Returns true if the audiofile is at the end*/
	public boolean isFinished();
	
	/**Returns the size of the file in beats*/
	public int getSize();
	
	/**Returns the size of the audiofile in seconds*/
	public int getSizeInSeconds();
	
	/**Returns the currentPosition of the song in frames*/ 
	public int getCurrentPos();
	
	/**Converts frames to seconds for the current song*/ 
	int framesToSeconds(int frames);
	
	/**Returns the position of the song in seconds*/
	public int getCurrentPosInSeconds();
	
	/**Sets the position of the audiofile to the given frame*/
	public void setPosition(int posInFrames);		

}
