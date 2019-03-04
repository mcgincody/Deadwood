import java.util.*;
import java.io.*;
import java.net.URL;

import javax.xml.parsers.*;
import java.awt.Point;

import org.w3c.dom.*;

public class BoardGenerator
{
	private static final boolean DEBUG = false;
	public static final String FILE_LOCATION = "board.xml";

	private BoardGenerator()
	{
	}

	// Does this need a parameter? Since we will only be using one file, it can just pull it's own constant right?
	public static List<Room> loadRooms()
	{
		try
		{
			List<Room> room_list = new ArrayList<Room>();

			// setup xml parsing stuff
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_LOCATION);
			Document doc = db.parse(is);

			Node board = doc.getFirstChild();

			// parse quadrant tags
			NodeList quadrants = board.getChildNodes();
			List<Quadrant> quadrants_list = new ArrayList<Quadrant>();
			for (int i = 0; i < quadrants.getLength(); i++)
			{
				Node quadrant_node = quadrants.item(i);
				Quadrant q = nodeToQuadrant(quadrant_node);
				if (q != null)
				{
					List<Integer> l = getLocations(quadrant_node);
					debug("" + l.size());
					q.setLocationOnBoard(l.get(0));
					quadrants_list.add(q);
				}
			}
			setNeighbors(quadrants_list);

			// dump all rooms into a single list
			for (Quadrant q : quadrants_list)
			{
				room_list.addAll(q.getAllRooms());
				for (Room room : q.getAllRooms())
				{
					room.setAdjacentRooms(q.getRoomNeighbors(room));
				}
			}
			
			return room_list;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private static Quadrant nodeToQuadrant(Node quadrant_node)
	{
		if (quadrant_node.hasChildNodes())
		{
			NodeList quadrant = quadrant_node.getChildNodes();
			Quadrant q = new Quadrant();

			for (int j = 0; j < quadrant.getLength(); j++)
			{
				Node room_node = quadrant.item(j);

				if (room_node.getNodeName().equals("room"))
				{
					Room r = nodeToRoom(room_node);
					List<Integer> locations = getLocations(room_node);
					if (r != null)
					{
						r.setQuadrant(q);
						for (int location : locations)
						{
							q.setRoom(location, r);
						}
					}
				}
			}
			return q;
		}
		return null;
	}

	private static List<Integer> getLocations(Node node)
	{
		if (node.hasChildNodes())
		{
			List<Integer> locations = new ArrayList<Integer>();
			debug("getting location for " + node.getNodeName());
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++)
			{
				Node child = children.item(i);

				if (child.getNodeName().equals("location"))
				{
					String value = child.getTextContent();
					switch (value)
					{
						case "NE":
							locations.add(Quadrant.NORTH_EAST);
							break;
						case "NW":
							locations.add(Quadrant.NORTH_WEST);
							break;
						case "SE":
							locations.add(Quadrant.SOUTH_EAST);
							break;
						case "SW":
							locations.add(Quadrant.SOUTH_WEST);
							break;
					}
				}
			}
			return locations;
		}
		return null;
	}

	private static Room nodeToRoom(Node room_node)
	{

		if (room_node.hasAttributes())
		{
			NamedNodeMap m = room_node.getAttributes();

			Room room = new Room(null, null, null, 0, null);
			room.setRoomName(getName(m));
			room.setClapperCount(getClappers(m));

			Point p = getXY(room_node);
			if (p != null) {
				room.setX(p.x);
				room.setY(p.y);
			} else {
			}

			List<RoleSpace> spaces = new ArrayList<RoleSpace>();

			if (room_node.hasChildNodes())
			{
				NodeList roles = room_node.getChildNodes();
				for (int i = 0; i < roles.getLength(); i++)
				{
					Node role_node = roles.item(i);
					if (role_node.getNodeName().equals("part"))
					{
						RoleSpace role = nodeToRole(role_node);
						spaces.add(role);
					}
					if (role_node.getNodeName().equals("takes"))
					{
						room.setClapperLocations(getClapperLoations(role_node));
					}
				}
			}
			room.setSpaces(spaces);
			return room;
		}

		return null;
	}

	private static String getName(NamedNodeMap m)
	{
		String name = "";
		Node name_node = m.getNamedItem("name");
		if (name_node != null)
		{
			name = name_node.getNodeValue();
		}
		return name;
	}

	private static int getClappers(NamedNodeMap m)
	{
		int clappers = 0;
		Node clappers_node = m.getNamedItem("clappers");
		if (clappers_node != null)
		{
			String clappers_string = clappers_node.getNodeValue();
			clappers = Integer.parseInt(clappers_string);
		}
		return clappers;
	}

	private static List<Point> getClapperLoations(Node takes_node)
	{
		List<Point> points = new ArrayList<Point>();
		if (takes_node.hasChildNodes()) 
		{
			NodeList children = takes_node.getChildNodes();
			for (int i = 0 ; i < children.getLength(); i++)
			{
				Node child = children.item(i);

				if (child.getNodeName().equals("take"))
				{
					points.add(getXY(child));

				}
			}
		}

		return points;
	}


	public static Point getXY(Node role_node) 
	{
		if (role_node.hasChildNodes()) 
		{
			NodeList children = role_node.getChildNodes();
			for (int i = 0 ; i < children.getLength(); i++)
			{
				Node child = children.item(i);

				if (child.getNodeName().equals("area"))
				{
					NamedNodeMap m = child.getAttributes();
					String xs = m.getNamedItem("x").getNodeValue();
					String ys = m.getNamedItem("y").getNodeValue();

					int x = Integer.parseInt(xs);
					int y = Integer.parseInt(ys);

					return new Point(x,y);
				}
			}
		}
		return null;

	}

	private static RoleSpace nodeToRole(Node role_node)
	{
		if (role_node.hasAttributes())
		{
			NamedNodeMap m = role_node.getAttributes();
			Node name_node = m.getNamedItem("name");
			Node level_node = m.getNamedItem("level");

			String name = name_node.getNodeValue();
			String level_string = level_node.getNodeValue();
			int level = Integer.parseInt(level_string);

			RoleSpace rs = new RoleSpace(level, name);
			Point p = getXY(role_node);
			if (p != null) {
				rs.setX(p.x);
				rs.setY(p.y);
			} else {
			}
			// Role r = new Role(RoleType.Extra, rs);

			return rs;
		} else
		{
			return null;
		}
	}

	private static void setNeighbors(List<Quadrant> quadrants)
	{
		int NW = Quadrant.NORTH_WEST;
		int NE = Quadrant.NORTH_EAST;
		int SW = Quadrant.SOUTH_WEST;
		int SE = Quadrant.SOUTH_EAST;

		int N = Quadrant.NORTH;
		int E = Quadrant.EAST;
		int S = Quadrant.SOUTH;
		int W = Quadrant.WEST;

		Quadrant[] q_array = new Quadrant[4];
		for (Quadrant q : quadrants)
		{
			int index = q.getLocationOnBoard();
			q_array[index] = q;
		}

		q_array[NW].setNeighbor(E, q_array[NE]);
		q_array[NW].setNeighbor(S, q_array[SW]);

		q_array[NE].setNeighbor(W, q_array[NW]);
		q_array[NE].setNeighbor(S, q_array[SE]);

		q_array[SW].setNeighbor(N, q_array[NW]);
		q_array[SW].setNeighbor(E, q_array[SE]);

		q_array[SE].setNeighbor(N, q_array[NE]);
		q_array[SE].setNeighbor(W, q_array[SW]);
	}

	private static void debug(String s)
	{
		if (DEBUG)
		{
			
		}
	}
}

