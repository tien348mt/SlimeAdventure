package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_Yoi extends Entity {
	public OBJ_Yoi(GamePanel gp) {
		super(gp);
		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/Yoi.png"));
			defaultImage = uTool.scaleImage(defaultImage,gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
