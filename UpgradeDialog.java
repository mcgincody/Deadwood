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

public class UpgradeDialog extends JDialog
{
	private JButton[] moneyButtons = new JButton[5];
	private JButton[] fameButtons = new JButton[5];
	private final JPanel contentPanel = new JPanel();
	private String currency;
	private int cost;
	private int rank;


	/**
	 * Create the dialog.
	 */
	public UpgradeDialog()
	{
		this.setTitle(Main.getCurrentGame().getTurnModerator().getActivePlayer().getName() + " is upgrading...");
		setSize(412,422);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblplayerIsUpgrading = new JLabel("Welcome to the casting office!");
			lblplayerIsUpgrading.setFont(new Font("Tahoma", Font.PLAIN, 22));
			GridBagConstraints gbc_lblplayerIsUpgrading = new GridBagConstraints();
			gbc_lblplayerIsUpgrading.insets = new Insets(0, 0, 5, 5);
			gbc_lblplayerIsUpgrading.gridwidth = 4;
			gbc_lblplayerIsUpgrading.gridx = 1;
			gbc_lblplayerIsUpgrading.gridy = 0;
			contentPanel.add(lblplayerIsUpgrading, gbc_lblplayerIsUpgrading);
		}
		{
			JLabel lblPleaseSelectAn = new JLabel("Please select an upgrade below.");
			GridBagConstraints gbc_lblPleaseSelectAn = new GridBagConstraints();
			gbc_lblPleaseSelectAn.gridwidth = 4;
			gbc_lblPleaseSelectAn.insets = new Insets(0, 0, 5, 5);
			gbc_lblPleaseSelectAn.gridx = 1;
			gbc_lblPleaseSelectAn.gridy = 1;
			contentPanel.add(lblPleaseSelectAn, gbc_lblPleaseSelectAn);
		}
		{
			JLabel lblRank = new JLabel("Rank");
			GridBagConstraints gbc_lblRank = new GridBagConstraints();
			gbc_lblRank.insets = new Insets(0, 0, 5, 5);
			gbc_lblRank.gridx = 1;
			gbc_lblRank.gridy = 3;
			contentPanel.add(lblRank, gbc_lblRank);
		}
		{
			JLabel lblCost = new JLabel("$ Cost");
			GridBagConstraints gbc_lblCost = new GridBagConstraints();
			gbc_lblCost.insets = new Insets(0, 0, 5, 5);
			gbc_lblCost.gridx = 2;
			gbc_lblCost.gridy = 3;
			contentPanel.add(lblCost, gbc_lblCost);
		}
		{
			JLabel lblFameCost = new JLabel("Fame Cost");
			GridBagConstraints gbc_lblFameCost = new GridBagConstraints();
			gbc_lblFameCost.insets = new Insets(0, 0, 5, 5);
			gbc_lblFameCost.gridx = 4;
			gbc_lblFameCost.gridy = 3;
			contentPanel.add(lblFameCost, gbc_lblFameCost);
		}
		{
			JLabel label = new JLabel("2");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 4;
			contentPanel.add(label, gbc_label);
		}
		{
			JButton money2 = new JButton("4");
			money2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "MONEY";
					cost = 4;
					rank = 2;
					setVisible(false);
					
				}
			});
			money2.setEnabled(false);
			moneyButtons[0] = money2;
			GridBagConstraints gbc_money2 = new GridBagConstraints();
			gbc_money2.insets = new Insets(0, 0, 5, 5);
			gbc_money2.gridx = 2;
			gbc_money2.gridy = 4;
			contentPanel.add(money2, gbc_money2);
		}
		{
			JButton fame2 = new JButton("5");
			fame2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "FAME";
					cost = 5;
					rank = 2;
					setVisible(false);
				}
			});
			fame2.setEnabled(false);
			fameButtons[0] = fame2;
			GridBagConstraints gbc_fame2 = new GridBagConstraints();
			gbc_fame2.insets = new Insets(0, 0, 5, 5);
			gbc_fame2.gridx = 4;
			gbc_fame2.gridy = 4;
			contentPanel.add(fame2, gbc_fame2);
		}
		{
			JLabel label = new JLabel("3");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 5;
			contentPanel.add(label, gbc_label);
		}
		{
			JButton money3 = new JButton("10");
			money3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "MONEY";
					cost = 10;
					rank = 3;
					setVisible(false);
				}
			});
			money3.setEnabled(false);
			moneyButtons[1] = money3;
			GridBagConstraints gbc_money3 = new GridBagConstraints();
			gbc_money3.insets = new Insets(0, 0, 5, 5);
			gbc_money3.gridx = 2;
			gbc_money3.gridy = 5;
			contentPanel.add(money3, gbc_money3);
		}
		{
			JButton fame3 = new JButton("10");
			fame3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "FAME";
					cost = 10;
					rank = 3;
					setVisible(false);
				}
			});
			fame3.setEnabled(false);
			fameButtons[1] = fame3;
			GridBagConstraints gbc_fame3 = new GridBagConstraints();
			gbc_fame3.insets = new Insets(0, 0, 5, 5);
			gbc_fame3.gridx = 4;
			gbc_fame3.gridy = 5;
			contentPanel.add(fame3, gbc_fame3);
		}
		{
			JLabel label = new JLabel("4");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 6;
			contentPanel.add(label, gbc_label);
		}
		{
			JButton money4 = new JButton("18");
			money4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "MONEY";
					cost = 18;
					rank = 4;
					setVisible(false);
				}
			});
			money4.setEnabled(false);
			moneyButtons[2] = money4;
			GridBagConstraints gbc_money4 = new GridBagConstraints();
			gbc_money4.insets = new Insets(0, 0, 5, 5);
			gbc_money4.gridx = 2;
			gbc_money4.gridy = 6;
			contentPanel.add(money4, gbc_money4);
		}
		{
			JButton fame4 = new JButton("15");
			fame4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "FAME";
					cost = 15;
					rank = 4;
					setVisible(false);
				}
			});
			fame4.setEnabled(false);
			fameButtons[2] = fame4;
			GridBagConstraints gbc_fame4 = new GridBagConstraints();
			gbc_fame4.insets = new Insets(0, 0, 5, 5);
			gbc_fame4.gridx = 4;
			gbc_fame4.gridy = 6;
			contentPanel.add(fame4, gbc_fame4);
		}
		{
			JLabel label = new JLabel("5");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 7;
			contentPanel.add(label, gbc_label);
		}
		{
			JButton money5 = new JButton("28");
			money5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "MONEY";
					cost = 28;
					rank = 5;
					setVisible(false);
				}
			});
			money5.setEnabled(false);
			moneyButtons[3] = money5;
			GridBagConstraints gbc_money5 = new GridBagConstraints();
			gbc_money5.insets = new Insets(0, 0, 5, 5);
			gbc_money5.gridx = 2;
			gbc_money5.gridy = 7;
			contentPanel.add(money5, gbc_money5);
		}
		{
			JButton fame5 = new JButton("20");
			fame5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "FAME";
					cost = 20;
					rank = 5;
					setVisible(false);
				}
			});
			fame5.setEnabled(false);
			fameButtons[3] = fame5;
			GridBagConstraints gbc_fame5 = new GridBagConstraints();
			gbc_fame5.insets = new Insets(0, 0, 5, 5);
			gbc_fame5.gridx = 4;
			gbc_fame5.gridy = 7;
			contentPanel.add(fame5, gbc_fame5);
		}
		{
			JLabel label = new JLabel("6");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 0, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 8;
			contentPanel.add(label, gbc_label);
		}
		{
			JButton money6 = new JButton("40");
			money6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "MONEY";
					cost = 40;
					rank = 6;
					setVisible(false);
				}
			});
			money6.setEnabled(false);
			moneyButtons[4] = money6;
			GridBagConstraints gbc_money6 = new GridBagConstraints();
			gbc_money6.insets = new Insets(0, 0, 0, 5);
			gbc_money6.gridx = 2;
			gbc_money6.gridy = 8;
			contentPanel.add(money6, gbc_money6);
		}
		{
			JButton fame6 = new JButton("25");
			fame6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currency = "FAME";
					cost = 25;
					rank = 6;
					setVisible(false);
				}
			});
			fame6.setEnabled(false);
			fameButtons[4] = fame6;
			GridBagConstraints gbc_fame6 = new GridBagConstraints();
			gbc_fame6.insets = new Insets(0, 0, 0, 5);
			gbc_fame6.gridx = 4;
			gbc_fame6.gridy = 8;
			contentPanel.add(fame6, gbc_fame6);
		}
		enableValidButtons();
	}
	
	public void enableValidButtons()
	{
		Player player = Main.getCurrentGame().getTurnModerator().getActivePlayer();
		int playerRank = player.getRank();
		int playerMoney = player.getMoney();
		int playerFame = player.getFame();
		for (int i = 0; i < moneyButtons.length; i++)
		{
			if(playerRank < i+2 && Integer.parseInt(moneyButtons[i].getText()) <= playerMoney)
			{
				moneyButtons[i].setEnabled(true);;
			}
			
			if(playerRank < i+2 && Integer.parseInt(fameButtons[i].getText()) <= playerFame)
			{
				fameButtons[i].setEnabled(true);
			}
		}
	}
	
	public String getCurrency()
	{
		return currency;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getRank ()
	{
		return rank;
	}
}
