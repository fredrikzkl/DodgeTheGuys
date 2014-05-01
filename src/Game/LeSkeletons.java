package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

interface MeatAndVeins{
	public void draw(Graphics2D g);
	
}

/**
 * @author Fredrik
 *
 */
public abstract class LeSkeletons implements MeatAndVeins{
	
	Game_DTG game;
	public final int SIZE;
	private int x,y;
	private double seassion;
	
	public LeSkeletons(int x, int y, int SIZE, Game_DTG game, double s){
		this(game, SIZE, s);
		this.x = x;
		this.y = y;
		
		
		
		
	}
	
	public LeSkeletons(Game_DTG game, int SIZE, double session) {
		this.game=game;
		this.SIZE = SIZE;
		this.seassion = session;
		
		x = (int)(Math.random()*(607));
		y = (int)(Math.random()*(589));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Game_DTG getGame() {
		return game;
	}
	
	
	public double getSeassion() {
		return seassion;
	}

	public void setSeassion(double session) {
		this.seassion = session;
	}

	public Rectangle getBounds(){
		return new Rectangle(getX(), getY(), SIZE, SIZE);
	}
	
	public boolean running(double s){
		if(s == seassion)
			return true;
		else
			return false;
	}
	
	public boolean collition(LeSkeletons e){
		return e.getBounds().intersects(getBounds());
	}
	
	public boolean gettingClose(LeSkeletons a){
		boolean cX, cY;
		cX = Math.abs(a.getX() - x) < 150;
		cY = Math.abs(a.getY() - y) < 150;
		if (cX && cY)
			return true;
		else
			return false;
	}
	
	
	
}
