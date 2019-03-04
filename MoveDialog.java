import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MoveDialog extends JDialog
{
	private Room choice;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MoveDialog(List<Room> rooms)
	{
		this.setTitle(Main.getCurrentGame().getTurnModerator().getActivePlayer().getName() + " is moving...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblClickOneOf = new JLabel("Select one of the adjacent rooms to move to.");
		contentPanel.add(lblClickOneOf);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				for (Room room : rooms)
				{
					
					buttonPane.add(makeNewButton(room.getRoomName(), room));
				}
				
			}
			setSize(450,136);
			setLocationRelativeTo(null);
		}
	}

	public JButton makeNewButton(String name, Room room)
	{
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				choice = room;
				setVisible(false);
			}
		});
		return button;
	}

	public Room getChoice()
	{
		return this.choice;
	}

}
