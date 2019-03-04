import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BoardModerator {
	private Board Board;

	public BoardModerator() {
		Board = new Board();
	}

	public void revealCard(Room room) {
		if (room.getCard() != null && room.getCard().getHidden())
		{
			room.getCard().setHidden(false);
			Main.boardPanel.revealCard(room);
		}
	}

	public void removeCard(Room room) 
	{	
		Main.boardPanel.removeCard(room);
		room.setCard(null);
		Board.decrementSceneCount();
	}
	public void endDay() 
	{
		Main.getCurrentGame().incrementDay();
		if(!Main.getCurrentGame().noMoreDays())
		{
			Board.initializeRooms();
			Board.setNewCards();
			Main.boardPanel.init();
			Main.boardPanel.revalidate();
			Main.boardPanel.repaint();

			placeAllPlayersInTrailers();
			int day = Main.getCurrentGame().getCurrentDay();
			JOptionPane.showMessageDialog(Main.getMainPanel(),
					"Day " + (day - 1) + " has ended. Now starting day " + day + ".");
			Main.getMainPanel().setTitle("Deadwood Studios: Day " + day);
		}

		
	}

	public  Board getBoard(){
		return Board;
	}

	public  void setBoard(Board board){
		Board = board;
	}
	
	public  void placeAllPlayersInTrailers()
	{
		for (Player player : Main.getCurrentGame().getPlayers())
		{
			Main.getCurrentGame().getTurnModerator().movePlayer(player, Board.getTrailer());
			Main.boardPanel.setPlayer(player, Board.getTrailer());
		}
		clearPlayerRoles(Board.getTrailer());
		
	}
	public  void deleteCard(Room room)
	{
		room.setCard(null);
	}
	
	public  void clearPlayerRoles(Room room)
	{
		for (Player player : room.getOccupyingPlayers())
		{
			player.setRole(null);
			Main.boardPanel.setPlayer(player, room);
		}				
	}
	public boolean roomHasAvailableRoles(int rank, Room currentRoom)
	{
		if (!getAvailableRoles(rank, currentRoom).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<RoleSpace> getAvailableRoles(int rank, Room room)
	{
		List<RoleSpace> extraRoles = room.getSpaces();
		List<RoleSpace> starringRoles = room.getCard().getSpaces();
		List<RoleSpace> availableRoles = new ArrayList<RoleSpace>();
		List<String> roleNames = new ArrayList<String>();
		for (RoleSpace roleSpace : extraRoles)
		{
			if(!roleSpace.isOccupied() && roleSpace.getRank() <= rank)
			{
				roleNames.add(roleSpace.getRoleName().toUpperCase());
				availableRoles.add(roleSpace);
			}
		}
		
		for (RoleSpace roleSpace : starringRoles)
		{
			if(!roleSpace.isOccupied() && roleSpace.getRank() <= rank)
			{
				roleNames.add(roleSpace.getRoleName().toUpperCase());
				availableRoles.add(roleSpace);
			}
		}
		return availableRoles;
	}		
}