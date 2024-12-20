package main;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *  * @author Rahav Shema and Tamar Ben Nahum
 *
 */
public class ShowRentCar extends JFrame {

	private JButton backbtnBut;
	private RuppinRent ruppin;
	private Choice searchBranchOfCar;
	private Choice searchModelOfCar;
	private Choice searchCaterory;
	private Choice searchYear;
	private Choice searchPrice;
	private Choice searchIsAutoCar;
	private Choice dateToPickCarFromBranch;
	private JButton searchbtn;
	private JLabel errorJLabel;

	public ShowRentCar(RuppinRent Ruppin) {
		super();
		ruppin = Ruppin;
		this.errorJLabel = new JLabel();
		this.searchBranchOfCar = new Choice();
		this.searchModelOfCar = new Choice();
		this.searchCaterory = new Choice();
		this.searchYear = new Choice();
		this.searchPrice = new Choice();
		this.searchIsAutoCar = new Choice();
		this.dateToPickCarFromBranch = new Choice();
		this.searchbtn = new JButton("Search Car");
		this.backbtnBut = new JButton("Back to Menu");

		backbtnBut.addActionListener((a) ->

		{
			UserMenu uMenu = new UserMenu(ruppin);
			uMenu.setVisible(true);
			dispose();
		});

		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Car> carMatchingSelectionUser = new ArrayList<>(); // כל הרכבים שמתאימים לבחירה של המשתמש
				String branchString = searchBranchOfCar.getSelectedItem();
				String model = searchModelOfCar.getSelectedItem();
				String caterory = searchCaterory.getSelectedItem();
				String year = searchYear.getSelectedItem();
				String price = searchPrice.getSelectedItem();
				String isautocar = searchIsAutoCar.getSelectedItem();
				String datePickCar = dateToPickCarFromBranch.getSelectedItem();

				Car selectedCarByUserCar = null;
				// בדיקת כל הרכבים במערכת והוספה של מי שמתאים לבחירת המשתמש למערך ההתאמות
				for (Car c : ruppin.getAllCars()) {
					if (c.isVehicleAvailable()) {
						if (branchString.equals("None") || c.getBranchOfCar() == Integer.parseInt(branchString)) {
							if (model.equals("None") || c.getModel().equals(model)) {
								if (caterory.equals("None") || c.getCategory().toString().equals(caterory)) {
									if (year.equals("None") || c.getManufactureYear() == Integer.parseInt(year)) {
										if (price.equals("None")
												|| c.getRentalPricePerDay() <= Integer.parseInt(price)) {
											if (isautocar.equals("None")
													|| (c.isAuto() == false && isautocar.equals("Automatic"))
													|| (c.isAuto() == true && isautocar.equals("Manual"))) {
												carMatchingSelectionUser.add(c);
											}
										}
									}
								}
							}
						}
					}
				}
				if (carMatchingSelectionUser.size() > 0) {
					ShowMatchingCars matchingCars = new ShowMatchingCars(ruppin, carMatchingSelectionUser);
					matchingCars.setVisible(true);
					dispose();
				} else {
					// display error message
					//JOptionPane.showMessageDialog(null, "No car was found that matches what you selected.");
					errorJLabel.setText("<html>No car was found that matches what you selected.</html>");
					errorJLabel.setVisible(true);
				}

			}
		});

		IntializeChoice();

		Initialize();

	}

	public void IntializeChoice() {
		// הוספת שנים לחיפוש שנת ייצור של רכב
		searchYear.add("None");// default values
		for (int i = 1950; i <= 2023; i++) {
			searchYear.add(Integer.toString(i));
		}

		// add category
		searchCaterory.add("None");// default values
		for (utils.E_CategoryStatus categoryStatus : utils.E_CategoryStatus.values()) {
			searchCaterory.add(categoryStatus.toString());
		}

		// add is car automatic or manual
		searchIsAutoCar.add("None");// default values
		searchIsAutoCar.add("Automatic");// אוטומטית
		searchIsAutoCar.add("Manual");// יידני

		// add Price
		searchPrice.add("None");// default values
		for (int i = 100; i <= 10000; i += 100) {
			searchPrice.add(Integer.toString(i));
		}

		// add Model car
		searchModelOfCar.add("None");// default values
		if (ruppin.getAllModelCar() != null) {
			for (String modelCarString : ruppin.getAllModelCar()) {
				searchModelOfCar.add(modelCarString);
			}
		}
		// add branch
		searchBranchOfCar.add("None");// default values
		if (ruppin.getBranchNumbers() != null) {
			for (String branchNumber : ruppin.getBranchNumbers()) {
				searchBranchOfCar.add(branchNumber);
			}
		}
		// add date pick the car from branch
		// Get current date
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();

		// Create an array to store the date strings
		String[] dateArray = new String[365];

		// Create a SimpleDateFormat object to format the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Loop for one year from now
		for (int i = 0; i < 365; i++) {
			// Add the current date to the array as a string
			dateArray[i] = dateFormat.format(currentDate);
			// Increment the current date by one day
			calendar.add(Calendar.DATE, 1);
			currentDate = calendar.getTime();
		}

		// Print the date array
		for (String date : dateArray) {
			dateToPickCarFromBranch.add(date);// נוסיף את התאריכים
		}

	}

	public void Initialize() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("Search by Car Branch Number:"));
		add(searchBranchOfCar);
		add(new JLabel("Search by Car model:"));
		add(searchModelOfCar);
		add(new JLabel("Search by Car Category:"));
		add(searchCaterory);
		add(new JLabel("Search by Car Year:"));
		add(searchYear);
		add(new JLabel("Search by Car Price:"));
		add(searchPrice);
		add(new JLabel("Search by Car Gearbox:"));
		add(searchIsAutoCar);
		add(new JLabel("Select Date to Pick up Car:"));
		add(dateToPickCarFromBranch);
		add(searchbtn);
		add(backbtnBut);
		searchbtn.setBackground(Color.pink);
		backbtnBut.setBackground(Color.yellow);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		pack();

	}

}
