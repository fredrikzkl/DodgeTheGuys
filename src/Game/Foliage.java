package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

	public class Foliage extends LeSkeletons {
	BufferedImage foliageSheet;
	
	public static int direction, sequence, spriteSize;
	public Foliage(int x, int y, int SIZE, Game_DTG game, double s) {
		super(x, y, SIZE, game, s);
		
	}

	public Foliage(Game_DTG game, int SIZE, double session) {
		super(game, SIZE, session);
		
		spriteSize = 32;
		sequence = 13;//428;
		//direction = 1;//43;
		direction = 4;
		
		try {
			foliageSheet = ImageIO.read(this.getClass().getClassLoader().getResource("tileNature.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(foliageSheet, getX(), getY(), getX()+spriteSize+7, getY()+spriteSize+7, 
				sequence*spriteSize, direction*spriteSize, (sequence + 1)*spriteSize, (direction +1)*spriteSize, getGame());
		//g.drawRect(getX(),getY(),spriteSize+7,spriteSize+7);

	}

}
