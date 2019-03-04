
public class Upgrade implements TurnAction
{

	@Override
	public void execute(Player player)
	{
		UpgradeDialog upDialog = new UpgradeDialog();
		upDialog.setModal(true);
		upDialog.setVisible(true);
		int cost = upDialog.getCost();
		int rank = upDialog.getRank();
		String currency = upDialog.getCurrency();
		upDialog.dispose();
		Main.getCurrentGame().getTurnModerator().increasePlayerRank(player, cost, rank, currency);
	}
}
