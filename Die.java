public class Die extends ImagePanel {
	public static final int DIE_SIZE = 46;
	private DieLocation location;
	private Player player;
	private String color;

	public Die(Player p, String color) {
		super("");
		player = p;
		this.color = color;
		this.setOpaque(false);
		setSize(DIE_SIZE, DIE_SIZE);
		setImage();
	}

	public void setImage() {
		String file = "dice/" + color + player.getRank() + ".png";
		setImage(file, DIE_SIZE);
	}
	
	

	// Call this when player gets an upgrade
	public void redraw() {
		setImage();
		revalidate();
		repaint();
	}

	public void setDieLocation(DieLocation dl) {
		if (this.location != null && this.location.getDie() == this) {
			this.location.removeDie();
		}
		this.location = dl;
	}
}
