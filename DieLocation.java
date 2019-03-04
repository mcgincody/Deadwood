import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.swing.JPanel;
public class DieLocation extends JPanel {
	private Die d;
	private boolean highlight;
	
	

	public DieLocation(RoleSpace rs, Room r) {

		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(46, 46);
		this.setLocation(rs.getX(), rs.getY());
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Player p = new Player(0, "michael", 0, 0, 1, null);
				setDie(new Die(p, "b"));
				revalidate();
				repaint();
			}

			public void mouseReleased(MouseEvent e) {

			}
		});
	}

	public void setDie(Die d) {
		this.d = d;
		this.add(d);
		// this is weird but there should be a way to put the die on a new location
		// without having to look through all locations to find the old one.
		// likely this class will have to be changed to RolePanel, and then 
		// this and DieStash will implement an interface that will allow the die
		// to remove itself when moved
		d.setLocation(new Point());
		d.setDieLocation(this);
	}

	public Die getDie() {
		return this.d;
	}

	public void removeDie() {
		this.remove(d);
		this.d = null;
	}

	public void setHighlight(boolean b) {
		this.highlight = b;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (highlight) {
			g.setColor(new Color(0x88ff0000, true));
			g.fillRect(0, 0, Die.DIE_SIZE, Die.DIE_SIZE);
		}
	}
}
