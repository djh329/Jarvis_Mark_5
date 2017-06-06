package music;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MusicScroller2 extends JPanel{

	private int XPosOnLine=0;
	private AudioFile af;
	private Timer t;
	private boolean isRunning=false;

	public MusicScroller2(AudioFile af) {
		this.af=af;
		repaint();
	}

	public void start() {
		isRunning=true;
		ActionListener TTask = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double curFrac = ((double) af.getCurrentPos())/af.getSize();
				if(curFrac>=0 && isRunning) {
					setPos(curFrac);
					repaint();
					System.out.println("Im repainting");
				}
				else {
					System.out.println("in the else part");
					if(af.getCurrentPos()<0) {
						setPos(0);
					}
					repaint();
					t.stop();
				}

			}
		};
		t = new Timer(500,TTask);
		t.start();
		repaint();
	}

	public void stop() {
		repaint();
		isRunning=false;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, this.getWidth(), 5);
		g.setColor(Color.red);
		g.fillOval(XPosOnLine, 0, 5, 5);
	}


	public void setPos(double frac) {
		XPosOnLine = (int)(frac * this.getWidth());
	}

	public boolean isRunning() {
		return isRunning;
	}

}
