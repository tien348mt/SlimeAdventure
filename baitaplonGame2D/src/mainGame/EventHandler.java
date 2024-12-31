package mainGame;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][];
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;

	public EventHandler(GamePanel gp) {
		this.gp = gp;

		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

		int col = 0;
		int row = 0;

		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 0;
			eventRect[col][row].y = 0;
			eventRect[col][row].width =70;
			eventRect[col][row].height = 38;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}

	}

	public boolean checkEvent() {
		int x = Math.abs(gp.player.worldX - previousEventX);
		int y = Math.abs(gp.player.worldY - previousEventY);

		int distance = Math.max(x, y);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		boolean check = false;

		if (canTouchEvent == true) {
			if (hit(9, 11, "any") == true) {
				gp.playSE(2);
				damagePit(gp.dialogueState);
				check = true;
			}
		}
		if (hit(13, 12, "any") == true || hit(13, 13, "any") == true) {
			gp.ui.showMessenge("T-Dịch chuyển");
			if (gp.keyH.Tpressed == true) {
				gp.playSE(6);
				Teleport2(gp.gameState);
				check = true;
			}
		}
		if (hit(31, 29, "any") == true) {
			gp.ui.showMessenge("T-Dịch chuyển");
			if (gp.keyH.Tpressed == true) {
				gp.playSE(6);
				Teleport1(gp.gameState);
				check = true;
			}
		}
//		if (hit(47, 7, "any") == true || hit(47, 8, "any") == true) {
//			gp.ui.showMessenge("T-Dịch chuyển");
//			if (gp.keyH.Tpressed == true) {
//				Teleport1(gp.gameState);
//				check = true;
//			}
//		}
		return check;
	}

	public boolean hit(int col, int row, String reqDirection) {

		boolean hit = false;

		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

		if (gp.player.solidArea.intersects(eventRect[col][row])) {
			if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;

				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

		return hit;
	}

	public void damagePit(int gameState) {

		gp.gameState = gameState;
		gp.ui.currentDialogue = "Đừng chạm vào bụi gai";
		gp.player.life -= 1;
		canTouchEvent = false; 
	}

	public void Teleport1(int gameState) {
		gp.gameState = gameState;
		gp.player.worldX = gp.tileSize * 12;
		gp.player.worldY = gp.tileSize * 12;
	}

	public void Teleport2(int gameState) {
		gp.gameState = gameState;
//		gp.player.worldX = gp.tileSize * 46;
//		gp.player.worldY = gp.tileSize * 7;
		gp.player.worldX = gp.tileSize * 30;
		gp.player.worldY = gp.tileSize * 29;
	}
}
