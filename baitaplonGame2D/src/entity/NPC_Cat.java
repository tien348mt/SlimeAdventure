package entity;

import java.awt.Graphics2D;
import effect.CacheDataLoader;
import mainGame.GamePanel;


public class NPC_Cat extends Entity{
	public NPC_Cat(GamePanel gp) {
		super(gp);
		name = "cat";
		
		int size = gp.tileSizeplayer;
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 48;
		solidArea.height = size - 40;
		collision = true;
		
		getImage();
		setDiaLogue();
	}
	public void getImage() {
		anim = CacheDataLoader.getInstance().getAnimation("npcmove");
	}
	public void setDiaLogue() {
		dialogues[0] = "Xin Chào, Slime được chọn";
		dialogues[1] = "Tượng thần đã giao cho bạn một nhiệm vụ";
		dialogues[2] = "Đó là đánh bại quái vật kim loại để nhận chìa khóa";
		dialogues[3] = "Hãy đi vào cánh cổng ở dưới và đem chìa khóa về";
		dialogues[4] = "Mở rương và lấy hòn đá sức mạnh";
		dialogues[5] = "";
	}
	public void draw(Graphics2D g2,GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX ;
        int screenY = worldY - gp.player.worldY + gp.player.screenY ;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	   anim.Update(System.nanoTime());
        	   anim.draw(screenX, screenY, g2, gp.tileSizeplayer, gp.tileSizeplayer);
        	   
         }
    }
	public void speak() {
		if(dialogues[dialougueIndex] == null ) {
			dialougueIndex = 0;
		
		}
		gp.ui.currentDialogue = dialogues[dialougueIndex];
	}
}
