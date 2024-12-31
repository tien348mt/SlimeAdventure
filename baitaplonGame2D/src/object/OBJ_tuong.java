package object;


import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainGame.GamePanel;

public class OBJ_tuong extends Entity{
	GamePanel gp;
	public OBJ_tuong(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "tuong";
		direction = "";
		//solidArea = new Rectangle();
		int size = gp.tileSize*3;
		solidArea.x = 28;
		solidArea.y = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = size - 28*2;
		solidArea.height = size - 96;
		collision = true;
		setDiaLogue();
		try {
			defaultImage = ImageIO.read(getClass().getResourceAsStream("/objects/tuong_than.png"));
			defaultImage = uTool.scaleImage(defaultImage, gp.tileSize*3, gp.tileSize*3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setDiaLogue() {
		dialogues[0] = "Hòn đá sức mạnh là sức mạnh nguyên thủy của thần";
		dialogues[1] = "Kẻ nắm giữ viên đá sẽ được ban thưởng";
		dialogues[2] = "Sức mạnh tuân chảy làm thay đổi dòng mana thuần";
		dialogues[3] = "Hãy tìm người hướng dẫn";
		dialogues[4] = "Người đó sẽ dẫn dắt ngươi đến với viên đá";
		dialogues[5] = "";
		dialogues[6] = "Chuyển đổi nguyên tố";
		dialogues[7] = "Nguyên tố HỎA";
		dialogues[8] = "";
		
	}
	public void speak() {
		if(dialogues[dialougueIndex] == null ) {
			dialougueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialougueIndex];
	}
	public void speak1() {
		if(dialogues[dialougueIndex1] == null ) {
			dialougueIndex1 = 6;
		}
		gp.ui.currentDialogue = dialogues[dialougueIndex1];
	}
}
