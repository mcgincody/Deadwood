import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class TurnChoiceDialog extends JDialog
{
	private TurnAction choice = null;
	private final JPanel contentPanel = new JPanel();

	public TurnChoiceDialog(List<TurnAction> actions, String playerName)
	{
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblItsIsNow = new JLabel("It is now " + playerName + "'s turn. Select an action below.");
		lblItsIsNow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblItsIsNow);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			for(TurnAction action : actions)
			{
				StringBuilder buttonName = new StringBuilder();
				String actionName = action.getClass().getName().toString();
				for (int i = 0; i < actionName.length()-1; i++)
				{
					buttonName.append(actionName.charAt(i));
					if (Character.isLowerCase(actionName.charAt(i)) && Character.isUpperCase(actionName.charAt(i+1)))
					{
						buttonName.append(" ");
					}
				}
				buttonName.append(actionName.charAt(actionName.length()-1));
				buttonPane.add(makeNewButton(buttonName.toString(), action));
			}
		}
		setSize((int) getPreferredSize().getWidth() + 20 ,136);
		setLocationRelativeTo(null);
	}
	public JButton makeNewButton(String name, TurnAction action)
	{
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				choice = action;
				setVisible(false);
			}
		});
		return button;
	}
	public TurnAction getChoice ()
	{
		return this.choice;
	}

}
