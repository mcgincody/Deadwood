import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class ActingDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ActingDialog(Player player)
	{
		this.setTitle(Main.getCurrentGame().getTurnModerator().getActivePlayer().getName() + " is on the set...");
		setResizable(false);
		setSize(450,300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblPlayer = new JLabel("Player:");
		
		JLabel lblPlayerName = new JLabel(player.getName());
		
		JLabel lblRole = new JLabel("Role:");
		
		JLabel lblRoleName = new JLabel(player.getRoleName());
		
		JLabel lblScene = new JLabel("Scene:");
		
		JLabel lblSceneName = new JLabel(player.getCurrentRoom().getCard().getMovieName());
		
		JLabel lblBudget = new JLabel("Budget:");
		
		JLabel lblSceneBudget = new JLabel("$" + String.valueOf(player.getCurrentRoom().getCard().getBudget()) + " million.");
		
		JLabel lblRehearsalBonus = new JLabel("Rehearsal Bonus:");
		
		JLabel lblRehearsalTokens = new JLabel(String.valueOf(player.getRehearsalTokens()));
		
		JLabel lblChanceOfSuccessful = new JLabel("Chance of Successful Roll:");
		
		double number = 100*(7.0 - player.getCurrentRoom().getCard().getBudget() + player.getRehearsalTokens())/6.0;

		number = Math.round(number * 100);
		number = number/100;	
		
		JLabel lblPercentChance = new JLabel(String.valueOf(number) + "%");
		
		JButton btnRollThemDice = new JButton("ROLL THAT DIE! (Act)");
		btnRollThemDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Act().execute(player);
			}
		});
		
		JButton btnRehearse = new JButton("Rehearse");
		btnRehearse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Rehearse().execute(player);
				setVisible(false);
			}
		});
		if((player.getRehearsalTokens() + 1) == player.getCurrentRoom().getCard().getBudget())
		{
			btnRehearse.setEnabled(false);
		}
		
		JLabel lblRollAmount = new JLabel("You need to roll a " + player.getCurrentRoom().getCard().getBudget() + " or higher.");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPlayer)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPlayerName))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(112)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblScene)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblSceneName))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblRole)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblRoleName))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblBudget)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblSceneBudget))))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRehearsalBonus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRehearsalTokens))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblChanceOfSuccessful)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPercentChance)))
					.addContainerGap(146, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(136, Short.MAX_VALUE)
					.addComponent(lblRollAmount)
					.addGap(126))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(97, Short.MAX_VALUE)
					.addComponent(btnRollThemDice)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRehearse)
					.addGap(86))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer)
						.addComponent(lblPlayerName)
						.addComponent(lblRole)
						.addComponent(lblRoleName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScene)
						.addComponent(lblSceneName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBudget)
						.addComponent(lblSceneBudget))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRehearsalBonus)
						.addComponent(lblRehearsalTokens))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChanceOfSuccessful)
						.addComponent(lblPercentChance))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(lblRollAmount)
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRollThemDice)
						.addComponent(btnRehearse))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
