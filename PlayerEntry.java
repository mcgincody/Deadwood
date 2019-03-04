import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerEntry extends JDialog
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private String[] comboColors = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple","Pink"};
	private JComboBox comboBox_2;
	private JLabel lblPlayerName_2;
	private JButton btnReady;
	private JComponent[] objects = new JComponent[24];

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public PlayerEntry()
	{
		setModal(true);
		setResizable(false);
		setTitle("Player Entry");
		setSize(389,440);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 0;
		getContentPane().add(panel_10, gbc_panel_10);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players:");
		panel_10.add(lblNumberOfPlayers);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				int i;
				for(i = 0; i < Integer.parseInt((String) comboBox_8.getItemAt(comboBox_8.getSelectedIndex()))*4-8; i+=4)
				{
					objects[i].setEnabled(true);
					objects[i+1].setEnabled(true);
					objects[i+2].setForeground(Color.BLACK);
					objects[i+3].setForeground(Color.BLACK);
				}
				
				for (i += 0; i < objects.length; i+=4)
				{
					objects[i].setEnabled(false);
					objects[i+1].setEnabled(false);
					objects[i+2].setForeground(Color.GRAY);
					objects[i+3].setForeground(Color.GRAY);
				}
			}
		});
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8"}));
		panel_10.add(comboBox_8);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		JLabel lblPlayerName = new JLabel("Player 1 Name:");
		panel.add(lblPlayerName);
		
		textField = new JTextField();
		
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblColor = new JLabel("Color:");
		panel.add(lblColor);

		JComboBox comboBox = new JComboBox(comboColors);
		comboBox.setSelectedIndex(-1);
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblPlayerName_1 = new JLabel("Player 2 Name:");
		panel_1.add(lblPlayerName_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1.add(textField_1);
		
		JLabel label_1 = new JLabel("Color:");
		panel_1.add(label_1);
		
		JComboBox comboBox_1 = new JComboBox(comboColors);
		comboBox_1.setSelectedIndex(-1);
		panel_1.add(comboBox_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		getContentPane().add(panel_2, gbc_panel_2);
		
		lblPlayerName_2 = new JLabel("Player 3 Name:");
		lblPlayerName_2.setForeground(Color.GRAY);
		panel_2.add(lblPlayerName_2);
		
		textField_2 = new JTextField();
		objects[0] = textField_2; 
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		panel_2.add(textField_2);
		
		JLabel label_3 = new JLabel("Color:");
		panel_2.add(label_3);
		
		comboBox_2 = new JComboBox(comboColors);
		objects[1] = comboBox_2; 
		comboBox_2.setEnabled(false);
		comboBox_2.setSelectedIndex(-1);
		panel_2.add(comboBox_2);
		objects[2] = lblPlayerName_2;
		objects[3] = label_3;
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		getContentPane().add(panel_3, gbc_panel_3);
		
		JLabel lblPlayerName_3 = new JLabel("Player 4 Name:");
		lblPlayerName_3.setForeground(Color.GRAY);
		panel_3.add(lblPlayerName_3);
		
		textField_3 = new JTextField();
		objects[4] = textField_3;
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		panel_3.add(textField_3);
		
		JLabel label_5 = new JLabel("Color:");
		panel_3.add(label_5);
		
		JComboBox comboBox_3 = new JComboBox(comboColors);
		objects[5] = comboBox_3;
		comboBox_3.setEnabled(false);
		comboBox_3.setSelectedIndex(-1);
		panel_3.add(comboBox_3);
		objects[6] = lblPlayerName_3;
		objects[7] = label_5;
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 5;
		getContentPane().add(panel_4, gbc_panel_4);
		
		JLabel lblPlayerName_4 = new JLabel("Player 5 Name:");
		lblPlayerName_4.setForeground(Color.GRAY);
		panel_4.add(lblPlayerName_4);
		
		textField_4 = new JTextField();
		objects[8] = textField_4;
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		panel_4.add(textField_4);
		
		JLabel label_7 = new JLabel("Color:");
		panel_4.add(label_7);
		
		JComboBox comboBox_4 = new JComboBox(comboColors);
		objects[9] = comboBox_4;
		comboBox_4.setEnabled(false);
		comboBox_4.setSelectedIndex(-1);
		panel_4.add(comboBox_4);
		objects[10] = lblPlayerName_4;
		objects[11] = label_7;
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 6;
		getContentPane().add(panel_5, gbc_panel_5);
		
		JLabel lblPlayerName_5 = new JLabel("Player 6 Name:");
		lblPlayerName_5.setForeground(Color.GRAY);
		panel_5.add(lblPlayerName_5);
		
		textField_5 = new JTextField();
		objects[12] = textField_5;
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		panel_5.add(textField_5);
		
		JLabel label_9 = new JLabel("Color:");
		panel_5.add(label_9);
		
		JComboBox comboBox_5 = new JComboBox(comboColors);
		objects[13] = comboBox_5;
		comboBox_5.setEnabled(false);
		comboBox_5.setSelectedIndex(-1);
		panel_5.add(comboBox_5);
		objects[14] = lblPlayerName_5;
		objects[15] = label_9;
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 7;
		getContentPane().add(panel_6, gbc_panel_6);
		
		JLabel lblPlayerName_6 = new JLabel("Player 7 Name:");
		lblPlayerName_6.setForeground(Color.GRAY);
		panel_6.add(lblPlayerName_6);
		
		textField_6 = new JTextField();
		objects[16] = textField_6;
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		panel_6.add(textField_6);
		
		JLabel label_11 = new JLabel("Color:");
		panel_6.add(label_11);
		
		JComboBox comboBox_6 = new JComboBox(comboColors);
		objects[17] = comboBox_6;
		comboBox_6.setEnabled(false);
		comboBox_6.setSelectedIndex(-1);
		panel_6.add(comboBox_6);
		objects[18] = lblPlayerName_6;
		objects[19] = label_11;
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 8;
		getContentPane().add(panel_7, gbc_panel_7);
		
		JLabel lblPlayerName_7 = new JLabel("Player 8 Name:");
		lblPlayerName_7.setForeground(Color.GRAY);
		panel_7.add(lblPlayerName_7);
		
		textField_7 = new JTextField();
		objects[20] = textField_7;
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		panel_7.add(textField_7);
		
		JLabel label_13 = new JLabel("Color:");
		panel_7.add(label_13);
		
		JComboBox comboBox_7 = new JComboBox(comboColors);
		objects[21] = comboBox_7;
		comboBox_7.setEnabled(false);
		comboBox_7.setSelectedIndex(-1);
		panel_7.add(comboBox_7);
		objects[22] = lblPlayerName_7;
		objects[23] = label_13;
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.weighty = 1.0;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 9;
		getContentPane().add(panel_9, gbc_panel_9);
		
		JLabel lblClickTheready = new JLabel("Click the \"Ready\" button when done entering names and colors.");
		panel_9.add(lblClickTheready);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 10;
		getContentPane().add(panel_8, gbc_panel_8);
		
		btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean firstTwoNamesEntereed = !textField.getText().isEmpty() && !textField_1.getText().isEmpty();
				boolean firstTwoColorsSelected = comboBox.getSelectedIndex() > -1 && comboBox_1.getSelectedIndex() > -1;
				List<String> players = new ArrayList<String>();
				int[] colors = new int[comboBox_8.getSelectedIndex()+2];
				if (firstTwoNamesEntereed && firstTwoColorsSelected)
				{
					players.add(textField.getText());
					players.add(textField_1.getText());
					colors[0] = comboBox.getSelectedIndex();
					colors[1] = comboBox_1.getSelectedIndex();
					for (int i = 0; i < comboBox_8.getSelectedIndex() * 4; i += 4)
					{
						String tempName = ((JTextField) objects[i]).getText();
						int tempColor = ((JComboBox) objects[i+1]).getSelectedIndex();
						if(tempName.isEmpty() || tempColor < 0)
						{
							lblClickTheready.setText("WARNING: Not every name has been entered.");
							lblClickTheready.setForeground(Color.RED);
							return;
						}
						players.add(tempName);
						colors[(i/4)+2] = tempColor;
					}
					Set set = new HashSet<Integer>();
					for(int i = 0; i < colors.length; i++)
					{
						set.add(colors[i]);
					}
					if(set.size() < colors.length){
						lblClickTheready.setText("WARNING: Each player must use a unique color.");
						lblClickTheready.setForeground(Color.RED);
						return;
					}
					set = new HashSet<String>(players);
					if(set.size() < players.size()){
						lblClickTheready.setText("WARNING: Each player must use a unique name.");
						lblClickTheready.setForeground(Color.RED);
						return;
					}
				}
				else
				{
					if (!firstTwoNamesEntereed)
					{
						lblClickTheready.setText("WARNING: Not every name has been entered.");
						lblClickTheready.setForeground(Color.RED);
						return;
					}
					else
					{
						lblClickTheready.setText("WARNING: Not everyone has picked a color.");
						lblClickTheready.setForeground(Color.RED);
						return;
					}
				}
				dispose();
				Main.getCurrentGame().addPlayersAndSetSpecialRules(players, colors);
			}
		});
		panel_8.add(btnReady);
	}
}
