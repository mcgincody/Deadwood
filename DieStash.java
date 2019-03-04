import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Class for displaying a bunch of dice in a room
 */
public class DieStash extends JPanel {
	private List<Die> dice;
	private boolean highlight;

	public DieStash() {
		super(null);
		this.setOpaque(false);
		dice = new ArrayList<Die>();
		setSize(CardPanel.BASE_WIDTH, Die.DIE_SIZE);
	}

	public void addDie(Die d) {
		dice.add(d);
		this.add(d);
		setLocations();
	}
	
	public void trimDice() {
		List<Die> toRemove = new ArrayList<Die>();
		for (Die d : dice) {
			if (d.getParent() != this) {
				toRemove.add(d);
			}
		}
		for (Die d : toRemove) {
			dice.remove(d);
		}
	}

	public void removeDie(Die d) {
		dice.remove(d);
		this.remove(d);
		setLocations();
	}

	public void setLocations() {
		trimDice();
		int width = getSize().width / (dice.size() + 1);
		for (int i = 0; i < dice.size(); i++) {
			Point p = new Point(i * width, 0);
			Die d = dice.get(i);
			d.setLocation(p);
		}
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		setLocations();
	}
	public void setWidth(int width) {
		setSize(width, getHeight());
	}

	public void setHighlight(boolean b) {
		this.highlight = b;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (highlight) {
			g.setColor(new Color(0x88ff0000, true));
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
