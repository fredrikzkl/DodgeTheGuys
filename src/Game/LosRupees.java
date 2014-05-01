package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LosRupees extends LeSkeletons implements Runnable{
	
	BufferedImage rupeeSprite;
	private int direction,sequence;
	

	public LosRupees(Game_DTG game, int SIZE, double session) {
		super(game, SIZE, session);
		
		
		direction = (int)(Math.random()*5);
		sequence = 3;

		try {
			rupeeSprite = ImageIO.read(this.getClass().getClassLoader().getResource("proRupees.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX()+3,getY(), SIZE+5, SIZE+20);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(rupeeSprite, getX(), getY(), getX()+16, getY()+28, 
				sequence*16, direction*28, (sequence + 1)*16, (direction +1)*28, getGame());
		/*g.setColor(Color.red);
		g.draw(getBounds());*/
	}

	@Override
	public void run() {
		boolean r = running(getGame().session);
		try {
			Thread.sleep((long)(Math.random()*10) * 100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(r && getGame().running){
			for(int x = 0; x<3;x++){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sequence = x;
				if(r)
					getGame().repaint();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
