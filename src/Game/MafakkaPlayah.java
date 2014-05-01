package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

public class MafakkaPlayah extends LeSkeletons implements Runnable{

	BufferedImage playerSprite;
	
	private boolean moving;
	private boolean upPressed,downPressed,leftPressed,rightPressed;
	private int direction, sequence, counter, steps;
	private int ay,ax;
	private int rupees;
	private int currentRupeesInLevel;
	
	
	private String dirt = "Steps_Dirt1.wav";
	private String rupee = "Rupee1.wav";
	
	
	public MafakkaPlayah(int x, int y, int size, Game_DTG game, double session) {
		
		super(x, y, size, game, session);
		ay = 0;
		ax = 0;
		moving = false;
		direction = 3;
		sequence = 0;
		rupees = 0;
		steps = 0;
		currentRupeesInLevel = 6;
		System.out.println(getGame().getHeight() + " " + getGame().getWidth());
		System.out.println(getGame().getWidth()-SIZE/2);
		try {
			playerSprite = ImageIO.read(this.getClass().getClassLoader().getResource("LINK.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.draw(getBounds());
		
		g.drawImage(playerSprite, getX(), getY(), getX()+SIZE, getY()+SIZE, 
				sequence*90, direction*90, (sequence + 1)*90, (direction +1)*90, getGame());
	}

	public void keyReleased(int e) {
		if(e == KeyEvent.VK_W || e== KeyEvent.VK_UP){
			upPressed = false;
			
		}
		if(e == KeyEvent.VK_S || e == KeyEvent.VK_DOWN){
			downPressed = false;
			
		}
		if(e == KeyEvent.VK_A || e== KeyEvent.VK_LEFT){
			leftPressed = false;
			
			
		}
		if(e == KeyEvent.VK_D || e== KeyEvent.VK_RIGHT){
			rightPressed = false;
			
		}
		
		if (e == KeyEvent.VK_R)
			getGame().nextLevelBro();
		getGame().repaint();
		
	}


	public void keypressed(int e) {
		if(e == KeyEvent.VK_W || e== KeyEvent.VK_UP){
			ay = -1;
			upPressed = true;
			direction = 2;
			moving = true;
		}
		if(e == KeyEvent.VK_S || e == KeyEvent.VK_DOWN){
			ay = 1;
			downPressed = true;
			direction = 3;
			moving = true;
		}
		if(e == KeyEvent.VK_A || e== KeyEvent.VK_LEFT){
			ax = -1;
			leftPressed = true;
			direction = 0;
			moving = true;
		
		}
		if(e == KeyEvent.VK_D || e== KeyEvent.VK_RIGHT){
			ax = 1;
			rightPressed = true;
			direction = 1;
			moving = true;
		}
		
		getGame().repaint();
		
	}
	
	

	public void walk(int x, int y) {
		//System.out.println("X " + x + " Y"+ y);
		if(x < 607 && x>-27){
			setX(x);
		}
		if(y>-16 && y<589){
			setY(y);
		}
		
	}
	public boolean blocked(int x, int y){
		
		System.out.println(getGame().getX());
		for(Trees e :getGame().trees){
			if(this.collition(e)){
				
				return true;
			}
			
		
		}
		return false;
	}
	
	
	public void movement(){
		int x = getX();
		int y = getY();
		
		
		if(rightPressed || leftPressed || upPressed || downPressed)
			if(!blocked(x+ax,y+ay))
			walk(x+ax,y+ay);
			
		
		if(!rightPressed && !leftPressed && !upPressed && !downPressed){
			moving = false;
		}
		
		if(!rightPressed && !leftPressed)
			ax = 0;
		
		if(!upPressed && !downPressed)
			ay = 0;
		
	}
	
	public void setSequence(){
		counter++;
		steps++;
		if(steps == 76){
			SoundEffect.playSoundEffect(dirt);
			steps = 0;
		}
		if (counter == 25){
			if(sequence<4){
				sequence++;
				
			}else{
				sequence=0;
				
			}
			counter = 0;
		}
	}

	@Override
	public void run() {
		int i = 0;
	
		while(getGame().running){
			if (i >= 60)
				i = 0;
			i++;
			getGame().repaint();
			movement();
			checkRupees(currentRupeesInLevel);;
			checkIfCreep();
			if (moving){
				
				
				setSequence();
			}
			checkIfRupee();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}


	private void checkIfRupee() {
		try{
			synchronized (getGame().rupees) {
				for(LosRupees e :getGame().rupees){
					if(this.collition(e)){
						getGame().rupees.remove(e);
						rupees++;
						currentRupeesInLevel--;
						SoundEffect.playSoundEffectLower(rupee);
					}
				}
			}
		
		}catch (ConcurrentModificationException e){
			
		}
	
		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(SIZE/3 + getX(), SIZE/3 + getY(), SIZE/3, SIZE*3/7);
	}





	private void checkRupees(int a){
		if (a == 0){
			getGame().nextLevelBro();
			currentRupeesInLevel = Game_DTG.rupeeCount;
		}
	}
	
	private void checkIfCreep(){
		try{
		for(CreepsNsheit e :getGame().creeps){
			if(this.collition(e)){
				getGame().GameOver(rupees);
			}
		}
		}catch (NullPointerException e){
			
		}
	}
	




}
