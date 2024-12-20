package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
/**
 *  @author Tamar Ben Nahum
 *
 */
public class Branch implements Serializable{
	// -----------Class Members-------------//

	private int numBranch;
	private String placeBranch;
	private String[] hourOpen;
	// check Vehicles that were not rented
	private ArrayList<Car> carNotRentedArray;
	private static final long serialVersionUID = 1123456789L;
	// --------------------Getter and Setter------------------------------//
	public int getNumBranch() {
		return numBranch;
	}
	
	
	// -------------------------------Constructors------------------------------
	public Branch(int numBranch, String placeBranch) {
		super();
		this.numBranch = numBranch;
		this.placeBranch = placeBranch;
		this.hourOpen = utils.OpenHour.hoursOpenningBranch;
		this.carNotRentedArray = new ArrayList<>();
	}

	// ------------------All Methods----------------------------------------


	/**
	 * check if car not rented
	 * 
	 * @param c
	 * @return
	 */
	public boolean addNewCar(Car c) {
		if (!carNotRentedArray.contains(c)) {
			carNotRentedArray.add(c);
			return true;
		}
		return false;
	}
	// -------------------------------hashCode equals & toString------------------

	@Override
	public String toString() {
		return "Branch [numBranch=" + numBranch + ", placeBranch=" + placeBranch + ", hourOpen="
				+ Arrays.toString(hourOpen) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numBranch);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		return numBranch == other.numBranch;
	}

}
