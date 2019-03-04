import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EndGameDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private String[] places = new String[] {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
	private boolean answer = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			EndGameDialog dialog = new EndGameDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EndGameDialog()
	{
		setSize(450,374);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 56, 0, 113, 30, 0};
		gbl_contentPanel.rowHeights = new int[]{44, 43, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 36));
		GridBagConstraints gbc_lblResults = new GridBagConstraints();
		gbc_lblResults.gridwidth = 3;
		gbc_lblResults.anchor = GridBagConstraints.NORTH;
		gbc_lblResults.insets = new Insets(0, 0, 5, 5);
		gbc_lblResults.gridx = 1;
		gbc_lblResults.gridy = 0;
		contentPanel.add(lblResults, gbc_lblResults);
		
		
		int[][] amounts = new int[Main.getCurrentGame().getPlayers().size()][4];
		Player[] players = new Player[amounts.length];
		int i = 0;
		//get player stats
		for(Player player : Main.getCurrentGame().getPlayers())
		{
			amounts[i][0] = player.getMoney();
			amounts[i][1] = player.getFame();
			amounts[i][2] = player.getRank();
			amounts[i][3] = amounts[i][0] + amounts[i][1] + (amounts[i][2] * 5);
			players[i] = player;
			i++;
		}
		// Determine ranking
		rankPlayers(amounts, players);
		JLabel[] labelPair;
		for (i = 0; i < players.length; i++)
		{
			labelPair = makeRankLabel(amounts[i], players[i].getName(), i);

			GridBagConstraints gbc_lblYo = new GridBagConstraints();
			gbc_lblYo.anchor = GridBagConstraints.EAST;
			gbc_lblYo.insets = new Insets(0, 0, 5, 5);
			gbc_lblYo.gridx = 1;
			gbc_lblYo.gridy = 2+i;
			contentPanel.add(labelPair[0], gbc_lblYo);
			
			GridBagConstraints gbc_lblYo2 = new GridBagConstraints();
			
			gbc_lblYo2.insets = new Insets(0, 0, 5, 5);
			gbc_lblYo2.gridx = 2;
			gbc_lblYo2.gridy = 2+i;
			contentPanel.add(labelPair[1], gbc_lblYo2);
			
			GridBagConstraints gbc_lblThingsAndStuff = new GridBagConstraints();
			gbc_lblThingsAndStuff.anchor = GridBagConstraints.WEST;
			gbc_lblThingsAndStuff.insets = new Insets(0, 0, 5, 0);
			gbc_lblThingsAndStuff.gridx = 3;
			gbc_lblThingsAndStuff.gridy = 2+i;
			contentPanel.add(labelPair[2], gbc_lblThingsAndStuff);
		}

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				buttonPane.add(panel, BorderLayout.SOUTH);
				{
					JButton btnYes = new JButton("Yes");
					btnYes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							setVisible(false);
						}
					});
					panel.add(btnYes);
				}
				{
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							answer = false;
							setVisible(false);
						}
					});
					panel.add(btnNo);
				}
			}
			{
				JPanel panel = new JPanel();
				buttonPane.add(panel, BorderLayout.NORTH);
				{
					JLabel lblWouldYouLike = new JLabel("Would you like to play another round?");
					panel.add(lblWouldYouLike);
				}
			}
		}
	}
	
	public void rankPlayers(int[][] scores, Player[] players)
	{
		int[] temp;
		Player tempPlayer;
		for (int i = scores.length - 1; i > 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if(scores[j][3] < scores[j+1][3])
				{
					tempPlayer = players[j];
					temp = scores[j];
					scores[j] = scores[j+1];
					players[j] = players[j+1];
					scores[j+1] = temp;
					players[j+1] = tempPlayer;
				}
				else if(scores[j][3] == scores[j+1][3])
				{
					if (players[j].getTurnOrder() > players[j+1].getTurnOrder())
					{
						tempPlayer = players[j];
						temp = scores[j];
						scores[j] = scores[j+1];
						players[j] = players[j+1];
						scores[j+1] = temp;
						players[j+1] = tempPlayer;
					}
				}
			}
		}
	}
	
	public JLabel[] makeRankLabel(int[] scores, String name, int rank)
	{
		JLabel placeLabel = new JLabel(places[rank] + " Place:");
		JLabel nameLabel = new JLabel(name);
		JLabel statsLabel = new JLabel("$" + scores[0] + " + " + scores[1] + " Fame + " + scores[2] + "x5 = " + scores[3]);
		
		return new JLabel[] {placeLabel,nameLabel, statsLabel};
	}
	
	public boolean getAnswer()
	{
		return answer;
	}
}
