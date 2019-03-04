import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPanel extends ImagePanel {
	public static final int BASE_WIDTH = 205;
	public static final int BASE_HEIGHT = 115;

	private Room room;
	private Card card;
	private List<DieLocation> dieLocations;
	private Map<RoleSpace, DieLocation> diemap;

	public CardPanel(Card c, Room r) {
		super("cards/" + c.getImg());
		dieLocations = new ArrayList<DieLocation>();
		diemap = new HashMap<RoleSpace, DieLocation>();
		this.card = c;
		this.room = r;
		this.setLayout(null);
		this.setSize(BASE_WIDTH, BASE_HEIGHT);
		this.setLocation(r.getX(), r.getY());

		for (RoleSpace rs : c.getSpaces()) {
			DieLocation dl = new DieLocation(rs, r);
			dieLocations.add(dl);
			diemap.put(rs, dl);
		}
		for (DieLocation d : dieLocations) {
			add(d);
		}
	}

	public Room getRoom() {
		return this.room;
	}

	public Map<RoleSpace, DieLocation> getDieMap() {
		return diemap;
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (card.getHidden()) {
			g.setColor(new Color(0xCFE2F5));
			g.fillRect(0, 0, getSize().width, getSize().height);
		}
	}
}
