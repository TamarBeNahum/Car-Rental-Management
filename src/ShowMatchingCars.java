package src;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


/**
 * מחלקה שמקבלת רשימה של כל הרכבים שעונים על תנאי החיפש ומציגה אותם
 * 
 * @author Tamar Ben Nahum
 *
 */
public class ShowMatchingCars extends JFrame {
	// private JList<Car> listofCar;// אובייקט להצגה של רשימת הרכבים
	private Choice listofCar;
	private JTextField priceField;// מחיר
	private JButton rentcarbtn;// כפתור קניה
	private JButton backbtnBut;// כפתור חזרה לתפריט
	private RuppinRent ruppin;
	private JLabel errorJLabel;

	public ShowMatchingCars(RuppinRent Ruppin, ArrayList<Car> matchingCars) {
		//matchingCars - רשימת הרכבים שעלו מתאימים לדרישות המשתמש
		this.ruppin = Ruppin;
		this.listofCar = new Choice();
		priceField = new JTextField();
		priceField.setEditable(false);
		this.errorJLabel = new JLabel();

		listofCar.add("None");
		for (Car car : matchingCars) {
			listofCar.add(car.toString());
		}

		listofCar.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (listofCar.getSelectedItem() == "None") {
					priceField.setText("");
				} else {
					Car selectedCar = matchingCars.get(listofCar.getSelectedIndex() - 1);
					priceField.setText("Price of selected car is: " + selectedCar.getRentalPricePerDay());
				}
			}
		});

		this.rentcarbtn = new JButton("<html>Comfirm price and <br> rent Selected Car</html>");
		rentcarbtn.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				if (listofCar.getSelectedItem() == "None") {
					//JOptionPane.showMessageDialog(null, "No Car was Selected");
					errorJLabel.setText("<html>No Car was Selected.</html>");
					errorJLabel.setVisible(true);
				} else {
					Car selectedCar = matchingCars.get(listofCar.getSelectedIndex() - 1);
					if (selectedCar.isVehicleAvailable()==false) {
						//JOptionPane.showMessageDialog(null, "car already rent");
						errorJLabel.setText("car already rent");
						errorJLabel.setVisible(true);
						return;
					}									
				}
				
				Car selectedCar = matchingCars.get(listofCar.getSelectedIndex()-1);
				if (listofCar.getSelectedItem() == "None") {
					//JOptionPane.showMessageDialog(null, "No Car was Selected");
					errorJLabel.setText("<html>No Car was Selected.</html>");
					errorJLabel.setVisible(true);
					selectedCar.carGotRent();//שינוי הרכב ללא זמין
					ShowMatchingCars showMatchingCars= new ShowMatchingCars(ruppin, matchingCars);
					//showMatchingCars.setVisible(true);
					
					WriteRuppinRent.SaveToFlie(ruppin);
					//JOptionPane.showMessageDialog(null, "The car was take to rent");
					errorJLabel.setText("<html>The car was take to rent.</html>");
					errorJLabel.setVisible(true);
					dispose();
				}				
			}
		});

		this.backbtnBut = new JButton("Back to Search");
		backbtnBut.addActionListener((a) ->

		{
			ShowRentCar showRentCar = new ShowRentCar(ruppin);
			showRentCar.setVisible(true);
			WriteRuppinRent.SaveToFlie(ruppin);
			dispose();
		});

		initialize();

	}

	private void initialize() {
		setLayout(new GridLayout(0, 1));
		add(new JLabel("Cars (please select the car you want to rent):"));
		add(listofCar);
		add(priceField);
		add(rentcarbtn);
		add(backbtnBut);
		rentcarbtn.setBackground(Color.pink);
		backbtnBut.setBackground(Color.MAGENTA);
		add(errorJLabel);
		errorJLabel.setVisible(false);
		pack();
	}

}
