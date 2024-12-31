package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_Tele extends Entity {
	public OBJ_Tele(GamePanel gp) {
		super(gp);
		type = 3;
		name = "tele";
		direction = "";
		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/tele.png"));
			defaultImage =  uTool.scaleImage(defaultImage, gp.tileSize*2, gp.tileSize*4);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
