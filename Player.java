import java.util.List;
import java.util.Random;
public class Player 
{
	private int turnOrder;
	private String name;
	private int money;
	private int fame;
	private int rehearsalTokens;
	private int color;
	private int rank;
	private Room currentRoom;
	private Role role;
	private String diceImageLocation;

	
	public Role getRole()
	{
		return role;
	}
	public void setDiceImageLocation(int rank)
	{
		this.diceImageLocation = this.diceImageLocation.substring(0, 6) + rank + ".png";
	}
	public void setDiceImageLocation()
	{

		this.diceImageLocation = "dice/" + getColorCharFromColorNumber(this.getColor()) + rank + ".png";
	}
	public String getColorCharFromColorNumber(int colorNumber)
	{
		switch(colorNumber)
		{
		case 0:return "r";
		case 1:return "o";
		case 2: return "y";
		case 3: return "g";
		case 4: return "c";
		case 5: return "b";
		case 6: return "v";
		case 7: return "p";
		
		default:
			return null;
		}
	}
	public String getImageLocation()
	{
		return this.diceImageLocation;
	}
	public void setRole(Role role)
	{
		this.role = role;
	}
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	public void setCurrentRoom(Room currentRoom)
	{
		this.currentRoom = currentRoom;
	}
	public int getTurnOrder() {
		return turnOrder;
	}
	public void setTurnOrder(int turnOrder) {
		this.turnOrder = turnOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getFame() {
		return fame;
	}
	
	public void setFame(int fame) {
		this.fame = fame;
	}
	public void addFunds(int[] funds) {
		this.money += funds[0];
		this.fame += funds[1];
		PlayerPanel panelToUpdate = Main.getPlayerPanels()[this.getTurnOrder()-1];
		panelToUpdate.setMoney(this.getMoney());
		panelToUpdate.setFame(this.getFame());
	}
	public int getRehearsalTokens() {
		return rehearsalTokens;
	}
	public void addRehearsalToken() {
		this.rehearsalTokens++;
	}
	
	public void removeRehearsalTokens()
	{
		this.rehearsalTokens = 0;
	}
			
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getRoleName()
	{
		return this.role.getRoleSpace().getRoleName();
	}
	
	public Player(int turnOrder, String name, int color, int fame, int rank, Room room) 
	{
		this.turnOrder = turnOrder;
		this.name = name;
		this.color = color;
		this.fame = fame;
		this.rank = rank;
		this.role = null;
		this.rehearsalTokens = 0;
		this.currentRoom = room;
		setDiceImageLocation();
		Main.boardPanel.setPlayer(this, this.currentRoom);
	}
	

	//TODO: fix the horrible choice switcher.
	public void chooseTurnAction(List<TurnAction> possibleActions)
	{
		while (true)
		{
			String choice = null;
			for (TurnAction action : possibleActions)
			{
				if (action.getClass().getName().toUpperCase().equals(choice))
				{
					action.execute(this);
					return;
				}
			}
		}
	}
	
	public int rollDice(int budget)
	{
		Random random = new Random();
		int dieRoll = random.nextInt(6) + 1; 
		return dieRoll;
	}
	
	public int[] rollWrapRewards()
	{
		Random random = new Random();
		int[] rolledValues = new int[this.currentRoom.getCard().getBudget()];
		for (int i = 0; i < rolledValues.length; i++)
		{
			rolledValues[i] = (random.nextInt(6)) + 1;
		}
		return rolledValues;
	}
}