import java.util.List;

public class Card
{
	//Attributes
	private String MovieName;
	private int Budget;
	private List<RoleSpace> Spaces;
	private String img;
	private Boolean Hidden;

	public String getMovieName()
	{
		return MovieName;
	}

	public void setMovieName(String movieName)
	{
		MovieName = movieName;
	}

	public int getBudget()
	{
		return Budget;
	}

	public void setBudget(int budget)
	{
		Budget = budget;
	}

	public List<RoleSpace> getSpaces()
	{
		return Spaces;
	}

	public void setSpaces(List<RoleSpace> spaces)
	{
		Spaces = spaces;
	}

	public Boolean getHidden()
	{
		return Hidden;
	}

	public void setHidden(Boolean hidden)
	{
		Hidden = hidden;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	//Constructors
	Card(String movieName, int budget, List<RoleSpace> roleSpaces) {
		this.MovieName = movieName;
		this.Budget = budget;
		this.Spaces = roleSpaces;
	}

	//Public Interface Methods
	public void SetRoleOccupied(){

	}
	
	public boolean playerOnCard()
	{
		for (RoleSpace roleSpace : this.Spaces)
		{
			if (roleSpace.isOccupied())
			{
				return true;
			}
		}
		return false;
	}
}
