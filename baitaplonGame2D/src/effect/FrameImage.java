package effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import mainGame.GamePanel;

public class FrameImage {

	private String name;
	 BufferedImage image;
	
	
	public FrameImage(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	public FrameImage(FrameImage frameImage) {
		image = new BufferedImage(frameImage.getImageWith(),
								  frameImage.getImageHeight(),
								  frameImage.image.getType());
		Graphics g = image.getGraphics();
		g.drawImage(frameImage.image,0,0, null);
		name = frameImage.name;
	}
	
	public void draw(Graphics2D g2, int x, int y, int size1, int size2) {
		g2.drawImage(image, x-image.getWidth()/2, y-image.getHeight()/2,size1 ,size2, null);
	}
	
	public FrameImage(){
        this.name = null;
        image = null;
    }
	
	public int getImageWith() {
		return image.getWidth();
	}
	
	public int getImageHeight() {
		return image.getHeight();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	 public void flipImage(){
	          
	            BufferedImage image = getImage();
	            
	            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	            tx.translate(-image.getWidth(), 0);

	            AffineTransformOp op = new AffineTransformOp(tx,
	            AffineTransformOp.TYPE_BILINEAR);
	            image = op.filter(image, null);
	            
	            setImage(image);
	            
	      
	}
}
