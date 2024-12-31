package object;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_House extends Entity {
	public OBJ_House(GamePanel gp) {
		super(gp);
		name = "house";
		direction = "";
		int size = gp.tileSize*4;
		solidArea.x = 0;
		solidArea.y = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = size;
		solidArea.height = size - 96;
		collision = true;

		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/NPCHOUSE1.png"));
			defaultImage = uTool.scaleImage(defaultImage, gp.tileSize*4, gp.tileSize*4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
