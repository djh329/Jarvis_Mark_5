package speech;
import java.io.*;
import sun.audio.*;


import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import music.Audio;
import music.AudioPlayer;
import music.MP3_v2;

//import weather.Weather;
//import web.mainWebGUI;
//import document.basicText;
//import gui.Main;
//import music.Audio;
//import music.BeatFinder;
//import scrap.BADBeatFinder;


public class inputProcessing

{
	TextToSpeech talker1 = new TextToSpeech();


	public inputProcessing(String input)
	{
		comprehend(input);
	}

	public static void comprehend(String input)
	{
		/** CODE for handling music */
		String keyword=input;

		if (input.contains("play") && !input.substring(0,input.indexOf("play")).contains("want")) {
			if (input.length()>=input.indexOf("play")+7 && input.substring(input.indexOf("play") + 5, input.indexOf("play")+7).equals("me"))
			{
				keyword = input.substring(input.indexOf("me")+3);
			}
			else
				keyword=input.substring(input.indexOf("play")+5);

			keyword=removeIdlerWords(keyword);
		}
		//TextToSpeech.say("keyword");
		//			//Audio.getLocofSong(keyword);
		//			Audio audioPlayer = new Audio();
		//			String title= audioPlayer.getLocofSong(keyword);
		//			audioPlayer.playSong(title);
		//		}
//		MP3_v2 mp = new MP3_v2("");
//		mp.play();
		AudioPlayer ap = new AudioPlayer("");
		//		else if (input.contains("open") || input.contains("find")) {
		//			//TODO look at the logic for this
		//			if(input.contains("open")) {
		//				if (input.length()>=input.indexOf("open")+6) { //open essay
		//					if(input.substring(input.indexOf("open") + 5, input.indexOf("open")+7).equals("me"))
		//					{
		//						keyword = input.substring(input.indexOf("me")+3);
		//					}	
		//					else if (input.substring(input.indexOf("open") + 5, input.indexOf("open")+7).equals("my"))
		//					{
		//						keyword = input.substring(input.indexOf("my")+3);
		//					} else {
		//						keyword=input.substring(input.indexOf("open")+5);
		//					}
		//				}
		//			}
		//			else 
		//				if (input.length()>=input.indexOf("find")+6){
		//					if(input.substring(input.indexOf("find") + 5, input.indexOf("find")+7).equals("me"))
		//					{
		//						keyword = input.substring(input.indexOf("me")+3);
		//					}	
		//					else if (input.substring(input.indexOf("find") + 5, input.indexOf("find")+7).equals("my"))
		//					{
		//						keyword = input.substring(input.indexOf("my")+3);
		//					} else {
		//						keyword=input.substring(input.indexOf("find")+5);
		//					}
		//				}else{}
		//
		//
		//			keyword=removeIdlerWords(keyword);
		//
		//			basicText bt = new basicText();
		//			String location = bt.getLocOfFile(keyword);
		//			System.out.println("File Location:\t" + location + "BBBBB");
		//			bt = new basicText(location);
		//
		//		}
		//		else if(input.contains("weather")){
		//			Weather weather = new Weather();
		//		}
		//		//mainWebGUI webGUI = new mainWebGUI();
		//		BeatFinder b = new BeatFinder("k");

	}

	//	/**Takes an input string and a keywords and sends back the part of the string after the keyword
	//	 * If the keyword is not present, then return a cut string
	//	 * @param totalPhrase: the input to be cut
	//	 * @param cutWord: the word to be cut after
	//	 */
	//	public static String afterGivenWord(String totalPhrase, String cutWord) {
	//		private String keyPhrase="";
	//		if (input.contains("play") && !input.substring(0,input.indexOf("play")).contains("want")) {
	//			if (input.length()>=totalPhrase.indexOf("play")+7 && input.substring(input.indexOf("play") + 5, input.indexOf("play")+7).equals("me"))
	//			{
	//				keyPhrase = totalPhrase.substring(totalPhrase.indexOf("me")+3);
	//			}
	//			else
	//				keyPhrase=totalPhrase.substring(totalPhrase.indexOf("play")+5);
	//			
	//			return keyPhrase;
	//	}


	/**Looks at the beginning and end of words and removes words that seems irrelevant"*/
	public static String removeIdlerWords(String keyword) {
		if (keyword.length()>8) 
		{
			if (keyword.substring(keyword.length()-6, keyword.length()).equals("please") || keyword.substring(keyword.length()-6, keyword.length()).equals("jarvis")) 
			{
				keyword=keyword.substring(0,keyword.length()-6);
			}
		}
		keyword=keyword.trim();
		System.out.println("In method removed Idler Words: " + keyword);
		return keyword;
	}










}
