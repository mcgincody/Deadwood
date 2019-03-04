import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class PlayerPanel extends JPanel
{
	JLabel image;
	JLabel name;
	JLabel money;
	JLabel fame;
	
	public PlayerPanel()
	{
		
	}
	
	public void assignPlayer(String name, String imageLocation)
	{
		Image initImage = null;
		try
		{
			initImage = ImageIO.read(new File(imageLocation));
		} catch (IOException e)
		{
			image=null;
		}
		ImageIcon rankImage = new ImageIcon(getScaledImage(initImage, 81, 81));
		image.setIcon(rankImage);
		this.name.setText(name);
	}
	
	public void initialize()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		this.add(panel_3);
		
		JLabel lblPlayer = new JLabel("Player 1");
		name = lblPlayer;
		lblPlayer.setForeground(Color.BLACK);
		lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(lblPlayer);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		this.add(panel_2);
		
		JLabel playerRankImage = new JLabel();
		this.image = playerRankImage;
		panel_2.add(playerRankImage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		this.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("$0");
		this.money = label;
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(label);
		
		JPanel blankSpace = new JPanel();
		blankSpace.setOpaque(false);
		panel_1.add(blankSpace);
		
		JLabel lblFame = new JLabel("0 Fame");
		this.fame = lblFame;
		lblFame.setForeground(Color.BLACK);
		lblFame.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(lblFame);
	}
	
	public void setMoney (int money)
	{
		this.money.setText("$" + String.valueOf(money));
	}
	
	public void setFame (int fame)
	{
		this.fame.setText(String.valueOf(fame) + " Fame");
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public void updateIcon(String imageLocation)
	{
		Image initImage = null;
		try
		{
			initImage = ImageIO.read(new File(imageLocation));
		} catch (IOException e)
		{
			image=null;
		}
		ImageIcon rankImage = new ImageIcon(getScaledImage(initImage, 81, 81));
		image.setIcon(rankImage);
	}
}
