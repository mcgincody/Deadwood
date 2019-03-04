import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardPanel extends ImagePanel {
	private Board b;
	private List<DieLocation> dieLocations;
	private Map<RoleSpace, DieLocation> dieLocationMap;
	private Map<Card, CardPanel> cardmap;
	private Map<Room, DieStash> roomStashes;
	private Map<Player, Die> dice;
	private Map<Room, List<ImagePanel>> clappers;

	public BoardPanel(Board b) {
		this.b = b;
		setImage("src/board.jpg");

		init();
	}
	
	public void updatePlayerDie(Player player)
	{
		Die d = dice.get(player);
		//dice.get(player).setImage();

		d.redraw();
		
	}

	public void init() {
		this.removeAll();
		dieLocations = new ArrayList<DieLocation>();
		dieLocationMap = new HashMap<RoleSpace, DieLocation>();
		roomStashes = new HashMap<Room,DieStash>();
		clappers = new HashMap<Room, List<ImagePanel>>();
		cardmap = new HashMap<Card,CardPanel>();
		dice = new HashMap<Player,Die>();

		for (Room r : b.getRooms()) {
			// init dielocations
			for (RoleSpace rs : r.getSpaces()) {
				DieLocation d = new DieLocation(rs, r);
				dieLocationMap.put(rs, d);
				dieLocations.add(d);
				add(d);
			}
			// init cards
			if (r.getCard() != null) {
				CardPanel c = new CardPanel(r.getCard(), r);
				// c.setVisible(false);
				cardmap.put(r.getCard(), c);
				dieLocationMap.putAll(c.getDieMap());
				add(c);
			}

			// init diestashes
			initDieStash(r);
			initClappers(r);
		}
	}

	public void addPlayer(Player p) {
		Die d = new Die(p, p.getColorCharFromColorNumber(p.getColor()));
		dice.put(p, d);
	}

	public void initDieStash(Room r) {
		DieStash ds = new DieStash();
		ds.setLocation(r.getStashLocation());
		ds.setWidth(r.getStashWidth());
		// ds.setHighlight(true);
		roomStashes.put(r, ds);
		add(ds);
	}

	public void initClappers(Room r) {
		List<ImagePanel> list = new ArrayList<ImagePanel>();
		for (int i = 0; i < r.clapperLocations.size(); i++) {
			ImagePanel ip = new ImagePanel("shot.png");
			ip.setSize(42, 42);
			ip.setOpaque(false);
			ip.setLocation(r.clapperLocations.get(i));
			list.add(ip);
			add(ip);
		}
		clappers.put(r, list);
	}

	public void removeClapper(Room r) {
		List<ImagePanel> list = clappers.get(r);
		ImagePanel ip = list.get(list.size() - 1);
		remove(ip);
		list.remove(ip);
		repaint();
	}

	public void removeCard(Room r) {
		remove(cardmap.get(r.getCard()));
		repaint();
	}

	public void removeCard(Card c) {
		remove(cardmap.get(c));
		repaint();
	}

	public void revealCard(Room r) {
		revealCard(r.getCard());
	}

	public void revealCard(Card c) {
		cardmap.get(c).setVisible(true);
		revalidate();
		repaint();
	}

	public void highlightStash(Room r, boolean h) {
		roomStashes.get(r).setHighlight(h);	
	}

	public void highlightRoom(Room r, int level, boolean h) {
		// unfinished
		highlightStash(r, h);
	}

	public void setPlayer(Player p, Room r) {
		if (!dice.containsKey(p)) {
			addPlayer(p);
		}
		roomStashes.get(r).addDie(dice.get(p));
		repaint();
	}

	public void setPlayer(Player p, RoleSpace rs) {
		DieLocation  dl = dieLocationMap.get(rs); 
		Die die = dice.get(p);
		dl.setDie(die);
		this.repaint();
		this.revalidate();
	}
	

}