package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Trees extends LeSkeletons {

	BufferedImage treeSheet;
	
	private int direction, sequence;
	
	public Trees(int x, int y, int SIZE, Game_DTG game, double s) {
		super(x, y, SIZE, game, s);
		
	}

	public Trees(Game_DTG game, int SIZE, double session) {
		super(game, SIZE, session);
		sequence = 3;
		direction = 2;
		try {
			treeSheet = ImageIO.read(this.getClass().getClassLoader().getResource("trees.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
	
		g.drawImage(treeSheet, getX(), getY(), getX()+SIZE+128, getY()+SIZE+128, 
		sequence*128, direction*128, (sequence + 1)*128, (direction +1)*128, getGame());
		g.setColor(Color.BLUE);
		g.draw(getBounds());
				
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(SIZE+55 + getX(), SIZE+90 + getY(), SIZE+10, SIZE+25);
	}

	
}
