import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main
{
	private static PlayerPanel[] playerPanels = new PlayerPanel[8];
	private static JFrame frmDeadwoodStudios;
	public static BoardPanel boardPanel;
	private static Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					WelcomeDialog welcome = new WelcomeDialog();
					welcome.setModal(true);
					welcome.setVisible(true);
					boolean yes = welcome.getAnswer();
					welcome.dispose();

					while (yes)
					{
						 new Main();
						frmDeadwoodStudios.setVisible(true);
						PlayerEntry playerEntry = new PlayerEntry();
						playerEntry.setModal(true);
						playerEntry.setVisible(true);;
						int i = 0;
						String playerOrderMessage = "The player order will be as follows:\n";
						for (Player player : game.getPlayers())
						{

							playerPanels[i].initialize();
							playerPanels[i].setBackground(getColor(player.getColor()));
							playerPanels[i].assignPlayer(player.getName(), player.getImageLocation());
							playerPanels[i].setFame(player.getFame());
							playerOrderMessage += (i + 1) + ") " + player.getName() + "\n";
							i++;
						}
						switch (game.getPlayers().size())
						{
						case 2:
						case 3:
							playerOrderMessage += "\nSince there are only " + game.getPlayers().size()
									+ "  players, you will only play for 3 days.";
							break;
						case 5:
							playerOrderMessage += "\nSince there are 5 players, everyone starts with 2 fame.";
							break;
						case 6:
							playerOrderMessage += "\nSince there are 6 players, everyone starts with 4 fame.";
							break;
						case 7:
						case 8:
							playerOrderMessage += "\nSince there are " + game.getPlayers().size()
									+ "  players, everyone starts at rank 2.";
							break;
						}

						JOptionPane.showMessageDialog(frmDeadwoodStudios, playerOrderMessage);
						List<Player> loopList = new ArrayList<Player>(game.getPlayers());
						Player nextPlayer;
						while (game.hasDaysRemaining())
						{
							while (game.getBoardModerator().getBoard().getSceneCount() > 1)
							{
								nextPlayer = loopList.remove(0);
								game.getTurnModerator().setActivePlayer(nextPlayer);
								loopList.add(nextPlayer);
								doPlayerTurn(nextPlayer);
								JOptionPane.showMessageDialog(frmDeadwoodStudios,
										"That's the end of " + nextPlayer.getName() + "'s turn.");
							}
							game.getBoardModerator().endDay();
						}
						EndGameDialog egd = new EndGameDialog();
						egd.setModal(true);
						egd.setVisible(true);
						yes = egd.getAnswer();
						egd.dispose();
						frmDeadwoodStudios.dispose();
						game = new Game();
					}
					System.exit(0);

				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println(e);
					JOptionPane.showMessageDialog(frmDeadwoodStudios, "YOU'VE RUINED EVERYTHING!");
					System.exit(-99999999);
				}
			}

			private void doPlayerTurn(Player player)
			{
				if (player.getRole() == null)
				{
					List<TurnAction> turnActions = game.getTurnModerator().provideTurnChoices();
					TurnChoiceDialog turnChoice = new TurnChoiceDialog(turnActions, player.getName());
					turnChoice.setModal(true);
					turnChoice.setVisible(true);
					TurnAction action = turnChoice.getChoice();
					turnChoice.dispose();
					switch (action.getClass().getName())
					{
					case "Move":
						action.execute(game.getTurnModerator().getActivePlayer());
						if (player.getCurrentRoom().getCard() != null)
						{
							if (game.getBoardModerator().roomHasAvailableRoles(player.getRank(),
									player.getCurrentRoom()))
							{
								ChoiceDialog choiceDialog = new ChoiceDialog(
										"<html>There are roles available for your rank in this room.<br> Would you like to take a role?</html>");
								choiceDialog.setModal(true);
								choiceDialog.setVisible(true);
								boolean yes = choiceDialog.getChoice();
								choiceDialog.dispose();
								if (!yes)
								{
									return;
								}
								new TakeRole().execute(game.getTurnModerator().getActivePlayer());
								break;

							}
						} 
						else if (player.getCurrentRoom().equals(game.getBoardModerator().getBoard().getTrailer()) 
								&& game.getTurnModerator().playerCanUpgrade())
						{
							String string = "<html>Looks like you can upgrade! Do you want to?</html>";
							ChoiceDialog choiceDialog = new ChoiceDialog(string);
							choiceDialog.setModal(true);
							choiceDialog.setVisible(true);;
							boolean yes = choiceDialog.getChoice();
							choiceDialog.dispose();
							if (!yes)
							{
								return;
							}
							new Upgrade().execute(player);
						}

						break;
					case "TakeRole":
						new TakeRole().execute(game.getTurnModerator().getActivePlayer());
						break;
					case "Upgrade":
						new Upgrade().execute(player);
						break;
					case "EndTurn":
						break;
					}
				} else
				{
					ActingDialog dialog = new ActingDialog(game.getTurnModerator().getActivePlayer());
					dialog.setModal(true);
					dialog.setVisible(true);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main()
	{
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmDeadwoodStudios = new JFrame();
		frmDeadwoodStudios.setTitle("Deadwood Studios: Day 1");
		frmDeadwoodStudios.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		frmDeadwoodStudios.setBounds((int) (width - 1645) / 2, (int) (height - 940) / 2, 1645, 940);
		frmDeadwoodStudios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 1207, 218, 218 };
		gridBagLayout.rowHeights = new int[] { 940 / 4, 940 / 4, 940 / 4, 940 / 4 };
		gridBagLayout.columnWeights = new double[] { 0.734, 0.133, 0.133 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		frmDeadwoodStudios.getContentPane().setLayout(gridBagLayout);




		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		game = new Game();
		game.getBoardModerator().setBoard(new Board());
		BoardPanel b = new BoardPanel(game.getBoardModerator().getBoard());
		boardPanel = b;
		frmDeadwoodStudios.getContentPane().add(b, gbc_panel);

		PlayerPanel playerOnePanel = new PlayerPanel();
		playerPanels[0] = playerOnePanel;
		playerOnePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_playerOnePanel = new GridBagConstraints();
		gbc_playerOnePanel.insets = new Insets(0, 0, 5, 5);
		gbc_playerOnePanel.fill = GridBagConstraints.BOTH;
		gbc_playerOnePanel.gridx = 1;
		gbc_playerOnePanel.gridy = 0;
		frmDeadwoodStudios.getContentPane().add(playerOnePanel, gbc_playerOnePanel);

		PlayerPanel playerTwoPanel = new PlayerPanel();
		playerPanels[1] = playerTwoPanel;
		playerTwoPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_playerTwoPanel = new GridBagConstraints();
		gbc_playerTwoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_playerTwoPanel.fill = GridBagConstraints.BOTH;
		gbc_playerTwoPanel.gridx = 2;
		gbc_playerTwoPanel.gridy = 0;
		frmDeadwoodStudios.getContentPane().add(playerTwoPanel, gbc_playerTwoPanel);

		PlayerPanel PlayerThreePanel = new PlayerPanel();
		playerPanels[2] = PlayerThreePanel;
		PlayerThreePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerThreePanel = new GridBagConstraints();
		gbc_PlayerThreePanel.insets = new Insets(0, 0, 5, 5);
		gbc_PlayerThreePanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerThreePanel.gridx = 1;
		gbc_PlayerThreePanel.gridy = 1;
		frmDeadwoodStudios.getContentPane().add(PlayerThreePanel, gbc_PlayerThreePanel);

		PlayerPanel PlayerFourPanel = new PlayerPanel();
		playerPanels[3] = PlayerFourPanel;
		PlayerFourPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerFourPanel = new GridBagConstraints();
		gbc_PlayerFourPanel.insets = new Insets(0, 0, 5, 0);
		gbc_PlayerFourPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerFourPanel.gridx = 2;
		gbc_PlayerFourPanel.gridy = 1;
		frmDeadwoodStudios.getContentPane().add(PlayerFourPanel, gbc_PlayerFourPanel);

		PlayerPanel PlayerFivePanel = new PlayerPanel();
		playerPanels[4] = PlayerFivePanel;
		PlayerFivePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerFivePanel = new GridBagConstraints();
		gbc_PlayerFivePanel.insets = new Insets(0, 0, 5, 5);
		gbc_PlayerFivePanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerFivePanel.gridx = 1;
		gbc_PlayerFivePanel.gridy = 2;
		frmDeadwoodStudios.getContentPane().add(PlayerFivePanel, gbc_PlayerFivePanel);

		PlayerPanel PlayerSixPanel = new PlayerPanel();
		playerPanels[5] = PlayerSixPanel;
		PlayerSixPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerSixPanel = new GridBagConstraints();
		gbc_PlayerSixPanel.insets = new Insets(0, 0, 5, 0);
		gbc_PlayerSixPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerSixPanel.gridx = 2;
		gbc_PlayerSixPanel.gridy = 2;
		frmDeadwoodStudios.getContentPane().add(PlayerSixPanel, gbc_PlayerSixPanel);

		PlayerPanel PlayerSevenPanel = new PlayerPanel();
		playerPanels[6] = PlayerSevenPanel;
		PlayerSevenPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerSevenPanel = new GridBagConstraints();
		gbc_PlayerSevenPanel.insets = new Insets(0, 0, 0, 5);
		gbc_PlayerSevenPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerSevenPanel.gridx = 1;
		gbc_PlayerSevenPanel.gridy = 3;
		frmDeadwoodStudios.getContentPane().add(PlayerSevenPanel, gbc_PlayerSevenPanel);

		PlayerPanel PlayerEightPanel = new PlayerPanel();
		playerPanels[7] = PlayerEightPanel;
		PlayerEightPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_PlayerEightPanel = new GridBagConstraints();
		gbc_PlayerEightPanel.fill = GridBagConstraints.BOTH;
		gbc_PlayerEightPanel.gridx = 2;
		gbc_PlayerEightPanel.gridy = 3;
		frmDeadwoodStudios.getContentPane().add(PlayerEightPanel, gbc_PlayerEightPanel);

	}

	public static Color getColor(int i)
	{
		switch (i)
		{
		case 0:
			return new Color(255, 130, 118);
		case 1:
			return new Color(255, 204, 126);
		case 2:
			return new Color(255, 255, 131);
		case 3:
			return new Color(172, 220, 138);
		case 4:
			return new Color(116, 194, 227);
		case 5:
			return new Color(112, 145, 255);
		case 6:
			return new Color(164, 98, 186);
		case 7:
			return new Color(229, 113, 154);

		default:
			return null;
		}
	}

	public static Game getCurrentGame()
	{
		return game;
	}

	public static PlayerPanel[] getPlayerPanels()
	{
		return playerPanels;
	}

	public static JFrame getMainPanel()
	{
		return frmDeadwoodStudios;
	}

	public Game getGame()
	{
		return game;
	}
}
