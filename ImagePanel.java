
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
		super(null);
	}
    public ImagePanel(String path) {
		super(null);
		setImage(path);
    }

	// must call this method before drawing
	public void setImage(String path) {
       try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            // handle exception...
       }
	}
	
	public void setImage(String path, int size) {
	       try {                
	          image = (BufferedImage) getScaledImage(ImageIO.read(new File(path)), size, size);
	       } catch (IOException ex) {
	            // handle exception...
	       }
		}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
	}
}
