package src;

import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

/**
 * 
 * @author Tamar Ben Nahum
 *
 */
public class SignUp extends JFrame {

	private JTextField fName;
	private JTextField lName;
	private JTextField IdUser;
	private JTextField email;
	private Choice day;
	private Choice month;
	private Choice year;
	private JTextField dateLicense;
	private JTextField password;
	private JTextField Passwordverification;
	private JButton backBth;
	private RuppinRent ruppin;
	private JButton addUsers;
	private JLabel errorJLabel;

	public SignUp(RuppinRent Ruppin) {
		this.ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.fName = new JTextField(10);
		this.lName = new JTextField(10);
		this.IdUser = new JTextField(9);
		this.email = new JTextField(20);
		this.day = new Choice();
		this.month = new Choice();
		this.year = new Choice();
		this.dateLicense = new JTextField(10);// שנת הוצאת רישיון
		this.password = new JTextField(20);
		this.Passwordverification = new JTextField(20);
		this.backBth = new JButton("BACK");
		this.addUsers = new JButton("ADD USER");

		addUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// check if user id is empty
				if (IdUser.getText().isEmpty() || fName.getText().isEmpty() || lName.getText().isEmpty()
						|| email.getText().isEmpty() || dateLicense.getText().isEmpty() || password.getText().isEmpty()
						|| Passwordverification.getText().isEmpty()) {
					// show to screen message
					// JOptionPane.showMessageDialog(null, "The fields can not be empty, please fill
					// them.");
					errorJLabel.setText("<html>The fields can not be empty, please fill them.</html>");
					return;
				}
				// בודק האם המייל מכיל @
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				Pattern pat = Pattern.compile(emailRegex);
				if (!(pat.matcher(email.getText()).matches())) {
					// JOptionPane.showMessageDialog(null, "email must be in correct format
					// ('example@email.com')");
					errorJLabel.setText("<html>email must be in correct format ('example@email.com').</html>");
					return;
				}

				if (!(IdUser.getText().length() == 9)) {
					// JOptionPane.showMessageDialog(null, "ID must contain 9 numbers only");
					errorJLabel.setText("<html>ID must contain 9 numbers only</html>");
					return;
				}

				// try and catch of input from the user
				try {
					String firstName = fName.getText();
					String lastName = lName.getText();
					int idUser = Integer.parseInt(IdUser.getText());
					String userEmail = email.getText();
					String birthDayUser = Integer.parseInt(day.getSelectedItem()) + "/"
							+ Integer.parseInt(month.getSelectedItem()) + "/"
							+ Integer.parseInt(year.getSelectedItem());
					int userLicenseYear = Integer.parseInt(dateLicense.getText());
					if (!(userLicenseYear >= 1950 && userLicenseYear <= 2023)) {
						// JOptionPane.showMessageDialog(null, "User license year must be between 1950
						// to 2023");
						errorJLabel.setText("<html>User license year must be between 1950 to 2023</html>");
						return;
					}
					String passwordUser = password.getText();
					String passwordVerUser = Passwordverification.getText();

					// check if the email equals to the new email that user want to added
					if (passwordUser.equals(passwordVerUser)) {
						User userInfo = new User(firstName, lastName, idUser, userEmail, birthDayUser, userLicenseYear,
								passwordUser);
						// add to user to the system
						if (ruppin.addNewUser(email.getText(), userInfo)) {
							JOptionPane.showMessageDialog(null, "user added");
							//errorJLabel.setText("user added!");

							// ShowUser showUser=new ShowUser(ruppin);
							// showUser.setVisible(true);

							// switch to sign in page
							SignIn sign_in = new SignIn(ruppin);
							sign_in.setVisible(true);
							// write file
							WriteRuppinRent.SaveToFlie(ruppin);
							dispose();
						} else {
							// JOptionPane.showMessageDialog(null, "The user already exists in the system");
							errorJLabel.setText("<html>The user already exists in the system</html>");
							errorJLabel.setVisible(true);
						}
					}

					// check if the Password doesn't match
					else {
						// JOptionPane.showMessageDialog(null, "Password doesn't match");
						errorJLabel.setText("<html>Password doesn't match</html>");
						errorJLabel.setVisible(true);
					}
				} catch (NumberFormatException e2) {
					// JOptionPane.showMessageDialog(null,
					// "<html>user id and year license driver<br>must be numbers</html>");
					errorJLabel.setText("<html>user id and year license driver<br>must be numbers</html>");
					errorJLabel.setVisible(true);
				}
			}

		});

		backBth.addActionListener((a) ->

		{
			SignIn signIn = new SignIn(ruppin);
			signIn.setVisible(true);
			signIn.getContentPane().setBackground(Color.pink);
			dispose();
		});

		Initialize();
		initializeChoice();

	}

	/**
	 * function add day,month and year
	 */
	private void initializeChoice() {

		for (Integer i = 1; i <= 12; i++) {
			month.add(Integer.toString(i));
		}
		for (Integer i = 1; i <= 31; i++) {
			day.add(Integer.toString(i));
		}
		for (Integer i = 1950; i <= 2023; i++) {
			year.add(Integer.toString(i));
		}
	}

	private JPanel addP() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(day);
		panel.add(month);
		panel.add(year);
		return panel;
	}

	void Initialize() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("First Name: "));
		add(fName);
		add(new JLabel("Last Name: "));
		add(lName);
		add(new JLabel("user id:"));
		add(IdUser);
		add(new JLabel("Email: "));
		add(email);
		add(new JLabel("User BirthDay Year: "));
		add(addP());
		add(new JLabel("User License Year: "));
		add(dateLicense);
		add(new JLabel("Password: "));
		add(password);
		add(new JLabel("Password Verification: "));
		add(Passwordverification);
		getContentPane().setBackground(Color.pink);
		add(backBth);
		add(addUsers);
		addUsers.setBackground(Color.yellow);
		backBth.setBackground(Color.CYAN);
		// setSize(200, 200);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		pack();
	}

}
