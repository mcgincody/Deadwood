import java.util.*;

public class Game
{
	private List<Player> players;
	private int daysToPlay;
	private int currentDay;
	private TurnModerator turnModerator;
	private BoardModerator boardModerator;
	
	public TurnModerator getTurnModerator()
	{
		if(turnModerator == null)
		{
			turnModerator = new TurnModerator();
		}
		return turnModerator;
	}
	
	public BoardModerator getBoardModerator()
	{
		if(boardModerator == null)
		{
			boardModerator = new BoardModerator();
		}
		return boardModerator;
	}

	public Game()
	{
		this.currentDay = 1;
	}

	public int getCurrentDay()
	{
		return this.currentDay;
	}

	public List<Player> getPlayers()
	{
		return this.players;
	}

	private List<Player> initializePlayers(String[] playerNames, int[] colors, int[] specialRules)
	{
		randomizeTurnOrder(playerNames, colors);
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < playerNames.length; i++)
		{
			players.add(new Player(i + 1, playerNames[i], colors[i], specialRules[1], specialRules[2],
					boardModerator.getBoard().getTrailer()));
		}

		for (Player player : players)
		{
			boardModerator.getBoard().getTrailer().addPlayer(player);
		}
		return players;
	}

	public boolean noMoreDays()
	{
		return (currentDay > daysToPlay);
	}

	public void incrementDay()
	{
		this.currentDay++;
		if (noMoreDays())
		{
			endGame();
			return;
		}
	}

	public Player endGame()
	{
		Player winner = null;
		int currentScore;
		int maxScore = 0;
		for (Player player : players)
		{
			if ((currentScore = player.getFame() + player.getMoney() + player.getRank() * 5) >= maxScore)
			{
				maxScore = currentScore;
				winner = player;
			}
		}
		return winner;
	}

	public void printEndingScore(String name, int score)
	{
	}

	public void printVictoryMessage(Player player)
	{

	}

	public int[] getSpecialRules(int playerCount)
	{
		// [0] modifies days, [1] modifies starting fame, [2] modifies starting
		// rank.
		int[] modifiers = new int[] { 4, 0, 1 };
		switch (playerCount)
		{
		case 2:
		case 3:
			modifiers[0] = 3;
			break;
		case 5:
			modifiers[1] = 2;
			break;
		case 6:
			modifiers[1] = 4;
			break;
		case 7:
		case 8:
			modifiers[2] = 2;
			break;
		}
		return modifiers;
	}

	private void randomizeTurnOrder(String[] playerNames, int[] colors)
	{
		int n = playerNames.length;
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++)
		{
			int change = i + random.nextInt(n - i);
			String temp = playerNames[i];
			int tempInt = colors[i];
			playerNames[i] = playerNames[change];
			colors[i] = colors[change];
			playerNames[change] = temp;
			colors[change] = tempInt;
		}
	}

	public List<Player> addPlayersAndSetSpecialRules(List<String> names, int[] colors)
	{
		int[] specialRules = setrules(names.size());
		this.players = initializePlayers(names.toArray(new String[names.size()]), colors, specialRules);
		this.daysToPlay = specialRules[0];
		return this.players;
	}

	private int[] setrules(int length)
	{
		return getSpecialRules(length);
	}

	public boolean hasDaysRemaining()
	{
		return this.currentDay <= this.daysToPlay;
	}

}
