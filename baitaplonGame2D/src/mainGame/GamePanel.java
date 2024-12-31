package mainGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTING
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	final int playerScale = 4;

	public final int tileSize = originalTileSize * scale;
	public final int tileSizeplayer = originalTileSize * playerScale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHeight = maxScreenRow * tileSize;

	// WORLD SETTINGS
	public int maxWorldCol;
	public int maxWorldRow;
	public final int worldWidth = tileSize + maxWorldCol;
	public final int worldHeight = tileSize + maxWorldRow;

	// FPS
	int FPS = 60;

	public TileManager tileM = new TileManager(this);
	Thread gameThread;
	Sound music = new Sound();
	Sound se = new Sound();
	// public Entity entity = new Entity(this);
	public KeyHandler keyH = new KeyHandler(this);
	public Set_Up_Game setUp = new Set_Up_Game(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
//	public Entity kami = new OBJ_tuong(this);
//	public OBJ_House npcHouse = new OBJ_House(this);
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[10];
	public Entity obj[] = new Entity[20];
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	public ArrayList<Entity> projectileList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();

	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int gameOver = 4;
	public final int gameDone = 5;
	public final int soundOption = 6;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);// if set to true, all the drawing from this component will be done in an
										// offscreen painting buffer
		this.addKeyListener(keyH);
		this.setFocusable(true);// with this, GamePanel can focus to receive key input

	}

	public void setupGame() {
		setUp.setObject();
		setUp.setNPC();
		setUp.setMonster();
		gameState = titleState;
		playMusic(0);
	}

	public void restart() {
		player.setDefaultValues();
		setUp.setObject();
		setUp.setNPC();
		setUp.setMonster();
		playMusic(0);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextTimeDraw = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			update();

			repaint();

			try {

				double remainingTime = nextTimeDraw - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextTimeDraw += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void update() {

		if (gameState == playState) {
			player.update();

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					if (monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if (monster[i].alive == false) {

					}
				}
			}

			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					if (projectileList.get(i).alive == true) {
						projectileList.get(i).update();
					}
					if (projectileList.get(i).alive == false) {
						projectileList.remove(i);
					}
				}
			}

		}
		if (gameState == pauseState) {

		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		// draw tiles
		if (gameState == titleState) {
			ui.draw(g2);

		} else {
			tileM.draw(g2);
			if (gameState == pauseState) {

			}
			// kami.draw(g2, this);

			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			if (gameState == playState || gameState == dialogueState) {
				// entityList.add(player);
				player.draw(g2, this);
			}

			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {

					int result = Integer.compare(e1.worldY, e2.worldY);

					return result;
				}
			});

			// DRAW ENTITIES
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2, this);
			}
			entityList.clear();

			ui.draw(g2);

			g2.dispose();
		}
	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}

	public void playSE(int i) {

		se.setFile(i);
		se.play();
	}
}
