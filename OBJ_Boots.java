package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject{
	
	GamePanel gp;

	public OBJ_Boots(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Boots"; // Instantiates name to item name
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png")); // Instantiates image to file path for image
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		}	
	}	
}
