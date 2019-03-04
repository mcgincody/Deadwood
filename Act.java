import javax.swing.JOptionPane;

public class Act implements TurnAction
{
	//Hello from [test]
	@Override
	public void execute(Player player)
	{
		// Get Card
		Card card = player.getCurrentRoom().getCard();
		// Get budget from Card
		int movieBudget = card.getBudget();
		// Have player roll the dice
		int dieRoll = player.rollDice(movieBudget);
		// Get the player's rehearsal tokens
		int rehearsalTokens = player.getRehearsalTokens();
		// Determine if the roll was successful
		boolean rollWasSuccessful = rollWasSuccessful(dieRoll, rehearsalTokens, movieBudget);
		// Get success or failure rewards
		int[] rewards = player.getRole().getRollRewards(rollWasSuccessful);
		//Print Results
		StringBuilder builder = new StringBuilder();
		builder.append("You rolled a " + dieRoll + " (plus a rehearsal bonus of " + player.getRehearsalTokens() +")...\n\n");
		Room room = player.getCurrentRoom();
		//Take action depending on success.
		if (rollWasSuccessful)
		{
			builder.append("...and acted successfully!\n");
			// Get current room
			
			// Decrease clapper count
			room.decreaseClapperCount();
			Main.boardPanel.removeClapper(room);
			// Add rewards
			player.addFunds(rewards);
			if (player.getRole().getRoleType() == RoleType.Extra)
			{
				builder.append("You are awarded $1 and 1 fame.");
			} 
			else
			{
				builder.append("You are awarded 2 fame.");
			}
		}
		else
		{
			builder.append("...and did not act successfully.\n");
		//	rewards = player.getRole().getFailureRewards();
			player.addFunds(rewards);
		//  if(player.getRole().getFailureRewards()[0] > 0)
			if (player.getRole().getRoleType() == RoleType.Extra)
			{
				builder.append("As an Extra, " + player.getName() + " still gets $" + rewards[0] + ".");
			}
		}
		JOptionPane.showMessageDialog(Main.getMainPanel(), builder.toString());
		if (room.getClapperCount() == 0)
		{
			handleWrap(player, room);
		}
	}
	
	public boolean rollWasSuccessful(int roll, int rehearsalTokens, int budget)
	{
		if(roll + rehearsalTokens >= budget)
		{
			return true;
		}
		return false;
	}
	
	public void handleWrap(Player player, Room room)
	{
		if(room.getCard().playerOnCard())
		{
			Main.getCurrentGame().getTurnModerator().determineWrapReward(player.rollWrapRewards());
		}
		else
		{
			JOptionPane.showMessageDialog(Main.boardPanel, "There was no one on the card so no bonuses are awarded.");
		}	
		Main.getCurrentGame().getBoardModerator().clearPlayerRoles(room);
		Main.getCurrentGame().getBoardModerator().removeCard(room);
		Main.getCurrentGame().getTurnModerator().removeAllRehearsalTokens(room.OccupyingPlayers);
	}
}
