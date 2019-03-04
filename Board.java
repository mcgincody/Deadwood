import java.util.List;
import java.util.Random;
public class Board
{
	private List<Room> Rooms;
	private int sceneCount;
	//Added this so the board won't have to be searched for the trailers every time.
	private Room trailers;
	private List<Card> cards;

	public Board ()
	{
		initializeRooms();
		this.cards = CardGenerator.loadCards();
		setTrailer();
		setNewCards();
	}
	
	public List<Card> getCards()
	{
		return this.cards;
	}

	public List<Room> getRooms(){
		return Rooms;
	}
	
	public Room getTrailer()
	{
		return this.trailers;
	}
	
	public void decrementSceneCount()
	{
		this.sceneCount--;
		
	}
	
	public int getSceneCount ()
	{
		return this.sceneCount;
	}
	
	public void setNewCards()
	{
		Random random = new Random();
		this.sceneCount = 0;
		for (Room room : this.Rooms)
		{
			if(!room.getRoomName().equals("Trailers") && !room.getRoomName().equals("Casting Office"))
			{
				room.setCard(this.cards.remove(random.nextInt(this.cards.size())));
				this.sceneCount++;
			}
			else
			{
				room.setCard(null);
			}
		}
	}
	
	public void setTrailer()
	{
		for (Room room : this.Rooms)
		{
			if(room.getRoomName().equals("Trailers"))
			{
				this.trailers = room;
				return;
			}
		}
	}
	
	public void initializeRooms()
	{
		this.Rooms = BoardGenerator.loadRooms();
		for (Room room : this.Rooms)
		{
			if (room.getRoomName().equals("Trailers"))
			{
				this.trailers = room;
				break;
			}
		}
	}
}
