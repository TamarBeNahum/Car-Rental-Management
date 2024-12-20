package src;


import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * 
 * @author Tamar Ben Nahum
 *
 */
public class ShowAllBranch extends JFrame {

	private JTextArea BranchArea;
	private JButton backbtnBut;
	private RuppinRent ruppin;

	public ShowAllBranch(RuppinRent Ruppin) {
		this.ruppin = Ruppin;
		this.BranchArea = new JTextArea();
		BranchArea.setText(ruppin.showAllBranch());

		this.backbtnBut = new JButton("Back");

		backbtnBut.addActionListener((a) ->

		{
			UserMenu uMenu = new UserMenu(ruppin);
			uMenu.setVisible(true);
			dispose();
		});

		initialize();
	}

	// מעדכן כל פעם את הטקסט של פרטי הסניפים
	public void updateTextArea() {
		BranchArea.setText(ruppin.showAllBranch());
	}

	private void initialize() {
		setLayout(new GridLayout(0, 1));
		add(BranchArea);
		BranchArea.setEditable(false);
		add(backbtnBut);
		
		pack();
	}

}
