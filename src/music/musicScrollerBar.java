package music;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class musicScrollerBar extends JPanel {
	private int scrollerHeight;
	private double currentFraction;
	private int width;
	private int height;

	public musicScrollerBar(int width, int height) {
		this.width=width;
		this.height=height;
		//this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		scrollerHeight=height;
//		this.setBackground(Color.green);
//		this.setOpaque(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(0, 0, width, height);
		int xPosOnLine = (int)(this.getWidth()*currentFraction);
		g.setColor(Color.RED);
		g.fillOval(xPosOnLine, 0, scrollerHeight, scrollerHeight); 
		//System.out.println("Trying to draw at: " + xPosOnLine);
		

	}

	/**Takes position of pointer as fraction
	 * Precondition: @param place is a fraction between 0 and 1
	 */
	public void setBarPos(double place) {
		currentFraction=place;
	}

}
