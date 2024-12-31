package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import mainGame.GamePanel;

public class OBJ_FireBall extends Projectile {

	GamePanel gp;
	public OBJ_FireBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "FireBall";
		speed = 10;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		alive = false;
		
		
		getImage();
	}
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_up_1.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			image1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_down_1.png"));
			image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
			image2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_left_1.png"));
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_right_1.png"));
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
