import java.util.*;

public class TakeRole implements TurnAction {

	@Override
	public void execute(Player player) {
		List<RoleSpace> extraRoles = player.getCurrentRoom().getAvailableExtraRoles(player);
		List<RoleSpace> starringRoles = player.getCurrentRoom().getAvailableStarRoles(player);
		TakeRoleDialog dialog = new TakeRoleDialog(extraRoles, starringRoles);
		dialog.setModal(true);
		dialog.setVisible(true);
		RoleSpace space = dialog.getChoice();
		RoleType roleType = dialog.getRoleType();
		dialog.dispose();
		player.setRole(new Role(roleType, space));
		Main.boardPanel.setPlayer(player, space);
		space.setIsOccupied(true);
		space.setOccupyingPlayer(player);
		
		/*
		int extraCount = 0;
		List<RoleSpace> availableExtraRoles = new ArrayList<RoleSpace>();
		List<RoleSpace> availableStarringRoles = new ArrayList<RoleSpace>();
		List<String> roleNames = new ArrayList<String>();
		for (RoleSpace roleSpace : extraRoles)
		{
			if(!roleSpace.isOccupied() && roleSpace.getRank() <= player.getRank())
			{
				roleNames.add(roleSpace.getRoleName().toUpperCase());
				availableExtraRoles.add(roleSpace);
				extraCount++;
			}
		}
		
		for (RoleSpace roleSpace : starringRoles)
		{
			if(!roleSpace.isOccupied() && roleSpace.getRank() <= player.getRank())
			{
				roleNames.add(roleSpace.getRoleName().toUpperCase());
				availableStarringRoles.add(roleSpace);
			}
		}
		
		if (availableRoles.size() > 0) 
		{
			printAvailableRoles(availableRoles, extraCount);
			
			InputAndOutput.slowPrintln();
			InputAndOutput.slowPrint("Type the name of the role you'd like to take.\n");
			String choice = null;
			while (!roleNames.contains(choice = InputAndOutput.getUserInput()))
			{
				InputAndOutput.printInvalidSelection(choice);
				printAvailableRoles(availableRoles, extraCount);
			}
			int i = 1;
			for (RoleSpace roleSpace : availableRoles)
			{
				
				if(roleSpace.getRoleName().toUpperCase().equals(choice))
				{
					if(i <= extraCount)
					{
						player.setRole(new Role(RoleType.Extra, roleSpace));
						
					}
					else
					{
						player.setRole(new Role(RoleType.Star, roleSpace));
					}
					JoshBoard.boardPanel.setPlayer(player, roleSpace);
					roleSpace.setIsOccupied(true);
					roleSpace.setOccupyingPlayer(player);
					break;
				}
				i++;
			}
			InputAndOutput.slowPrintln(player.getName() + " has taken the role of \"" + player.getRoleName() + "\"");
		}
	}
	
	private void printAvailableRoles(List<RoleSpace> availableRoles, int extraCount)
	{
		InputAndOutput.slowPrint("The following roles are available:\n");
		if (extraCount > 0) 
		{
			InputAndOutput.slowPrint("Extra Role(s): ");
			for (int i = 0; i < extraCount; i++) 
			{
				RoleSpace roleSpace = availableRoles.get(i);
				if(i == extraCount-1)
				{
					InputAndOutput.slowPrint(roleSpace.getRoleName().toUpperCase() + ", Rank " + roleSpace.getRank() + ".");
				} 
				else
				{
					InputAndOutput.slowPrint(roleSpace.getRoleName().toUpperCase() + ", Rank " + roleSpace.getRank() + ", ");
				}
			}
			InputAndOutput.slowPrintln();
		}

		if (availableRoles.size() - extraCount > 0) 
		{
			InputAndOutput.slowPrint("Starring Role(s): ");
			for (int i = extraCount; i < availableRoles.size(); i++) 
			{
				RoleSpace roleSpace = availableRoles.get(i);
				if(i == availableRoles.size()-1)
				{
					InputAndOutput.slowPrint(roleSpace.getRoleName().toUpperCase() + ", Rank " + roleSpace.getRank() + ".");
				} 
				else
				{
					InputAndOutput.slowPrint(roleSpace.getRoleName().toUpperCase() + ", Rank " + roleSpace.getRank() + ", ");
				}
			}
		}*/
	}
}
