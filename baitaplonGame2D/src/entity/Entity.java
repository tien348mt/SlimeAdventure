package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import effect.Animation;
import mainGame.GamePanel;
import mainGame.UtilityTool;
import object.OBJ_Key;

public class Entity {

		GamePanel gp;
		public BufferedImage image, image1,image2,image3, defaultImage;
		public int worldX, worldY;
		public int speed ;
		public String name;
		
		//public BufferedImage image[] = new BufferedImage[20];
		public String direction;
	
		
		public int spriteCounter = 0;
		
		public int spriteNum = 1;
		public int solidAreaDefaultX,solidAreaDefaultY;
		public boolean collision = false;
		public boolean collisionOn = false;
		public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // hit box
		public Animation anim = new Animation();
		public Animation anim1 = new Animation();
		public int actionCounter = 0;
		public String dialogues[] = new String[20];
		public int dialougueIndex = 0;
		public int dialougueIndex1 = 10;
		public UtilityTool uTool = new UtilityTool();
		public Projectile projectile;
		
		public boolean invincible = false;
		public int invincibleCounter  = 0;
		public int hpBarCounter  = 0;
		public int type; //0=player , 1 = monster
		
		public int attack;
		public boolean alive = true;
		public boolean dying = false;
		public boolean hpBarOn = false;
		public boolean onPath = false;
		
		
		// PLAYER STATUS
		public int maxLife;
		public int life;
		
		public Entity(GamePanel gp) {
			this.gp = gp;
			
		}
		public void setAction() {}
		public void damageReaction() {};
		public void checkdropItem() {
			dropItem(new OBJ_Key(gp));
		};
		public void dropItem(Entity dropItem) {
			
			for(int i =0; i < gp.obj.length; i++) {
				if(gp.obj[i] == null) {
					gp.obj[i] = dropItem;
					gp.obj[i].worldX = worldX + 20;
					gp.obj[i].worldY = worldY + 30;
					break;
				}
			}
		}
		public void speak() {}
		public void speak1() {};
		public void checkCollision() {
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, false);
			gp.cChecker.checkEntity(this, gp.npc);
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			
			if(this.type == 2 && contactPlayer == true) {
				if(gp.player.invincible == false) {
					gp.player.life -=1;
					gp.player.invincible = true;
				}
			}
			
		}
		public void update() {
				setAction();
				checkCollision();
				//IF COLLISION IS FALSE , NPC CAN MOVE
				if(collisionOn == false) {
					
					switch(direction) {
					case "up":
						worldY -= speed;
						break;
					case "down":
						worldY += speed;
						break;
					case "left":
						worldX -= speed;
						break;
					case "right":
						worldX += speed;
						break;
					}
				}
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1 ) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		public void draw(Graphics2D g2,GamePanel gp) {
		
			int screenX = worldX - gp.player.worldX + gp.player.screenX ;
			int screenY = worldY - gp.player.worldY + gp.player.screenY ;
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize< gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - 2*gp.player.screenY
					&& worldY - gp.tileSize< gp.player.worldY + 2*gp.player.screenY) {
				
				switch(direction) {
				case "up":
					defaultImage = image;
					break;
				case "down":
					defaultImage = image1;
					break;
				case "left":
					defaultImage = image2;
					break;
				case "right":
					defaultImage = image3;
					break;
				case "":
					defaultImage = defaultImage;
				}
			
			}
			g2.drawImage(defaultImage, screenX ,screenY, null);
				
		}
//	public void searchPath(int goalCol, int goalRow) {
//		
//		int startCol = (worldX + solidArea.x)/gp.tileSize;
//		int startRow = (worldY + solidArea.y)/gp.tileSize;
//		
//		//System.out.println(startCol +"+"+ startRow );
//		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
//		
//		if(gp.pFinder.search() == true) {
//			
//			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
//			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
//			
//			int enLeftX = worldX + solidArea.x;
//			int enRightX = worldX + solidArea.x + solidArea.width;
//			int enTopY = worldY + solidArea.y;
//			int enBottomY = worldY + solidArea.y + solidArea.height;
//			
//			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
//				direction = "up";
//			}
//			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
//				direction = "down";
//			}
//			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
//				
//				if(enLeftX > nextX) {
//					direction = "left";
//				}
//				if(enLeftX < nextX) {
//					direction = "right";
//				}
//			}
//			else if(enTopY > nextY && enLeftX > nextX) {
//				direction = "up";
//				checkCollision();
//				if(collisionOn == true) {
//					direction = "left";
//				}
//			}
//			else if(enTopY > nextY && enLeftX < nextX) {
//				direction = "up";
//				checkCollision();
//				if(collisionOn == true) {
//					direction = "right";
//				}
//			}
//			else if(enTopY < nextY && enLeftX > nextX) {
//				direction = "down";
//				checkCollision();
//				if(collisionOn == true) {
//					direction = "left";
//				}
//			}
//			else if(enTopY < nextY && enLeftX < nextX) {
//				direction = "down";
//				checkCollision();
//				if(collisionOn == true) {
//					direction = "right";
//				}
//			}
////			int nextCol = gp.pFinder.pathList.get(0).col;
////			int nextRow = gp.pFinder.pathList.get(0).row;
////			if(nextCol == goalCol && nextRow == goalRow) {
////				onPath = false;
////			}
//		}
	//}
}
