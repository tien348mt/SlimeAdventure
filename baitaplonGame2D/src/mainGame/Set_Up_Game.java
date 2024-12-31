package mainGame;

import entity.NPC_Cat;
import monster.BOSS_metal;
import monster.MON_metal;
import object.OBJ_House;
import object.OBJ_Tele;
import object.OBJ_Thorn;
import object.OBJ_chest;
import object.OBJ_tuong;

public class Set_Up_Game {

	GamePanel gp;
	
	
	public Set_Up_Game(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		
//		gp.kami = new OBJ_tuong(gp);
//		gp.kami.worldX = 40*gp.tileSize;//41
//		gp.kami.worldY = 0*gp.tileSize;		//3
		
		gp.obj[0] = new OBJ_House(gp);
		gp.obj[0].worldX = 6*gp.tileSize;
		gp.obj[0].worldY = 4*gp.tileSize;	
		
		gp.obj[1] = new OBJ_Tele(gp);
		gp.obj[1].worldX = 14*gp.tileSize;
		gp.obj[1].worldY = 10*gp.tileSize;	
		

		gp.obj[2] = new OBJ_tuong(gp);
		gp.obj[2].worldX = 40*gp.tileSize;
		gp.obj[2].worldY = 0*gp.tileSize;	
		
		gp.obj[3] = new OBJ_Tele(gp);
//		gp.obj[3].worldX = 48*gp.tileSize;
//		gp.obj[3].worldY = 6*gp.tileSize;
		gp.obj[3].worldX = 32*gp.tileSize;
		gp.obj[3].worldY = 27*gp.tileSize;
		
		gp.obj[4] = new OBJ_chest(gp);
		gp.obj[4].worldX = 10*gp.tileSize;
		gp.obj[4].worldY = 8*gp.tileSize;	
		
		gp.obj[5] = new OBJ_Thorn(gp);
		gp.obj[5].worldX = 9*gp.tileSize + 10;
		gp.obj[5].worldY = 11*gp.tileSize - 20;	
		
	
	}
	public void setNPC() {
		gp.npc[0] = new NPC_Cat(gp);
		gp.npc[0].worldX = 8* gp.tileSize;
		gp.npc[0].worldY = 8* gp.tileSize;
	}
	public void setMonster() {
		
		gp.monster[0] = new MON_metal(gp);
		gp.monster[0].worldX = 25*gp.tileSize;
		gp.monster[0].worldY = 27*gp.tileSize;
		
		gp.monster[1] = new MON_metal(gp);
		gp.monster[1].worldX = 20*gp.tileSize;
		gp.monster[1].worldY = 27*gp.tileSize;
		
		gp.monster[2] = new BOSS_metal(gp);
		gp.monster[2].worldX = 20*gp.tileSize;
		gp.monster[2].worldY = 25*gp.tileSize;
		
	}
}
