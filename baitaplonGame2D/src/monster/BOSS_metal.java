package monster;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import effect.CacheDataLoader;
import entity.Entity;
import mainGame.GamePanel;


public class BOSS_metal extends Entity {
	GamePanel gp;
	public BOSS_metal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = 2;
		name = "BossKnight";
		speed = 1;
		direction = "right";
		maxLife = 12;
		life = maxLife;
		alive = true;

		int size = gp.tileSize * 2;
		solidArea.x = -28;
		solidArea.y = 0;//-10
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = size - 10;
		solidArea.height = size - 20;

		collision = true;
		getImage();
	}

	public void getImage() {

		anim = CacheDataLoader.getInstance().getAnimation("bossmove");
		anim1 = CacheDataLoader.getInstance().getAnimation("bossdie");
	}

	public void draw(Graphics2D g2, GamePanel gp) {

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			if (life <= 0) {
				if (anim1.isLastFrame() == false) {
					anim1.Update(System.nanoTime());
					anim1.draw(screenX, screenY, g2, gp.tileSize * 3, gp.tileSize * 3);
				}
				if (anim1.isLastFrame() == true) {
					int monsterIndex = gp.cChecker.checkEntity(gp.player.projectile, gp.monster);
					if (monsterIndex != 999) {
						gp.monster[2].checkdropItem();
						gp.monster[2] = null;
					}
				}

			} else {
				anim.Update(System.nanoTime());
				anim.draw(screenX, screenY, g2, gp.tileSize * 3, gp.tileSize * 3);
			}
			// DRAW HP BAR
			if (hpBarOn == true) {
				double x = (int) gp.tileSize / maxLife;
				double HP = x * life;
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 30, screenY - 35, gp.tileSize, 10);

				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX - 30, screenY - 35, (int) HP, 10);

				hpBarCounter++;
				if (hpBarCounter > 300) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if (invincible == true) {
				hpBarOn = true;
				invincible = false;
			}
		}
	}

	public void setAction() {
//		if (onPath == true) {
//
//			int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize ;
//			int goalRow = (gp.player.worldX + gp.player.solidArea.y) / gp.tileSize ;
////			int goalCol = 9;
////			int goalRow = 10;
//			System.out.println(goalCol + "+"+goalRow);
//			searchPath(goalCol, goalRow);
//		} else {
			actionCounter++;
			if (actionCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1;

				if (i < 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				if (i > 75 && i <= 100) {
					direction = "right";
				}

				actionCounter = 0;
			}
		//}

	}

	public void damageReaction() {
		actionCounter = 0;
////		onPath = true;
		speed = 4;
		switch(gp.player.projectile.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
}
