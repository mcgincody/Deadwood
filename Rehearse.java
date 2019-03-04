
public class Rehearse implements TurnAction
{

	@Override
	public void execute(Player player)
	{
		player.addRehearsalToken();
	}
}
