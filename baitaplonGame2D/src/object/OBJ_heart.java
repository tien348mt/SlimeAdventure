package object;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_heart extends Entity{
	public OBJ_heart(GamePanel gp) {
		super(gp);
		name = "heart";

		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
			image1 =  uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
			image2 =  uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 =  uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
