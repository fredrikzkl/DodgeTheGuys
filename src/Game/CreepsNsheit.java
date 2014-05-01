package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreepsNsheit extends LeSkeletons implements Runnable{

	BufferedImage creepSprite;
	
	private int direction,sequence, counter, movementSpeed, speedCounter;
	
	public CreepsNsheit(Game_DTG game, double s) {
		super(game, 35, s);
		direction = 0;
		sequence = 0;
		speedCounter = 0;
		movementSpeed = 10;
		
		
		
		try {
			creepSprite = ImageIO.read(this.getClass().getClassLoader().getResource("forest-spider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(SIZE/3 + getX(), SIZE/3 + getY(), SIZE/3, SIZE*3/10);
	}


	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(creepSprite, getX(), getY(), getX()+SIZE, getY()+SIZE, 
				sequence*35, direction*35, (sequence + 1)*35, (direction +1)*35, getGame());
		//g.setColor(Color.YELLOW);
		//g.draw(getBounds());
	}
	
	public void AI(){
		int playerX = getGame().player.getX() + 10;
		int playerY = getGame().player.getY() + 30;
		
		if(playerX> getX()){
			direction = 3;
			/*for(CreepsNsheit e : getGame().creeps){
				if (this.collition(e)){
					e.setX(getX()-1);	
					
				}
				
			}*/
			setX(getX()+1);
			
		}
		if(playerX< getX()){
			direction = 1;
			setX(getX()-1);
		}
		if(playerY> getY()){
			direction = 0;
			setY(getY() +1);
		}
		if(playerY< getY()){
			direction = 2;
			setY(getY() -1);
		}
		
	}

	public void setSequence(){
		counter++;
		if (counter == 11){
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
		
		while(running(getGame().session) && getGame().running){
			if (i >= 60)
				i = 0;
			i++;
			getGame().repaint();
			AI();
			
			setSequence();
			speedIncreaser();
			
			try {
				Thread.sleep(movementSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	private void speedIncreaser() {
		speedCounter++;
		if (speedCounter > 550){
			if (movementSpeed > 5){
				movementSpeed --;
			}
			speedCounter = 0;
		}
		
	}

}
