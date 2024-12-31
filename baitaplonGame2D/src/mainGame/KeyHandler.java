package mainGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, transform, Fpressed, Tpressed, shotPressed;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(gp.gameState == gp.titleState || gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_W) {
				gp.playSE(5);
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.playSE(5);
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				gp.playSE(5);
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
				}
				if(gp.ui.commandNum == 1) {
					gp.gameState = gp.soundOption;
					gp.ui.commandNum = 0;
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_Q) {
				transform = true;
			}
			if (code == KeyEvent.VK_F) {
				Fpressed = true;
			}
			if (code == KeyEvent.VK_T) {
				Tpressed = true;
			}
			if (code == KeyEvent.VK_J) {
				shotPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				gp.playSE(5);
				if(gp.gameState == gp.playState) {
					gp.gameState = gp.pauseState;
					
//				}else if(gp.gameState == gp.pauseState) {
//					gp.gameState = gp.playState;
				}
			}
			if (gp.gameState == gp.dialogueState) {
				gp.playSE(5);
				if (code == KeyEvent.VK_F) {
					gp.gameState = gp.playState;
				}
			}
		}
		if(gp.gameState == gp.gameOver || gp.gameState == gp.gameDone) {
			
			if(code == KeyEvent.VK_D) {
				gp.playSE(5);
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_A) {
				gp.playSE(5);
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.restart();
				}
				if(gp.ui.commandNum == 1) {
					gp.ui.commandNum = 0;
					gp.gameState = gp.titleState;
					gp.restart();
				}
			}
		}
		
		if(gp.gameState == gp.soundOption) {
			
			if(gp.ui.commandNum == 0) {
				if(code == KeyEvent.VK_D) {
					if(gp.music.volumeScale >= 0 && gp.music.volumeScale <4)
						gp.music.volumeScale++;
						gp.music.checkVolume();
						gp.playSE(5);
				}
				if(code == KeyEvent.VK_A) {
					if(gp.music.volumeScale > 0 && gp.music.volumeScale <= 4)
						gp.music.volumeScale--;
						gp.music.checkVolume();
						gp.playSE(5);
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.playSE(5);
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 1) {
						gp.ui.commandNum = 0;
					}
				}
				if(code == KeyEvent.VK_W) {
					gp.playSE(5);
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 1;
					}
				}
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 1) {
	
						gp.gameState = gp.pauseState;				
					}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_Q) {
			transform = false;
		}
		if (code == KeyEvent.VK_F) {
			Fpressed = false;
		}
		if (code == KeyEvent.VK_T) {
			Tpressed = false;
		}
		if (code == KeyEvent.VK_J) {
			shotPressed = false;
		}
	}

}
