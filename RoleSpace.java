public class RoleSpace
{
	//Attributes
	private Boolean isOccupied;
	private int rank;
	private String roleName;
	private Player occupyingPlayer;
	private int x;
	private int y;
	
	public int getRank()
	{
		return this.rank;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	//Constructors
	RoleSpace (int rank, String roleName){
		this.isOccupied = false;
		this.rank = rank;
		this.roleName = roleName;
	}
	
	//Public Interface Methods
	public Boolean isOccupied(){
		return isOccupied;
	}
	
	public void setIsOccupied(boolean isOccupied)
	{
		this.isOccupied = isOccupied;
	}

	public Player getOccupyingPlayer()
	{
		return this.occupyingPlayer;
	}
	
	public void setOccupyingPlayer(Player player)
	{
		this.occupyingPlayer = player;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public int getX() {
		return x;
	}
}
