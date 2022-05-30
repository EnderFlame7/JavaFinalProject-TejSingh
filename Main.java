package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		// Creates the window for the game
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Outfall");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		// Packs the window to fit the size of the game panel
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();

	}

}
