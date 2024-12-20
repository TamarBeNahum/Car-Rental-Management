package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Admin menu
 * @author Tamar Ben Nahum
 *
 */
public class AdminMenu extends JFrame {

	private JButton addNewBranch;
	private JButton addNewCar;
	private JButton backbtn;
	private RuppinRent ruppin;
	private JLabel errorJLabel;

	public AdminMenu(RuppinRent Ruppin) {
		this.ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.addNewBranch = new JButton("ADD NEW BRANCH");
		this.addNewCar = new JButton("ADD NEW CAR");
		this.backbtn = new JButton("BACK TO MENU");

		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SignIn signIn = new SignIn(ruppin);
				signIn.setVisible(true);
				dispose();

			}
		});

		addNewBranch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddBranch addBranch = new AddBranch(ruppin);
				addBranch.setVisible(true);
				dispose();

			}
		});
		
		addNewCar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ruppin.countBranch() > 0) {
					AddCar addCar = new AddCar(ruppin);
					addCar.setVisible(true);
					dispose();
				} else {
					//JOptionPane.showMessageDialog(null, "You must add branches first before adding a new vehicle ");
					errorJLabel.setText("<html>You must add branches first before adding a new vehicle.</html>");
					errorJLabel.setVisible(true);
					return;
				}
			}
		});
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		add(addNewBranch, BorderLayout.WEST);
		add(addNewCar, BorderLayout.CENTER);
		add(backbtn, BorderLayout.EAST);
		addNewBranch.setBackground(Color.pink);
		addNewCar.setBackground(Color.CYAN);
		backbtn.setBackground(Color.yellow);
		add(errorJLabel,BorderLayout.SOUTH);
		errorJLabel.setVisible(false);
		setSize(500,300);
		//pack();
	}
}
