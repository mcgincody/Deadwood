import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import java.awt.Point;

import org.w3c.dom.*;

public class CardGenerator
{
	public static final String FILE_LOCATION = "src/cards.xml";

	public static List<Card> loadCards() 
	{
		try
		{
			// setup xml parsing stuff
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_LOCATION);
			Document doc = db.parse(is);

			Node cards_node = doc.getFirstChild();
			NodeList cards_list = cards_node.getChildNodes();
			List<Card> cards = new ArrayList<Card>();

			for (int i = 0; i < cards_list.getLength(); i++) {
				Node card_node = cards_list.item(i);
				Card c = nodeToCard(card_node);

				if (c != null) 
				{
					c.setHidden(true);
					cards.add(c);
				}

			}
			return cards;
		} catch (Exception e)
		{
			
			e.printStackTrace();
			return null;
		}
	}

	public static Card nodeToCard(Node node)
	{
		String nodeName = node.getNodeName();
		if (node.hasAttributes() && nodeName.equals("card"))
		{
			NamedNodeMap m = node.getAttributes();
			String name = getName(m);
			String img = m.getNamedItem("img").getNodeValue();
			int budget = getInt(m, "budget");

			NodeList children = node.getChildNodes();
			List<RoleSpace> rolespaces = new ArrayList<RoleSpace>();
			for (int i = 0; i < children.getLength(); i++)
			{
				Node n = children.item(i);		  
				RoleSpace rs = nodeToRoleSpace(n);
				if (rs != null) {
					rolespaces.add(rs);
				}
			}
			Card c = new Card(name, budget, rolespaces);
			c.setImg(img);
			return c;
		}
		return null;

	}

	public static RoleSpace nodeToRoleSpace(Node node)
	{
		String nodeName = node.getNodeName();
		if (node.hasAttributes() && nodeName.equals("part"))
		{
			NamedNodeMap m = node.getAttributes();
			String name = getName(m);
			int level = getInt(m, "level");

			RoleSpace rs = new RoleSpace(level, name);
			Point p = BoardGenerator.getXY(node);
			if (p != null) {
				rs.setX(p.x);
				rs.setY(p.y);
			} else {
			}
			return rs;
		}
		return null;
	}

	private static int getInt(NamedNodeMap m, String name)
	{
		int item = 0;
		Node item_node = m.getNamedItem(name);
		if (item_node != null)
		{
			String s = item_node.getNodeValue();
			item = Integer.parseInt(s);
		}
		return item;
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

}
