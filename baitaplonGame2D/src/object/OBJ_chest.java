package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_chest extends Entity {
	public OBJ_chest(GamePanel gp) {
		super(gp);
		name = "Chest";
		direction = "";
		int size = gp.tileSize;
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = size;
		solidArea.height = size;
		collision = true;
		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			defaultImage = uTool.scaleImage(defaultImage,gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
