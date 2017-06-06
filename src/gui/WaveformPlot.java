package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import structures.usefulMethods;

/**Displays a waveform for a single channel of a wav audio file
 * The audio chanelshould be buffered to make all values positive
 * Code Sourced from: http://stackoverflow.com/questions/11017283/java-program-to-create-a-png-waveform-for-an-audio-file/11024268#11024268
 * @author daniel
 *
 */
public class WaveformPlot extends JPanel {


	int[] listP =null;
	int numBytes;
	int linePos=0;
	private int frames_per_pixel;
	private int increment;
	int avgHeight;
	int startingPoint=0;
	int cursorPos=0;
	int bitSize;
	double scaleFactor=3;	


	public WaveformPlot(int[] list, int bitSize) {
		listP = list;
		numBytes=list.length;
		this.setSize(5500, 500);
		this.setBackground(Color.white);
		this.setOpaque(true);
		this.bitSize=bitSize;

		frames_per_pixel = (numBytes-startingPoint) / getWidth()/(2*(bitSize/8));
		increment = frames_per_pixel *2;
		startingPoint=53*increment;
		
		
		System.out.println("\nIncrement " + increment );

		
	}


	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.green);
		int my_byte = 0;
		int y_last = 0;
		int y_new=0;
		int x=0;


		for (int t =startingPoint; t< numBytes-increment; t=t+increment) {

			my_byte = listP[t];
			
			int[] subArray = Arrays.copyOfRange(listP, t,t+increment);
			my_byte= (int) usefulMethods.calcAvg(subArray);

			y_new = (int) ( (my_byte)/(Math.pow(2, bitSize-1))  * getHeight()/2  );
			
			
			//Scale Code
			y_new = (int)((y_new -getHeight()/2) * scaleFactor) + getHeight()/2;


			g.setColor(Color.blue);
			int mid = getHeight()/2;
			g.drawLine(0, mid, getWidth(), mid);

			g.setColor(Color.green);
			g.drawLine(x-1, y_last, x, y_new);



			g.setColor(Color.red);
			g.drawLine(linePos, 0, linePos, getHeight());
			y_last = y_new;
			x++;
		}


	}

	public int getCursorPos() {
		return linePos;
	}


	public void setCursor(int xCoord) {
		linePos=xCoord;
		repaint();
	}

}
