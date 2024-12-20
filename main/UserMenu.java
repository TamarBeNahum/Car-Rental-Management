package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 *   @author Tamar Ben Nahum
 *
 */
public class UserMenu extends JFrame {
	private JButton showAllBranch;
	private JButton ShowRentCar;
	private JButton backbtnBut;
	private RuppinRent ruppin;
	private JLabel errorJLabel;

	public UserMenu(RuppinRent Ruppin) {
		this.ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.showAllBranch = new JButton("SHOW BRANCH");
		this.ShowRentCar = new JButton("SHOW RENT CAR");
		this.backbtnBut = new JButton("BACK");
		
		// back to sing in window
		backbtnBut.addActionListener((a) ->

		{
			SignIn signIn = new SignIn(ruppin);
			signIn.setVisible(true);
			signIn.getContentPane().setBackground(Color.ORANGE);

			dispose();
		});
		
		// show all branches in new window
		showAllBranch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ruppin.showAllBranch().length() == 0) {
					//JOptionPane.showMessageDialog(null, "No Branches to show");
					errorJLabel.setText("<html>No Branches to show.</html>");
					errorJLabel.setVisible(true);
					return;
				}
				ShowAllBranch sAllBranch = new ShowAllBranch(Ruppin);
				sAllBranch.setVisible(true);
				dispose();
			}
		});
		// show all car in new window
		ShowRentCar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ruppin.getAllCars().size() == 0) {
					//JOptionPane.showMessageDialog(null, "No Cars to show");
					errorJLabel.setText("<html>No cars to show.</html>");
					errorJLabel.setVisible(true);
					return;
				}
				ShowRentCar showRentCar = new ShowRentCar(Ruppin);
				showRentCar.setVisible(true);
				//showRentCar.getContentPane().setBackground(Color.ORANGE);
				dispose();
			}
		});

		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		add(showAllBranch, BorderLayout.WEST);
		add(ShowRentCar, BorderLayout.CENTER);
		add(backbtnBut, BorderLayout.EAST);
		showAllBranch.setBackground(Color.pink);
		ShowRentCar.setBackground(Color.CYAN);
		backbtnBut.setBackground(Color.yellow);
		add(errorJLabel,BorderLayout.SOUTH);
		errorJLabel.setVisible(false);
		setSize(400, 300);
		// pack();
	}

}
