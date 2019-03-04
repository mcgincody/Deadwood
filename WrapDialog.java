import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WrapDialog extends JDialog
{
	private JLabel[] roleRolls;
	private JLabel[] totals;
	private JLabel windfall;
	/**
	 * Create the dialog.
	 * @param rolledDice 
	 */
	public WrapDialog(List<RoleSpace> spacesForRewards, int[] rolledDice)
	{
		roleRolls = new JLabel[spacesForRewards.size()];
		totals = new JLabel[roleRolls.length];

		setSize(568,363);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("OK");
				btnConfirm.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						dispose();
					}
				});
				btnConfirm.setVisible(false);
				buttonPane.add(btnConfirm);
				
				JButton btnRollRewards = new JButton("Roll Rewards");
				btnRollRewards.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						btnConfirm.setVisible(true);
						btnRollRewards.setVisible(false);
						displayWrapRewards(spacesForRewards, rolledDice);
						
					}
				});
				buttonPane.add(btnRollRewards);
			}
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		
		gbl_panel.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblThatsAWrap = new JLabel("That's a wrap! Click the button below to roll wrap rewards.");
		GridBagConstraints gbc_lblThatsAWrap = new GridBagConstraints();
		gbc_lblThatsAWrap.gridwidth = 4;
		gbc_lblThatsAWrap.insets = new Insets(0, 0, 5, 5);
		gbc_lblThatsAWrap.gridx = 1;
		gbc_lblThatsAWrap.gridy = 0;
		panel.add(lblThatsAWrap, gbc_lblThatsAWrap);
		
		JLabel lblplayerAs = new JLabel("Nobody as");
		if (spacesForRewards.get(0).isOccupied())
		{
			lblplayerAs.setText(spacesForRewards.get(0).getOccupyingPlayer().getName() + " as");
		}
		GridBagConstraints gbc_lblplayerAs = new GridBagConstraints();
		gbc_lblplayerAs.insets = new Insets(0, 0, 5, 5);
		gbc_lblplayerAs.gridx = 2;
		gbc_lblplayerAs.gridy = 2;
		panel.add(lblplayerAs, gbc_lblplayerAs);
		
		JLabel lblRole = new JLabel(spacesForRewards.get(0).getRoleName());
		GridBagConstraints gbc_lblRole = new GridBagConstraints();
		gbc_lblRole.insets = new Insets(0, 0, 5, 5);
		gbc_lblRole.gridx = 2;
		gbc_lblRole.gridy = 3;
		panel.add(lblRole, gbc_lblRole);
		
		JLabel role1Dice = new JLabel("");
		roleRolls[roleRolls.length-1] = role1Dice;
		GridBagConstraints gbc_role1Dice = new GridBagConstraints();
		gbc_role1Dice.insets = new Insets(0, 0, 5, 5);
		gbc_role1Dice.gridx = 2;
		gbc_role1Dice.gridy = 4;
		panel.add(role1Dice, gbc_role1Dice);
		
		JLabel role1Reward = new JLabel("$");
		totals[roleRolls.length-1] = role1Reward;
		GridBagConstraints gbc_role1Reward = new GridBagConstraints();
		gbc_role1Reward.insets = new Insets(0, 0, 5, 5);
		gbc_role1Reward.gridx = 2;
		gbc_role1Reward.gridy = 6;
		panel.add(role1Reward, gbc_role1Reward);
		
		
		if (spacesForRewards.size() > 1)
		{
			JLabel lblplayerAs_1 = new JLabel("Nobody as");
			if (spacesForRewards.get(1).isOccupied())
			{
				lblplayerAs_1.setText(spacesForRewards.get(1).getOccupyingPlayer().getName() + " as");
			}
			GridBagConstraints gbc_lblplayerAs_1 = new GridBagConstraints();
			gbc_lblplayerAs_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblplayerAs_1.gridx = 3;
			gbc_lblplayerAs_1.gridy = 2;
			panel.add(lblplayerAs_1, gbc_lblplayerAs_1);
			
			JLabel lblRole_1 = new JLabel(spacesForRewards.get(1).getRoleName());
			GridBagConstraints gbc_lblRole_1 = new GridBagConstraints();
			gbc_lblRole_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblRole_1.gridx = 3;
			gbc_lblRole_1.gridy = 3;
			panel.add(lblRole_1, gbc_lblRole_1);
			
			JLabel role2Dice = new JLabel("");
			roleRolls[roleRolls.length-2] = role2Dice;
			GridBagConstraints gbc_role2Dice = new GridBagConstraints();
			gbc_role2Dice.insets = new Insets(0, 0, 5, 5);
			gbc_role2Dice.gridx = 3;
			gbc_role2Dice.gridy = 4;
			panel.add(role2Dice, gbc_role2Dice);
			
			JLabel role2Reward = new JLabel("$");
			totals[roleRolls.length-2] = role2Reward;
			GridBagConstraints gbc_role2Reward = new GridBagConstraints();
			gbc_role2Reward.insets = new Insets(0, 0, 5, 5);
			gbc_role2Reward.gridx = 3;
			gbc_role2Reward.gridy = 6;
			panel.add(role2Reward, gbc_role2Reward);
		}

		if (spacesForRewards.size() > 2)
		{
			JLabel lblplayerAs_2 = new JLabel("Nobody as");
			if (spacesForRewards.get(2).isOccupied())
			{
				lblplayerAs_2.setText(spacesForRewards.get(2).getOccupyingPlayer().getName() + " as");
			}
			
			GridBagConstraints gbc_lblplayerAs_2 = new GridBagConstraints();
			gbc_lblplayerAs_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblplayerAs_2.gridx = 4;
			gbc_lblplayerAs_2.gridy = 2;
			panel.add(lblplayerAs_2, gbc_lblplayerAs_2);
			
			JLabel lblRole_2 = new JLabel(spacesForRewards.get(2).getRoleName());
			GridBagConstraints gbc_lblRole_2 = new GridBagConstraints();
			gbc_lblRole_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblRole_2.gridx = 4;
			gbc_lblRole_2.gridy = 3;
			panel.add(lblRole_2, gbc_lblRole_2);
			
			JLabel role3Dice = new JLabel("");
			roleRolls[roleRolls.length-3] = role3Dice;
			GridBagConstraints gbc_role3Dice = new GridBagConstraints();
			gbc_role3Dice.insets = new Insets(0, 0, 5, 5);
			gbc_role3Dice.gridx = 4;
			gbc_role3Dice.gridy = 4;
			panel.add(role3Dice, gbc_role3Dice);
			
			JLabel role3Reward = new JLabel("$");
			totals[roleRolls.length-3] = role3Reward;
			GridBagConstraints gbc_role3Reward = new GridBagConstraints();
			gbc_role3Reward.insets = new Insets(0, 0, 5, 5);
			gbc_role3Reward.gridx = 4;
			gbc_role3Reward.gridy = 6;
			panel.add(role3Reward, gbc_role3Reward);
		}
		
		JLabel lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 6;
		panel.add(lblTotal, gbc_lblTotal);
		
		
		
		
		
		JLabel lblWindfallBonuses = new JLabel("Windfall Bonuses:");
		GridBagConstraints gbc_lblWindfallBonuses = new GridBagConstraints();
		gbc_lblWindfallBonuses.insets = new Insets(0, 0, 0, 5);
		gbc_lblWindfallBonuses.gridx = 1;
		gbc_lblWindfallBonuses.gridy = 8;
		panel.add(lblWindfallBonuses, gbc_lblWindfallBonuses);
		
		JLabel lblPlyrwindfall = new JLabel("");
		windfall = lblPlyrwindfall;
		GridBagConstraints gbc_lblPlyrwindfall = new GridBagConstraints();
		gbc_lblPlyrwindfall.gridwidth = 3;
		gbc_lblPlyrwindfall.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlyrwindfall.gridx = 2;
		gbc_lblPlyrwindfall.gridy = 8;
		panel.add(lblPlyrwindfall, gbc_lblPlyrwindfall);
	}
	
	private void displayWrapRewards(List<RoleSpace> spacesForRewards, int[] rolledDice)
	{
		int[] spaceTotals = new int[spacesForRewards.size()];
		for (int i = 0; i < rolledDice.length; i++)
		{
			spaceTotals[i % spacesForRewards.size()] += rolledDice[rolledDice.length - 1 - i];
			JLabel temp = roleRolls[i % spacesForRewards.size()];
			temp.setText(temp.getText() + String.valueOf(rolledDice[rolledDice.length - 1 - i]));
			if (rolledDice.length - i > spacesForRewards.size())
			{
				temp.setText(temp.getText() + ", ");
			}
		}
		for (int i = 0; i < totals.length; i++)
		{
			totals[i].setText(totals[i].getText() + String.valueOf(spaceTotals[i]));
		}
		
		int occupiedCount = 0;
		StringBuilder builder = new StringBuilder();
		for(RoleSpace space : Main.getCurrentGame().getTurnModerator().getActivePlayer().getCurrentRoom().getSpaces())
		{
			if(space.isOccupied())
			{
				occupiedCount++;
				if(occupiedCount > 1)
				{
					builder.append(", ");
				}
				builder.append(space.getOccupyingPlayer().getName() + ": $" + space.getRank());
			}
		}
		windfall.setText(builder.toString());
		
	}

}
