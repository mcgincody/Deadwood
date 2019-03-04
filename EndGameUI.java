import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class EndGameUI extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	EndGameUI(Player Winner){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel winner = new JLabel("The winner is " + Winner.getName());
		winner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(winner);
		setSize((int) getPreferredSize().getWidth() + 20 ,136);
		setLocationRelativeTo(null);
	}
}