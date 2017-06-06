package music;

import java.util.concurrent.atomic.AtomicInteger;

public interface AudioFile {
	
	public void play();
	public void pause();
	public void restart();
	public void stop();
	public boolean isPlaying();
	public boolean isFinished();
	public int getSize();
	public int getCurrentPos();
	int framesToSeconds(int frames);
	public int positionInSeconds();

}
