import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomeDialog extends JDialog
{
	boolean answer;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public WelcomeDialog()
	{
		setTitle("Welcome!");
		setSize(436,191);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToDeadwood = new JLabel("<html><center>Welcome to Deadwood Studios!<br>Would like to play a game?</center></html>", SwingConstants.CENTER);
		lblWelcomeToDeadwood.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToDeadwood.setFont(new Font("Tahoma", Font.PLAIN, 28));
		getContentPane().add(lblWelcomeToDeadwood, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 20);
		panel.setLayout(fl_panel);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setModal(false);
				answer = true;
				setVisible(false);
			}
		});
		panel.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModal(false);
				answer = false;
				setVisible(false);
			}
		});
		panel.add(btnNo);
	}
	
	public boolean getAnswer()
	{
		return answer;
	}
}
