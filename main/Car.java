package main;

import java.io.Serializable;
import java.util.Objects;

import utils.E_CategoryStatus;

/**
 * car class
 * 
 * @author Tamar Ben Nahum
 *
 */

public class Car implements Serializable {
	// -----------Class Members-------------//

	private int manufactureYear;// שנת ייצור
	private String model;// דגם
	private String subModel;// תת דגם
	private utils.E_CategoryStatus Category;// קטגוריה
	private boolean isAuto;// אוטומטי או ידני
	private int rentalPricePerDay;// מחיר השכרה ליום
	private int licencenumber;// מספר רישוי
	private Branch branchOfCar;// מכונית בסניפים
	private boolean isVehicleAvailable;//בודק האם הרכב זמין להשכרה
	private static final long serialVersionUID = 1123456789L;
	
	// --------------------Getter and Setter------------------------------//

	public int getManufactureYear() {
		return manufactureYear;
	}

	public String getModel() {
		return model;
	}

	public utils.E_CategoryStatus getCategory() {
		return Category;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public int getRentalPricePerDay() {
		return rentalPricePerDay;
	}

	public Integer getBranchOfCar() {
		return branchOfCar.getNumBranch();
	}

	public boolean isVehicleAvailable() {
		return isVehicleAvailable;
	}

	// -------------------------------Constructors------------------------------
	public Car(int manufactureYear, String model, String subModel, E_CategoryStatus category, Boolean isAuto,
			int rentalPricePerDay, int licencenumber, Branch branchOfCar) {
		super();
		this.manufactureYear = manufactureYear;
		this.model = model;
		this.subModel = subModel;
		Category = category;
		this.isAuto = isAuto;
		this.rentalPricePerDay = rentalPricePerDay;
		this.licencenumber = licencenumber;
		this.branchOfCar = branchOfCar;
		this.isVehicleAvailable = true;
	}

	// ------------------All Methods----------------------------------------
	/**
	 * האם רכב מושכר או לא 
	 */
	public void carGotRent() {
		this.isVehicleAvailable = false;
	}

	// -------------------------------hashCode equals & toString------------------

	@Override
	public String toString() {
		return "Car [manufactureYear=" + manufactureYear + ", model=" + model + ", subModel=" + subModel + ", Category="
				+ Category + ", isAuto=" + isAuto + ", rentalPricePerDay=" + rentalPricePerDay + ", licencenumber="
				+ licencenumber + ", branchOfCar=" + Integer.toString(branchOfCar.getNumBranch()) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(licencenumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return (licencenumber == other.licencenumber);
	}

}
