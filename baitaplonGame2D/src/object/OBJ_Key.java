package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_Key extends Entity {
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "Key";
		direction = "";
		int size = gp.tileSize/2;
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = size;
		solidArea.height = size;
		collision = true;
		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			defaultImage = uTool.scaleImage(defaultImage,gp.tileSize/2, gp.tileSize/2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
