package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;
import mainGame.GamePanel;
import mainGame.KeyHandler;
import object.OBJ_FireBall;

public class Player extends Entity {

	KeyHandler keyH;
	Animation anim = new Animation();
	Animation anim1 = new Animation();
	Animation anim2 = new Animation();
	Animation anim3 = new Animation();
	public final int screenX;
	public final int screenY;
	FrameImage frame1 = new FrameImage();
	FrameImage frame2 = new FrameImage();
	FrameImage frame3 = new FrameImage();
	FrameImage frame4 = new FrameImage();
	public int haskey;
	public int Yoi;
	public int trans;
	int shootCounter;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		haskey = 0;
		Yoi = 0;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 25;
		solidArea.height = 30;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 40;// 35
		worldY = gp.tileSize * 8;// 13
		speed = 3;
		direction = "right";
		// PLAYERSTATUS
		maxLife = 6;
		life = maxLife;
		projectile = new OBJ_FireBall(gp);
	}

	public void getPlayerImage() {
//		
		frame1 = CacheDataLoader.getInstance().getFrameImage("frame1");
		anim = CacheDataLoader.getInstance().getAnimation("move");
		anim1 = CacheDataLoader.getInstance().getAnimation("move");
		anim1.flipAllImage();
		frame2 = CacheDataLoader.getInstance().getFrameImage("frame1");
		frame2.flipImage();
		frame3 = frame1;
		anim2 = CacheDataLoader.getInstance().getAnimation("transform1");
		anim3 = CacheDataLoader.getInstance().getAnimation("transform2");
		frame4 = CacheDataLoader.getInstance().getFrameImage("frame27");
	}

	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true
				|| keyH.transform == true || keyH.Fpressed == true || keyH.Tpressed == true) {
			
			if (keyH.upPressed == true) {
				direction = "up";

			} else if (keyH.downPressed == true) {
				direction = "down";

			} else if (keyH.leftPressed == true) {
				direction = "left";

			} else if (keyH.rightPressed == true) {
				direction = "right";

			} else if (keyH.transform == true) {
				direction = "transform";

			} else if (keyH.Fpressed == true) {
				direction = "contact";
			} else if (keyH.Tpressed == true) {
				direction = "tele";
			}
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkEntity(this, gp.npc);
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonter(monsterIndex);

			interactNPC();
			trans = interactStatuesOfGods();	
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObj(objIndex);

			// pickUpObject(objIndex);
			if (keyH.Fpressed == false) {
				if (gp.eHandler.checkEvent() == true) {

				} else {
					gp.gameState = gp.playState;
				}
			}
			if (collisionOn == false) {
				switch (direction) {
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
				case "transform":

					break;
				}
			}
		}

		if (keyH.shotPressed == true && projectile.alive == false && shootCounter == 30) {

			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shootCounter = 0;
			gp.playSE(2);
		}

		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shootCounter < 30) {
			shootCounter++;
		}
		if (life <= 0) {
			Yoi = 0;
			gp.gameState = gp.gameOver;
			gp.stopMusic();
			gp.playSE(4);
		}
	}

	public void pickUpObj(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			switch (objectName) {
			case "Key":
				 gp.playSE(3);
				haskey++;
				System.out.println(haskey);
				gp.obj[i] = null;
				break;
			case "Chest":
				if (haskey > 0) {
					gp.playSE(8);
					gp.obj[i] = null;
					haskey--;
					Yoi++;
					gp.ui.showMessengeItem("Nhận được thần đồng hiến tế");
				} else {
					gp.ui.showMessengeItem("Cần chìa khóa để mở");
				}

				break;
			}
		}

	}

	private void contactMonter(int i) {

		if (i != 999) {
			if (invincible == false) {
				life -= 1;
				invincible = true;
			}
		}

	}
	
	public int interactStatuesOfGods(){
		int i =0;
		if (45 < worldY && worldY < 162 && worldX >= 1935 && worldX <= 2028) {
			gp.ui.showMessenge("F-Nói chuyện");
			if (keyH.Fpressed == true) {
				if(Yoi == 0) {
					gp.gameState = gp.dialogueState;
					gp.obj[2].speak();
					gp.obj[2].dialougueIndex++;
					if (gp.obj[2].dialougueIndex == 6) {
						keyH.Fpressed = false;
						gp.obj[2].dialougueIndex = 0;
					}
				} else if(Yoi == 1) {
					gp.gameState = gp.dialogueState;
					gp.obj[2].speak1();
					gp.obj[2].dialougueIndex1++;
					if (gp.obj[2].dialougueIndex1 == 9) {
						keyH.Fpressed = false;
						gp.obj[2].dialougueIndex1 = 6;
					}
					if(keyH.Fpressed == false) {
//						gp.ui.gameFinish = true;
						i = 1;
					}
				}
			}
		}
		return i;
	}
	public void interactNPC() {
		if (400 < worldY && worldY < 447 && worldX >= 339 && worldX <= 432) {
			gp.ui.showMessenge("F-Nói chuyện");
			if (keyH.Fpressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[0].speak();
				gp.npc[0].dialougueIndex++;
				if (gp.npc[0].dialougueIndex == 6) {
					keyH.Fpressed = false;
					gp.npc[0].dialougueIndex = 0;
				}
			}

		} else {
			gp.ui.messengeOn = false;
		}
	}

	public void damageMonster(int i, int attack) {
		if (i != 999) {
			if (gp.monster[i].invincible == false) {
				gp.playSE(7);
				gp.monster[i].life -= attack;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

			}

			if (gp.monster[i].life <= 0) {
				gp.monster[i].alive = false;
			}
		}
	}

	public void draw(Graphics2D g2, GamePanel gp) {
		if(trans == 0) {
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true
				|| keyH.transform == true) {
			if (keyH.transform == true) {
				anim2.Update(System.nanoTime());
				anim2.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				anim2.reset();
			}
			if (keyH.rightPressed == true && keyH.leftPressed == false) {
				// direction = "right";
				frame3 = frame1;
				anim.Update(System.nanoTime());
				anim.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
			}

			if (keyH.leftPressed == true && keyH.rightPressed == false) {
				// direction = "left";
				frame3 = frame2;
				anim1.Update(System.nanoTime());
				anim1.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
			}
			if (keyH.upPressed == true) {
				if (frame3 == frame1) {
					anim.Update(System.nanoTime());
					anim.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}
				if (frame3 == frame2) {
					anim1.Update(System.nanoTime());
					anim1.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}

			}
			if (keyH.downPressed == true) {
				if (frame3 == frame1) {
					anim.Update(System.nanoTime());
					anim.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}
				if (frame3 == frame2) {
					anim1.Update(System.nanoTime());
					anim1.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}

			}
			if (keyH.leftPressed == true && keyH.rightPressed == true) {
				if (direction == "right") {
					frame3 = frame1;
					anim.Update(System.nanoTime());
					anim.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}
				if (direction == "left") {
					frame3 = frame2;
					anim1.Update(System.nanoTime());
					anim1.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}
			}
		} else {
			if (direction == "up" || direction == "down" || direction == "contact" || direction == "tele") {
				frame3.draw(g2, screenX, screenY, gp.tileSizeplayer, gp.tileSizeplayer);
			}
			if (direction == "right") {
				frame1.draw(g2, screenX, screenY, gp.tileSizeplayer, gp.tileSizeplayer);
			}
			if (direction == "left") {
				frame2.draw(g2, screenX, screenY, gp.tileSizeplayer, gp.tileSizeplayer);
			}
			if (direction == "transform") {
				if (anim2.isLastFrame() == false) {
					anim2.Update(System.nanoTime());
					anim2.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);

					// Wait until anim2 has finished drawing before drawing anim3
				} else {
					anim3.Update(System.nanoTime());
					anim3.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
				}
			}

		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		}
		if(trans == 1) {
			anim2.Update(System.nanoTime());
			anim2.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
			if (anim2.isLastFrame() == true) {
				trans = 0;
				Yoi = 0;
				gp.stopMusic();
				gp.playSE(1);
				gp.ui.gameFinish = true;
			}
		}
	}
}
