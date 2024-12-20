package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Tamar Ben Nahum
 *
 */
public class SignIn extends JFrame {

	private JTextField email;
	private JPasswordField password;
	private JButton createUser;
	private JButton sdmitBth;
	private RuppinRent ruppin;
	private JLabel errorJLabel;

	public SignIn(RuppinRent Ruppin) {
		super();
		this.ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.email = new JTextField(20);
		this.password = new JPasswordField();
		this.createUser = new JButton("<html> Haven't an account? <br/> Create new account!</html>");
		this.sdmitBth = new JButton("Enter");

		sdmitBth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User adminUser = ruppin.getManager();
				if (email.getText().equals(adminUser.getEmail())
						&& adminUser.checkPassword(String.valueOf(password.getPassword()))) {
					AdminMenu managerMenu = new AdminMenu(ruppin);
					managerMenu.setVisible(true);
					dispose();
					// clear fields
					email.setText("");
					password.setText("");
				} else {

					if (ruppin.CheckUser(email.getText(), String.valueOf(password.getPassword()))) {
						UserMenu userMenu = new UserMenu(ruppin);
						userMenu.setVisible(true);
						dispose();
						// clear fields
						email.setText("");
						password.setText("");
					} else {
						// JOptionPane.showMessageDialog(null, "Email / Password is incorrect");
						errorJLabel.setText("<html>Email / Password is incorrect</html>");
						errorJLabel.setVisible(true);}
				}
			}
		});

		createUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp(ruppin);
				signUp.setVisible(true);
				dispose();

			}

		});

		Initialize();

	}

	private void Initialize() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("Email: "));
		add(email);
		add(new JLabel("Password: "));
		add(password);
		add(createUser);
		add(sdmitBth);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		// setSize(500, 300);
		pack();
	}
}