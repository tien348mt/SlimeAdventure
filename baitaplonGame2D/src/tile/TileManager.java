package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mainGame.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	boolean drawPath = true;
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();

	public TileManager(GamePanel gp) {

		this.gp = gp;

		InputStream is = getClass().getResourceAsStream("/maps/tile01.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line;
		try {
			while ((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		tile = new Tile[fileNames.size()];
//		System.out.println(fileNames.size());
		getTileImage();

		is = getClass().getResourceAsStream("/maps/map01.txt");
		br = new BufferedReader(new InputStreamReader(is));

		try {
			String line2;
			line2 = br.readLine();
			String maxTile[] = line2.split(" ");

			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loadMap("/maps/map01.txt");
	}

	public void getTileImage() {

		for (int i = 0; i < fileNames.size(); i++) {

			String fileName;
			boolean collision;

			fileName = fileNames.get(i);
			// System.out.println(fileName);
			if (collisionStatus.get(i).equals("true")) {
				collision = true;
			} else {
				collision = false;
			}

			try {
				tile[i] = new Tile();
				tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + fileName));
				tile[i].collision = collision;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void loadMap(String filePath) {

		try {

			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");// split the string at a space

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
//			
//			if(drawPath == true) {
//				g2.setColor(new Color(255,0,0,70));
//				
//				for(int i= 0;i<gp.pFinder.pathList.size();i++) {
//					
//					 worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
//					 worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
//					 screenX = worldX - gp.player.worldX + gp.player.screenX;
//					 screenY = worldY - gp.player.worldY + gp.player.screenY;
//					 
//					 g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//				}
//			}

		}

	}
}
