import java.util.*;

public class Move implements TurnAction
{

	@Override
	public void execute(Player player)
	{
		List<Room> adjacentRooms = player.getCurrentRoom().getAdjacentRooms();
		MoveDialog moveDialog = new MoveDialog(adjacentRooms);
		moveDialog.setModal(true);
		moveDialog.setVisible(true);;
		Room choice = moveDialog.getChoice();
		moveDialog.dispose();
		Main.getCurrentGame().getTurnModerator().movePlayer(player, choice);
		Main.getCurrentGame().getBoardModerator().revealCard(choice);
		return;
		/*
		if (!choice.equals("TRAILERS") && !choice.equals("CASTING OFFICE"))
		{
			
			if (player.getCurrentRoom().getCard() != null)
			{
				if (BoardModerator.roomHasAvailableRoles(player.getRank(), player.getCurrentRoom()))
				{
					if (InputAndOutput.getYesOrNo())
					{
						new TakeRole().execute(player);
					}
				}
			} 
			else
			{
				InputAndOutput.slowPrint("The scene being filmed here has already been wrapped.\n");
			}

		} 
		else
		{
			InputAndOutput.slowPrintln(
					player.getName() + " is now in the " + player.getCurrentRoom().getRoomName().toLowerCase() + ".");
			if (player.getCurrentRoom().getRoomName().equals("Casting Office"))
			{
				if (player.getMoney() >= CastingOffice.getMoneyCost()[player.getRank() - 1]
						|| player.getFame() >= CastingOffice.getFameCost()[player.getRank() - 1])
				{
					InputAndOutput.slowPrintln(
							"You have enough resources to upgrade your rank. Would you like to upgrade (Y/N)? ");
					if (InputAndOutput.getYesOrNo())
					{
						new Upgrade().execute(player);
						;
					}
				}
			}
		}
		InputAndOutput.slowPrintln();
	}
	
	private String getMoveChoice(Player player)
	{
		List<String> choiceStrings = TurnModerator.showValidMoves(player);
		String choice = null;
		while (!choiceStrings.contains(choice = InputAndOutput.getUserInput()))
		{
			InputAndOutput.slowPrint(choice + " is not a valid choice. \n");
			TurnModerator.showValidMoves(player);
		}
		return choice;*/
	}
}

