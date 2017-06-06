package FileParsing;

public class sentenceChanger {

	/**Returns a string in Sentence Case */
	public static String sentenceCase(String input) {
		if(input.length()!=0)
			return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
		return "";
	}

	/**Returns a string with the first letter of each word capitalized */
	public static String firstCapsCase(String input) {

		String total = "";
		for(String a:input.split(" "))
			total+=" " + sentenceCase(a);
		total = total.trim();
		return total;
	}
}
