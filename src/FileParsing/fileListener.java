package FileParsing;

import java.io.File;

public interface fileListener {
	
	public enum fileType {SONG, RECIPE}
	
	
	public abstract void  fileChanged(String passedFile);
	

}
