import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TurnModerator {

	private static Player activePlayer;
	private int[] moneyUpgradeCosts = new int[]{4,10,18,28,40};
	private int[] fameUpgradeCosts = new int[]{5,10,15,20,25};

	public  Player getActivePlayer()
	{
		return activePlayer;
	}
	
	public  void setActivePlayer(Player nextActivePlayer)
	{
		activePlayer = nextActivePlayer;
		provideTurnChoices();
	}


	public  List<TurnAction> provideTurnChoices() 
	{
		List<TurnAction> turnActions = new ArrayList<TurnAction>();
		Player currentPlayer = getActivePlayer();
		if (currentPlayer.getCurrentRoom().getRoomName().equals("Casting Office") && playerCanUpgrade())
		{
			turnActions.add(new Upgrade());
		}
		if (currentPlayer.getCurrentRoom().getCard() != null
				&& Main.getCurrentGame().getBoardModerator().roomHasAvailableRoles(activePlayer.getRank(), activePlayer.getCurrentRoom())
				&& activePlayer.getRole() == null)
		{
			turnActions.add(new TakeRole());
		}
		if (currentPlayer.getRole() != null)
		{
			turnActions.add(new Act());
			if(activePlayer.getRehearsalTokens() + 1 < activePlayer.getCurrentRoom().getCard().getBudget())
			{
				turnActions.add(new Rehearse());
			}
		}
		else
		{
			turnActions.add(new Move());
		}
		turnActions.add(new EndTurn());
		return turnActions;
		
	}

	public  List<String> showValidMoves(Player player) {
		StringBuilder print = new StringBuilder();
		print.append("You can move to ");
		Room currentRoom = player.getCurrentRoom();
		List<String> choiceStrings = new ArrayList<String>();
		int i = currentRoom.getAdjacentRooms().size();
		for (Room room : currentRoom.getAdjacentRooms())
		{
			choiceStrings.add(room.getRoomName().toUpperCase());
			if (i > 2)
			{
				print.append(choiceStrings.get(choiceStrings.size()-1) + ", ");
			}
			else if (i == 2)
			{
				print.append(choiceStrings.get(choiceStrings.size()-1) + " or ");
			}
			else
			{
				print.append(choiceStrings.get(choiceStrings.size()-1) + ". Where do you want to move? ");
			}
			i--;
		}
		return choiceStrings;
	}
	public  void increasePlayerRank(Player player, int cost, int rank, String currency) {
		switch(currency){
		case "MONEY":
			player.addFunds(new int[] {0 - cost, 0});
			player.setRank(rank);
			break;
		case "FAME":
			player.addFunds(new int[]{0, 0 - cost});
			player.setRank(rank);
			break;
		}
		player.setDiceImageLocation();
		Main.getPlayerPanels()[player.getTurnOrder()-1].updateIcon(player.getImageLocation());
		Main.boardPanel.updatePlayerDie(player);
		/*
		for (PlayerPanel panel : JoshBoard.getPlayerPanels())
		{
			if(panel != null)
			{
				if (panel.name.getText().equals(player.getName()))
				{
					panel
				}
			}
		}*/
	}
	public  void determineWrapReward(int[] rolledDice) {
		//Get player rolls
		boolean playersOnCard = false;
		
		Arrays.sort(rolledDice);
		List<RoleSpace> spacesForRewards = activePlayer.getCurrentRoom().getCard().getSpaces();
		Collections.sort(spacesForRewards, new SortByRank());
		WrapDialog wrapDialog = new WrapDialog(spacesForRewards, rolledDice);
		wrapDialog.setModal(true);
		wrapDialog.setVisible(true);
		int spaces = spacesForRewards.size();
		int[] rewardDistribution = new int[spaces];
		
		for (int i=0; i< rolledDice.length; i++)
		{
			rewardDistribution[i % spaces] += rolledDice[(rolledDice.length - 1)-i];
		}
		for (int i = 0; i < rewardDistribution.length; i++)
		{
			
			RoleSpace space = activePlayer.getCurrentRoom().getCard().getSpaces().get(i);
			if(space.isOccupied())
			{
				playersOnCard = true;
				space.getOccupyingPlayer().addFunds(new int[] {rewardDistribution[rewardDistribution.length - 1 - i], 0});;
				
			}
		}
		if(playersOnCard)
		{
		Room room = activePlayer.getCurrentRoom();
			for (RoleSpace space : room.getSpaces()) 
			{
				if (space.isOccupied()) 
				{
					space.getOccupyingPlayer().addFunds(new int[] { space.getRank(), 0 });
					
				}
			}
		}
		else
		{
			
		}
		//increase starring players' money according to array (could also be done while building array for efficiency's sake)
		//Award windfall bonuses to extras (based on the ROLE's rank, not the budget or player rank. We missed that when we played the first time.
		//  e.g. a rank 2 role awards $2)
		//Call InputAndOutput.printWrapRewards(REWARDARRAY, CURRENTROOM)
	}
	public  void nextPlayer() {
	}
	public void removeRehearsalTokens(Room room) {
	}
	public  void movePlayer(Player player, Room room)
	{
		player.getCurrentRoom().removePlayer(player);
        player.setCurrentRoom(room);
        room.addPlayer(player);
		Main.boardPanel.setPlayer(player, room);
	}
	
	public  void removeAllRehearsalTokens(List<Player> players)
	{
		for (Player player : players)
		{
			player.removeRehearsalTokens();
		}
	}
	
	public  boolean playerCanUpgrade()
	{
		int rank = activePlayer.getRank();
		int money = activePlayer.getMoney();
		int fame = activePlayer.getFame();
		for (int i = 0; i < moneyUpgradeCosts.length; i++)
		{
			if (rank < i+2 && moneyUpgradeCosts[i] <= money || rank < i+2 && fameUpgradeCosts[i] <= fame)
			{
				return true;
			}
		}
		
		return false;
		
	}
}
//Need this to order role spaces
class SortByRank implements Comparator<RoleSpace>
{
	@Override
	public int compare(RoleSpace a, RoleSpace b)
	{
		// TODO Auto-generated method stub
		 return a.getRank() - b.getRank();
	}}