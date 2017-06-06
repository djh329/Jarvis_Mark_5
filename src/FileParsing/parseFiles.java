package FileParsing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class parseFiles {

	/** Given search parameters for a file name, returns the file. If multiple or no results are found, null is returned. In the case of multiple
	 * the appropriate file listener is called. If exactName is true, only files that match the discovered titles directly will be sent back
	 * For example, the essay for Yale will return a file called Yale Essay when false, but not when true*/ 
	public String getLocOf(String[][] searchTerms, boolean exactName) {
		// your directory http://stackoverflow.com/questions/4852531/find-files-in-a-folder-using-java

		File f = new File("/Users/daniel/Documents/");
		HashSet<File> matchedFiles = new HashSet<File>();
		for(String[] singleSet: searchTerms) {
			matchingFiles(f,singleSet, matchedFiles, exactName);
		}
		System.out.println();

		//Put Space on the Console
		System.out.println("\n\n");
		if(matchedFiles.size()==0)

			System.out.println("No Matching Files Found");
		else {
			//	for (File g:matchedFiles)
			//		System.out.println(g);
			//TODO add to print out each file checked

			if(matchedFiles.size()>1) {
				System.out.println("MULTIPLE CHOICES IN PARSEFILES LINE 37");
				//MainWin.mainWin.listChoice(matchedFiles);
				//MainWin.mainWin.showGUIList();
			}
			else {
				String[] dummy = new String[1];
				int i=0;
				for(File h: matchedFiles) {
					dummy[i] = h.toString();
					i++;
				}
				if(dummy.length!=1)
					throw new IllegalArgumentException("Too many in string dummy what the hell man");
				return dummy[0];
			}

		}

		return null;
	}


	/**Returns a boolean if a file is found that contains a specific keyword given a keyword.
	 * @Param is an array of all files that contain the key word
	 * Precondition: f is a non null File*/
	public static boolean matchingFiles(File dir, String[] searchKey, Set<File> allFiles, boolean exactName) {

		if(dir.isFile()) {
			if(checkSentenceCases(dir.getName(), searchKey, exactName)) {
				allFiles.add(dir);
				return true;
			}
			return false;
		}

		if(dir.isDirectory()){
			for (File f:dir.listFiles()) {
				matchingFiles(f, searchKey, allFiles, exactName);
			}
			return true; }
		else
			return false;
	}




	/**Given a string that represents a string of ordered important terms, returns if each word in the String is contained,
	 *  not neccesarily in order, in a certain string
	 */
	private static boolean checkEachWord(String filename, String searchKey) {
		String[] eachWord = searchKey.split("\\s+");
		for(String singleWord: eachWord) {	
			if(!(checkSentenceCases(filename, singleWord)))
				return false;
			//System.out.println(eachWord);
		}
		return true;
	}

	/** Returns if a string contains a keyword in upper, lower, sentence and FirstCaps cases */
	private static boolean checkSentenceCases(String filename, String searchKey) {
		return filename.toLowerCase().contains(searchKey.toLowerCase());
	}


	/** Returns if a string contains all keywords in a given list in either upper, lower, sentence and FirstCaps cases */
	private static boolean checkSentenceCases(String filename, String[] searchKey, boolean exactName) {
		boolean hasAll=true;
		if(exactName) {
			for(String keyEl:searchKey) {
				hasAll=checkSentenceCases(filename, keyEl);
				
				if(!hasAll)
					return false;
			}
		}
		else {
			for(String keyEl:searchKey) {
				hasAll=checkEachWord(filename, keyEl);
				//			hasAll= checkSentenceCases(filename, keyEl);
				
				if(!hasAll)
					return false;
				
			}
			
		}
		return true;
	}


}
