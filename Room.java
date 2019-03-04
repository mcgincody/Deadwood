import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Room
{
	//Attributes
	String RoomName;
	List<RoleSpace> Spaces;
	List<Point> clapperLocations;
	private Card Card;
	int clapperCount;
	List<Player> OccupyingPlayers;
	List<Room> AdjacentRooms;
	private Quadrant quadrant;
	private int x;
	private int y;

	//Constructors
	public Room(String roomName, List<RoleSpace> roleSpaces, Card card, int clapperCount, List<Room> adjacentRooms){
		this.RoomName = roomName;
		this.Spaces = roleSpaces;
		this.Card = card;
		this.clapperCount = clapperCount;
		this.AdjacentRooms = adjacentRooms;
		this.OccupyingPlayers = new ArrayList<Player>();
		this.clapperLocations = new ArrayList<Point>(); 
	}

	//Public Interface Methods
	public void setRoleOccupied(){

	}
	
	public void addPlayer(Player player)
	{
		this.OccupyingPlayers.add(player);
	}
	
	public void removePlayer (Player player)
	{
		int i=0;
		for (Player tempPlayer : this.OccupyingPlayers)
		{
			if(tempPlayer.getName().equals(player.getName()))
			{
				OccupyingPlayers.remove(i);
				return;
			}
			else
			{
				i++;
			}
		}
	}

	public String getRoomName()
	{
		return RoomName;
	}

	public void setRoomName(String roomName)
	{
		RoomName = roomName;
	}

	public List<RoleSpace> getSpaces()
	{
		return Spaces;
	}

	public void setSpaces(List<RoleSpace> spaces)
	{
		Spaces = spaces;
	}

	public Card getCard()
	{
		return Card;
	}

	public void setCard(Card card)
	{
		Card = card;
	}

	public int getClapperCount()
	{
		return clapperCount;
	}

	public void setClapperCount(int clapperCount)
	{
		this.clapperCount = clapperCount;
	}

	public List<Player> getOccupyingPlayers()
	{
		return OccupyingPlayers;
	}

	public void setOccupyingPlayers(List<Player> occupyingPlayers)
	{
		OccupyingPlayers = occupyingPlayers;
	}

	public List<Room> getAdjacentRooms()
	{
		return AdjacentRooms;
	}

	public void setAdjacentRooms(List<Room> adjacentRooms)
	{
		this.AdjacentRooms = adjacentRooms;
	}

	public List<RoleSpace> OccupiedSpaces(){
		return null;
	}

	public void decreaseClapperCount()
	{
		this.clapperCount--;
	}

	public Room findAdjacentRoomByName(String name)
	{
		for (Room room : this.AdjacentRooms)
		{
			if(room.getRoomName().toUpperCase().equals(name))
			{
				return room;
			}
		}
		return null;
	}

	public void setQuadrant(Quadrant q) {
		this.quadrant = q;
	}

	public Quadrant getQuadrant() {
		return this.quadrant;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
	public void setClapperLocations(List<Point> p) {
		clapperLocations = p;
	}
	public void addClapperLocation(Point p) {
		clapperLocations.add(p);
	}

	public Point getStashLocation() {
		if (RoomName.equals("Trailers")) {
			return new Point(999, 268);
		} else if (RoomName.equals("Casting Office")) {
			return new Point(17, 475);
		} else {
			return new Point(x,y + CardPanel.BASE_HEIGHT);
		}
	}
	
	public List<RoleSpace> getAvailableExtraRoles(Player player)
	{
		List<RoleSpace> spaces = new ArrayList<RoleSpace>();
		for (RoleSpace space : this.getSpaces())
		{
			if(!space.isOccupied() && !(player.getRank() < space.getRank()))
			{
				spaces.add(space);
			}
		}
		return spaces;
	}
	
	public List<RoleSpace> getAvailableStarRoles(Player player)
	{
		List<RoleSpace> spaces = new ArrayList<RoleSpace>();
		if (this.Card != null) {
			for (RoleSpace space : this.Card.getSpaces())
			{
				if(!space.isOccupied() && !(player.getRank() < space.getRank()))
				{
					spaces.add(space);
				}
			}
		}
		return spaces;
	}

	public int getStashWidth() {
		int width = CardPanel.BASE_WIDTH;
		if (RoomName.equals("Ranch")) {
			return width - 50;
		}
		return width;
	}
}
