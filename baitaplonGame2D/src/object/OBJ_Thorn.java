package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_Thorn extends Entity {
	public OBJ_Thorn(GamePanel gp) {
		super(gp);
		name = "Thorn";
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
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/thorn.png"));
			defaultImage = uTool.scaleImage(defaultImage,gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
