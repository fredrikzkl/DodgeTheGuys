package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;




public class Game_DTG extends JPanel{
	
	private int spriteX,spriteY,spriteSize;
	
	public static final int TILES = 10;
	public static final int TILE_SIZE = 64;
	
	BufferedImage ground;
	
	MafakkaPlayah player;
	List<CreepsNsheit> creeps;
	List<LosRupees> rupees;
	List<Trees> trees;
	List<Foliage> foliage; 
	
	public boolean running;
	
	public int highscore;
	public double session;
	int creepCount;
	public static int rupeeCount;
	private int treeCount;
	private int foliageCount;
	
	private Sound backgroundMusic;
	private String youSuck = "youSuck.wav";
	private String music = "Lost_Woods.wav";
	private String bite = "bite.wav";
	private String eat = "Eating.wav";
	private String nextLevel = "nextLevel.wav";
	
	public Game_DTG(){
		
		highscore = 0;
		backgroundMusic = new Sound(music);
		backgroundMusic.loop();
		backgroundMusic.volume(-20f);
		running = true;
		setBounds(0,0,TILES*TILE_SIZE+6,TILES*TILE_SIZE+29);
		setOpaque(false);
		
		player = new MafakkaPlayah(9, 9, 64,this, session);
		(new Thread(player)).start();
		
		creeps = new ArrayList<>();
		creepCount = 0;
		
		rupees = new ArrayList<>();
		rupeeCount = 6;
		
		trees = new ArrayList<>();
		treeCount = (int)(Math.random()*(8-2))+2;
		foliage = new ArrayList<>();
		foliageCount = (int)(Math.random()*(28-15))+15;
				
		fooliageDraw();
		
		try {
			ground = ImageIO.read(this.getClass().getClassLoader().getResource("pathTile1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setFocusable(true);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e.getKeyCode());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				player.keypressed(e.getKeyCode());
				
			}
		});
		nextLevelBro();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D masterGraphs = (Graphics2D)g;
		 setBackground(new Color(0,0,255));
		draw(masterGraphs);
	}


	private void draw(Graphics2D g) {
		gridDraw(g);
		
		for(Foliage e: foliage){
			e.draw(g);
		}
		
		
		synchronized (rupees) {
			for(LosRupees e:rupees){
				e.draw(g);	
		}
			
		
		}
		for(CreepsNsheit e:creeps){
			e.draw(g);
		}
		
		player.draw(g);
		for(Trees e :trees){
			e.draw(g);
		}
		
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
	}

	private void gridDraw(Graphics2D g) {
		for(int row = 0; row<TILES;row++){
			for(int col = 0; col<TILES;col++){
				g.drawImage(ground, row*TILE_SIZE, col*TILE_SIZE,TILE_SIZE,TILE_SIZE ,this);
			}
		}
		
		
	}
	public void fooliageDraw(){
		for(int t = 0; t<treeCount;t++){
			trees.add(new Trees(this,5,session));
		}
		
		for(int f = 0; f<foliageCount;f++){
			
			foliage.add(new Foliage(this,5,session));
		}
	}
	
	public void nextLevelBro(){
		if(creepCount != 0)
		SoundEffect.playSoundEffect(nextLevel);
		
		running = true;
		session = Math.random();
		rupees.clear();
		creeps.clear();
		creepCount++;
		
	
		for(int r = 0; r<rupeeCount; r++){
			rupees.add(new LosRupees(this, 5, session));
			try{
			(new Thread(rupees.get(r))).start();
			}catch (IndexOutOfBoundsException e){
				System.out.println("Failed loading game");
				System.exit(0);
			}
		
		}
		
		for(int c = 0; c < creepCount; c++){
			CreepsNsheit k = new CreepsNsheit(this, session);
			if(k.gettingClose(player)){
				c--;
			}else{	
				creeps.add(k);
				(new Thread(k)).start();
		}
		}
		
	}

	public static void main(String[] args) {
		Game_DTG game = new Game_DTG();
		JFrame x = new JFrame();
		Dimension size = new Dimension(game.getWidth(),game.getHeight());
		x.add(game);
		x.setTitle("Dodge the guys! v1.0");
		x.setResizable(true);
		x.setPreferredSize(size);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.pack();
		x.setVisible(true);
		x.setLocationRelativeTo(null);		
		
	}
	
	public void GameOver(int points){
		backgroundMusic.stop();
		running = false;
		SoundEffect.playSoundEffectHigher(bite);
		SoundEffect.playSoundEffect(eat);
		if(points > highscore)
			SoundEffect.playSoundEffect(youSuck);
		if(points > highscore){
			JOptionPane.showMessageDialog(null, "You lost! New highscore: " + points);
		}else{
			JOptionPane.showMessageDialog(null, "You lost! Rupees found: " + points);
		}
		
		int choice = JOptionPane.showConfirmDialog(
				null, "Play Again?",
				"Warning", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			
			player = new MafakkaPlayah(9, 9, 64,this, session);
			(new Thread(player)).start();
			backgroundMusic.loop();
			creepCount = 0;
			repaint();
			nextLevelBro();
			
		}
		if (choice == JOptionPane.NO_OPTION)
			System.exit(0);

	}
	
	
}
