package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import effect.FrameImage;
import entity.Entity;
import object.OBJ_Yoi;
import object.OBJ_heart;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	FrameImage image;
	BufferedImage heart_full, heart_half, heart_blank, YoiImage;
	public int commandNum = 0;
	public boolean gameFinish = false;
	
	Font arial_40, arial_80B;
	public boolean messengeOn = false;
	public boolean messengeItemOn = false;
	public String messenge = "";
	public String messenge1 = "";
	int messengeCounter = 0;
//	public boolean gameFinish = false;
	public String currentDialogue = "";

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		Entity heart = new OBJ_heart(gp);
		Entity yoi = new OBJ_Yoi(gp);
		heart_full = heart.image1;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		YoiImage = yoi.defaultImage;
	}

	public void showMessenge(String text) {
		messenge = text;
		messengeOn = true;
	}
	public void showMessengeItem(String text) {
		messenge1 = text;
		messengeItemOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//DRAW TITLESTATUS
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//DRAW PLAYSTATE
		if(gp.gameState == gp.playState) {
			if(gameFinish == true) {
				gp.gameState = gp.gameDone;
				gameFinish = false;
			}else {
				drawPlayerLife();
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
				g2.setColor(Color.white);
				g2.drawImage(YoiImage, gp.tileSize/2, gp.tileSize/2 + 60, gp.tileSize, gp.tileSize, null);
				g2.drawString("x "+ gp.player.Yoi, 74, 115);
				if(messengeOn == true) {
					drawContact();
				}
				if(messengeItemOn == true) {
					drawNoticeItem();
				}
			}
			
		}
		// PAUSESTATE
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOUGESTATE
		if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialougeStateScreen();
		}
		// GAMEOVERSTATE
		if(gp.gameState == gp.gameOver) {
			drawGameOver();
		}
		//GAMEDONE
		if(gp.gameState == gp.gameDone) {
			drawGameDone();
		}
		//SOUNDOPTION
		if(gp.gameState == gp.soundOption) {
			drawSoundOption();
		}

	}
	
	public void drawSoundOption() {
		
		BufferedImage image,image3,image2;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "";
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x = getXPause(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		try {
			image2 = ImageIO.read(getClass().getResourceAsStream("/menu/buttomsound.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/menu/2.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/menu/soundmenu.png"));
			x = gp.screenWidth/2 -gp.tileSize*3;
			y+= gp.tileSize;
			g2.drawImage(image, x, y,gp.tileSize*6,gp.tileSize*7,null);
			if(commandNum == 0) {
				g2.drawImage(image3, x, y+gp.tileSize*2 +90,gp.tileSize + 20,gp.tileSize + 20,null);
				switch(gp.music.volumeScale) {
				case 0:
					g2.drawImage(image2, x + 75, y+gp.tileSize*2 + 115,gp.tileSize/2,gp.tileSize/2,null);
					break;
				case 1:
					g2.drawImage(image2, x+115, y+gp.tileSize*2 +115,gp.tileSize/2,gp.tileSize/2,null);
					break; 
				case 2:
					g2.drawImage(image2, x+155, y+gp.tileSize*2 +115,gp.tileSize/2,gp.tileSize/2,null);
					break; 
				case 3: 
					g2.drawImage(image2, x+195, y+gp.tileSize*2 +115,gp.tileSize/2,gp.tileSize/2,null);
					break; 
				case 4:
					g2.drawImage(image2, x+238, y+gp.tileSize*2 +115,gp.tileSize/2,gp.tileSize/2,null);
					break; 
				}
			}
			if(commandNum == 1) {
				g2.drawImage(image3, x + 20, y+gp.tileSize*2 + 150,gp.tileSize + 20,gp.tileSize + 20,null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawGameDone() {
	BufferedImage image,image3;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "";
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x = getXPause(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		try {
			image3 = ImageIO.read(getClass().getResourceAsStream("/menu/2.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/menu/Complete.png"));
			x = gp.screenWidth/2 -gp.tileSize*3;
			y+= gp.tileSize;
			g2.drawImage(image, x, y,gp.tileSize*6,gp.tileSize*6,null);
			if(commandNum == 0) {
				g2.drawImage(image3, x+40, y+gp.tileSize*2 +10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 1) {
				g2.drawImage(image3, x+170, y+gp.tileSize*2 +10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void drawGameOver() {
		
		BufferedImage image,image3;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "";
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x = getXPause(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		try {
			image3 = ImageIO.read(getClass().getResourceAsStream("/menu/2.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/menu/deadImage.png"));
			x = gp.screenWidth/2 -gp.tileSize*3;
			y+= gp.tileSize;
			g2.drawImage(image, x, y,gp.tileSize*6,gp.tileSize*6,null);
			if(commandNum == 0) {
				g2.drawImage(image3, x+40, y+gp.tileSize*3 - 30,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 1) {
				g2.drawImage(image3, x+170, y+gp.tileSize*3 -30,gp.tileSize + 20,gp.tileSize + 20,null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawNoticeItem() {
		int x = gp.tileSize * 2;
		int y = 8 * gp.tileSize;
		int width = gp.screenWidth - gp.tileSize * 4;
		int height = gp.tileSize * 3;
		drawSubWindow(x, y, width, height);
		g2.setFont(g2.getFont().deriveFont(30F));
		g2.drawString(messenge1, x +100, y + 80);
	
		
		messengeCounter++;
		if(messengeCounter >30) {
			
			messengeCounter = 0;
			messengeItemOn = false;
		}
	}

	public void drawPlayerLife() {
		
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		//DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+= gp.tileSize;
		}
		//RESET
		 x = gp.tileSize/2;
		 y = gp.tileSize/2;
		 i = 0;
		 //DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+= gp.tileSize;
		}
	}
	
	public void drawDialougeStateScreen() {
		// window
		int x = gp.tileSize * 2;
		int y = 8 * gp.tileSize;
		int width = gp.screenWidth - gp.tileSize * 4;
		int height = gp.tileSize * 3;

		drawSubWindow(x, y, width, height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString(currentDialogue, x, y);
	}

	public void drawContact() {
			int x = gp.player.screenX + 40;
			int y = gp.player.screenY - 16;
			int width = 130;
			int height = 32;

			drawSubWindow(x, y, width, height);

			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F));
			x += 16;
			y += 21;
			g2.drawString(messenge, x, y);
	}

	private void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

	}
	
	
	public void drawTitleScreen() {
		BufferedImage image,image1,image2,image3;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "SLIMEADVENTURE";
		int x = getXPause(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		try {
			image2 = ImageIO.read(getClass().getResourceAsStream("/menu/background.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/menu/2.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/menu/menu_background.png"));
			image1 = ImageIO.read(getClass().getResourceAsStream("/menu/button.png"));
			x = gp.screenWidth/2 -gp.tileSize*3;
			y+= gp.tileSize;
			g2.drawImage(image2, 0, 0,gp.screenWidth,gp.screenHeight,null);
			g2.drawImage(image, x, y,gp.tileSize*6,gp.tileSize*6,null);
			g2.drawImage(image1, x+80, y+gp.tileSize*2,gp.tileSize*3,gp.tileSize*3,null);
			if(commandNum == 0) {
				g2.drawImage(image3, x+20, y+gp.tileSize*2 - 10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 1) {
				g2.drawImage(image3, x+20, y+gp.tileSize*3 -10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 2) {
				g2.drawImage(image3, x+20, y+gp.tileSize*4 -10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void drawPauseScreen() {
		BufferedImage image,image1,image3;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "";
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x = getXPause(text);
		int y = gp.tileSize*2;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+5, y+5);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		try {
			image3 = ImageIO.read(getClass().getResourceAsStream("/menu/2.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/menu/menu_background.png"));
			image1 = ImageIO.read(getClass().getResourceAsStream("/menu/button.png"));
			x = gp.screenWidth/2 -gp.tileSize*3;
			y+= gp.tileSize;
			g2.drawImage(image, x, y,gp.tileSize*6,gp.tileSize*6,null);
			g2.drawImage(image1, x+80, y+gp.tileSize*2,gp.tileSize*3,gp.tileSize*3,null);
			if(commandNum == 0) {
				g2.drawImage(image3, x+20, y+gp.tileSize*2 - 10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 1) {
				g2.drawImage(image3, x+20, y+gp.tileSize*3 -10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
			if(commandNum == 2) {
				g2.drawImage(image3, x+20, y+gp.tileSize*4 -10,gp.tileSize + 20,gp.tileSize + 20,null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int getXPause(String text) {
		int Length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - Length / 2;
		return x;
	}
}
