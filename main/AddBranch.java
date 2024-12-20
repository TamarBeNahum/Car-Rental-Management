package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *  @author Rahav Shema and Tamar Ben Nahum
 * 
 *
 */
public class AddBranch extends JFrame {
	private RuppinRent ruppin;
	private JButton backbtn;
	private JTextField branchNumber;
	private JTextField branchLocation;
	private JButton addBranchbtn;
	private JLabel errorJLabel;

	public AddBranch(RuppinRent Ruppin) {
		super();
		this.ruppin=Ruppin;
		this.errorJLabel = new JLabel();
		this.addBranchbtn=new JButton("<html>Add new Branch</html>");
		this.backbtn=new JButton("Back");
		this.branchLocation=new JTextField(20);
		this.branchNumber=new JTextField(20);
		//חוזרים לתפריט של המנהל 
		backbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminMenu adminMenu = new AdminMenu(ruppin);
				adminMenu.setVisible(true);
				dispose();
				
			}
		});
		
	
		addBranchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int branchN;
				String loctionBranch;
				try {
					branchN=Integer.parseInt(branchNumber.getText());
					loctionBranch=branchLocation.getText();
					if(loctionBranch.isEmpty()) {
						//JOptionPane.showMessageDialog(null, "<html>Branch loction can not be empty,<br>"
							//	+ " Please fill in the fields </html>");
					
						errorJLabel.setText("<html>Branch loction can not be empty,<br> Please fill in the fields </html>");
						errorJLabel.setVisible(true);
						return;	
						}
					}
					
					catch (NumberFormatException e2) {
						//JOptionPane.showMessageDialog(null, "Branch number must contain only numbers");
						errorJLabel.setText("<html>Branch number must contain only numbers </html>");
						errorJLabel.setVisible(true);
						return;
					}
				catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, "<html>Branch number can not be empty,<br> please fill it</html>");
					errorJLabel.setText("<html>Branch number can not be empty,<br> please fill it</html>");
					errorJLabel.setVisible(true);
					
					return;
				}
				
				if(ruppin.addBranch(branchN, new Branch(branchN,loctionBranch))){
					//JOptionPane.showMessageDialog(null, "The branch was added");
					errorJLabel.setText("<html>The branch was added</html>");
					errorJLabel.setVisible(true);
					
					//clear filed
					branchNumber.setText("");
					branchLocation.setText("");
				}
				
				else {
					//JOptionPane.showMessageDialog(null, "The branch has already exists");
					errorJLabel.setText("<html>The branch has already exists</html>");
					errorJLabel.setVisible(true);
				}
			}

	});
		initialize();

	}

	private void initialize() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("Branch Number:"));
		add(branchNumber);
		add(new JLabel("Branch Location:"));
		add(branchLocation);
		add(addBranchbtn);
		add(backbtn);
		//setSize(200,200);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		pack();
	}

}
