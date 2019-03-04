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

public class TakeRoleDialog extends JDialog
{

	private RoleSpace choice;
	private RoleType thisRoleType;
	/**
	 * Create the dialog.
	 * @param starringRoles 
	 * @param extraRoles 
	 */
	public TakeRoleDialog(List<RoleSpace> extraRoles, List<RoleSpace> starringRoles)
	{
		setBounds(100, 100, 450, 300);
		setSize(450,300);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{445, 0};
		gridBagLayout.rowHeights = new int[]{35, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			{
				JLabel lblTheseAreThe = new JLabel("These are the roles available in this room:");
				panel.add(lblTheseAreThe);
			}
		}
		{
			JPanel buttonPane1 = new JPanel();
			GridBagConstraints gbc_buttonPane1 = new GridBagConstraints();
			gbc_buttonPane1.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane1.insets = new Insets(0, 0, 5, 0);
			gbc_buttonPane1.gridx = 0;
			gbc_buttonPane1.gridy = 1;
			getContentPane().add(buttonPane1, gbc_buttonPane1);
			GridBagLayout gbl_buttonPane1 = new GridBagLayout();
			gbl_buttonPane1.columnWidths = new int[]{432, 0};
			gbl_buttonPane1.rowHeights = new int[]{45, 45, 0};
			gbl_buttonPane1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_buttonPane1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			buttonPane1.setLayout(gbl_buttonPane1);
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.anchor = GridBagConstraints.NORTH;
				gbc_panel.fill = GridBagConstraints.HORIZONTAL;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 0;
				buttonPane1.add(panel, gbc_panel);
				{
					
						JLabel lblExtraRoles = new JLabel("Extra Roles");
						panel.add(lblExtraRoles);
					
				}
			}
			{
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 1;
				buttonPane1.add(panel, gbc_panel);
				{
					int i = 0;
					for (RoleSpace role : extraRoles)
					{
						panel.add(makeNewButton(role, RoleType.Extra));
						i++;
					}
					if (i > 3)
					{
						this.setSize(630, this.getHeight());
					}
				}
			}
		}
		
		JPanel buttonPane2 = new JPanel();
		GridBagConstraints gbc_buttonPane2 = new GridBagConstraints();
		gbc_buttonPane2.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane2.anchor = GridBagConstraints.SOUTH;
		gbc_buttonPane2.gridx = 0;
		gbc_buttonPane2.gridy = 2;
		getContentPane().add(buttonPane2, gbc_buttonPane2);
		GridBagLayout gbl_buttonPane2 = new GridBagLayout();
		gbl_buttonPane2.columnWidths = new int[]{0, 0};
		gbl_buttonPane2.rowHeights = new int[]{53, 54, 0};
		gbl_buttonPane2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonPane2.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		buttonPane2.setLayout(gbl_buttonPane2);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			buttonPane2.add(panel, gbc_panel);
			{
				JLabel lblStarringRoles = new JLabel("Starring Roles");
				panel.add(lblStarringRoles);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			buttonPane2.add(panel, gbc_panel);
			{
				for (RoleSpace role : starringRoles)
				{
					panel.add(makeNewButton(role, RoleType.Star));
				}
			}
		}
	}
	
	public JButton makeNewButton(RoleSpace role, RoleType roleType)
	{
		JButton button = new JButton(role.getRoleName());
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				choice = role;
				thisRoleType = roleType;
				setVisible(false);
			}
		});
		return button;
	}
	
	public RoleSpace getChoice()
	{
		return this.choice;
	}
	
	public RoleType getRoleType()
	{
		return thisRoleType;
	}
}
