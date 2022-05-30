package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; // Increases the size of small sprite due to monitor size
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16; // How many tiles are shown on screen
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50; // How large the world is
	public final int maxWorldRow = 50;
	
	// FPS
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound(); // Music
	Sound se = new Sound(); // Sound effects
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; // Game timer
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	
	
	public GamePanel() {
		
		// Sets up canvas for images to be drawn
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		
		// Creates all the objects
		aSetter.setObject();
		
		playMusic(0);
		
	}
	
	public void startGameThread() {
		
		// Starts the game timer
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		// 1000000000 is 1 second in nanoseconds
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		// GAME LOOP
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				// 1 UPDATE: update info such as char position				
				update();
				
				// 2 DRAW: draw the screen with the updated info						
				repaint();
				
				delta--;
				drawCount++;
			}	
			
			if(timer >= 1000000000) { // Every second the timer and drawCount is reset
				
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}	
		}
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);				
		Graphics2D g2 = (Graphics2D)g;
		
		// DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			
			drawStart = System.nanoTime();
			
		}		
		
		// TILES
		tileM.draw(g2); // Tiles first because thats the farthest layer
		
		// OBJECTS
		for(int i = 0; i < obj.length; i++) {
			
			if(obj[i] != null) { // Avoids NullPointer error
				
				obj[i].draw(g2, this); // Draws objects on screen
				
			}
		}
		
		// PLAYER
		player.draw(g2);
		
		// UI
		ui.draw(g2);
		
		// DEBUG
		if(keyH.checkDrawTime == true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time:" + passed, 10, 400);
			System.out.println("Draw Time:" + passed);
			
		}		
		
		g2.dispose(); // saves memory
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
		
	}
}
