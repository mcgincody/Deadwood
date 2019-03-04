import java.util.*;

public class Quadrant
{

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	public static final int NORTH_EAST = 0;
	public static final int NORTH_WEST = 1;
	public static final int SOUTH_EAST = 2;
	public static final int SOUTH_WEST = 3;

	private Room[] rooms = new Room[4];
	private Quadrant[] neighbors = new Quadrant[4];

	private int locationOnBoard = -1;


	public Quadrant()
	{
		this(null, null, null, null);
	}

	public Quadrant(Room north_east, Room north_west, Room south_east, Room south_west)
	{
		setRoom(NORTH_EAST, north_east);
		setRoom(NORTH_WEST, north_west);
		setRoom(SOUTH_EAST, south_east);
		setRoom(SOUTH_WEST, south_west);
	}

	public void setRoom(int direction, Room r)
	{
		rooms[direction] = r;
	}

	public void setNeighbor(int direction, Quadrant q)
	{
		neighbors[direction] = q;
	}

	public Room getRoom(int direction)
	{
		return rooms[direction];
	}

	public Collection<Room> getAllRooms()
	{
		Set<Room> set = new HashSet<Room>();
		for (int i = 0; i < 4; i++)
		{
			set.add(rooms[i]);
		}
		return set;
	}

	public List<Integer> getLocations(Room r)
	{
		if (getAllRooms().contains(r))
		{
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 4; i++)
			{
				if (rooms[i] == r)
				{
					list.add(i);
				}
			}
			return list;
		}
		return null;
	}

	public List<Room> getRoomNeighbors(Room r)
	{
		if (getAllRooms().contains(r))
		{
			List<Room> roomNeighbors = new ArrayList<Room>();
			roomNeighbors.addAll(getAllRooms());
			roomNeighbors.remove(r);
			for (int i : getLocations(r))
			{
				roomNeighbors.addAll(getRoomNeighbors(i));
			}
			return roomNeighbors;
		}
		return null;
	}

	private List<Room> getRoomNeighbors(int i)
	{
		// given a location, return only the neighbors
		// from other quadrants
		List<Room> list = new ArrayList<Room>();
		switch (i)
		{
			case NORTH_EAST:
				if (neighbors[NORTH] != null)
				{
					list.add(neighbors[NORTH].rooms[SOUTH_EAST]);
				}
				if (neighbors[EAST] != null)
				{
					list.add(neighbors[EAST].rooms[NORTH_WEST]);
				}
				break;
			case NORTH_WEST:
				if (neighbors[NORTH] != null)
				{
					list.add(neighbors[NORTH].rooms[SOUTH_WEST]);
				}
				if (neighbors[WEST] != null)
				{
					list.add(neighbors[WEST].rooms[NORTH_EAST]);
				}
				break;
			case SOUTH_EAST:
				if (neighbors[SOUTH] != null)
				{
					list.add(neighbors[SOUTH].rooms[NORTH_EAST]);
				}
				if (neighbors[EAST] != null)
				{
					list.add(neighbors[EAST].rooms[SOUTH_WEST]);
				}
				break;
			case SOUTH_WEST:
				if (neighbors[SOUTH] != null)
				{
					list.add(neighbors[SOUTH].rooms[NORTH_WEST]);
				}
				if (neighbors[WEST] != null)
				{
					list.add(neighbors[WEST].rooms[SOUTH_EAST]);
				}
				break;

		}
		return list;
	}

	public Quadrant getNeighbor(int direction)
	{
		return neighbors[direction];
	}

	public void setLocationOnBoard(int location)
	{
		this.locationOnBoard = location;
	}

	public int getLocationOnBoard()
	{
		return this.locationOnBoard;
	}
}
