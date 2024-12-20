package src;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * class add car
 * 
 * @author Tamar Ben Nahum
 *
 */
public class AddCar extends JFrame {

	private RuppinRent ruppin;
	private JTextField licenceNumber;
	private Choice manufactureYear;
	private JTextField modelCar;
	private JTextField subModelCar;
	private Choice categoryCar;
	private Choice carIsAuto;
	private Choice branchToAdd;
	private Choice priceToRentCar;
	private JButton addCarToBranch;
	private JButton backbtn;
	private JLabel errorJLabel;

	public AddCar(RuppinRent Ruppin) {
		super();
		ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.licenceNumber = new JTextField(20);
		this.modelCar = new JTextField(20);
		this.subModelCar = new JTextField(20);
		this.carIsAuto = new Choice();
		this.categoryCar = new Choice();
		this.manufactureYear = new Choice();
		this.priceToRentCar = new Choice();
		this.branchToAdd = new Choice();
		this.backbtn = new JButton("Back to Menu");
		this.addCarToBranch = new JButton("Add new Car");

		backbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdminMenu adminMenu = new AdminMenu(ruppin);
				adminMenu.setVisible(true);
				WriteRuppinRent.SaveToFlie(ruppin);
				dispose();
			}
		});

		addCarToBranch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int licenceNum;
				String model = modelCar.getText();
				String subModel = subModelCar.getText();
				try {
					licenceNum = Integer.parseInt(licenceNumber.getText());
					if (model.isEmpty() || subModel.isEmpty()) {
						// JOptionPane.showMessageDialog(null,
						// "<html>The car model / sub model fields cannot remain empty,<br>"
						// + " if you don't care what model it is, write 'None'</html>");
						errorJLabel.setText("<html>The car model / sub model fields cannot remain empty,<br>"
								+ " if you don't care what model it is, write 'None'</html>");
						errorJLabel.setVisible(true);
						return;
					}

				} catch (NumberFormatException e1) {
					//JOptionPane.showMessageDialog(null,
						//	"Car license number can not be characters, please enter a number only!");
					errorJLabel.setText("Car license number can not be characters, please enter a number only!");
					errorJLabel.setVisible(true);
					return;
				}
				// בודקת האם הבחירה של המכונית היא אוטומטית או ידנית
				Boolean boolFlagString;
				if (carIsAuto.getSelectedIndex() == 1) {
					boolFlagString = true;
				} else {
					boolFlagString = false;
				}
				if (ruppin.addCar(licenceNum,
						new Car(Integer.parseInt(manufactureYear.getSelectedItem()), model, subModel,
								utils.E_CategoryStatus.valueOf(categoryCar.getSelectedItem()), boolFlagString,
								Integer.parseInt(priceToRentCar.getSelectedItem()), licenceNum,
								ruppin.getBranch(Integer.parseInt(branchToAdd.getSelectedItem()))))) {
					//JOptionPane.showMessageDialog(null, "Car Added");
					errorJLabel.setText("Car Added");
					errorJLabel.setVisible(true);
					// clear fields
					licenceNumber.setText("");
					manufactureYear.select(0);
					modelCar.setText("");
					subModelCar.setText("");
					categoryCar.select(0);
					carIsAuto.select(0);
					branchToAdd.select(0);
					priceToRentCar.select(0);

				} else {
					//JOptionPane.showMessageDialog(null, "The branch already exists in the system");
					errorJLabel.setText("The branch already exists in the system");
					errorJLabel.setVisible(true);
				}

			}
		});

		intializeChoice();
		initialize();
	}

	private void intializeChoice() {
		// שנות ייצור רכב
		for (Integer i = 1950; i <= 2023; i++) {
			manufactureYear.add(Integer.toString(i));
		}
		// הוספת קטגוריה לרכב (סדאן, מיני)
		for (utils.E_CategoryStatus carCategory : utils.E_CategoryStatus.values()) {
			categoryCar.add(carCategory.toString());
		}
		carIsAuto.add("Automatic");// אוטומטית
		carIsAuto.add("Manual");// ידנית
		// מחיר של מכונית להשכרה לפי יום
		for (int i = 100; i <= 10000; i += 100) {
			priceToRentCar.add(Integer.toString(i));
		}

		if (ruppin.getBranchNumbers() != null) {
			for (String branchNumber : ruppin.getBranchNumbers())
				branchToAdd.add(branchNumber);
		}
	}

	private void initialize() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("Car license Number:"));
		add(licenceNumber);
		add(new JLabel("Car manufactur year:"));
		add(manufactureYear);
		add(new JLabel("Car model:"));
		add(modelCar);
		add(new JLabel("Car Sub model:"));
		add(subModelCar);
		add(new JLabel("Car category:"));
		add(categoryCar);
		add(new JLabel("Car Gearbox:"));
		add(carIsAuto);
		add(new JLabel("Car price to rent:"));
		add(priceToRentCar);
		add(new JLabel("Branch To be Added to:"));
		add(branchToAdd);
		add(addCarToBranch);
		add(backbtn);
		backbtn.setBackground(Color.yellow);
		addCarToBranch.setBackground(Color.pink);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		pack();
	}
}
