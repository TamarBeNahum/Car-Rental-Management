package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 
 * @author Rahav Shema and Tamar Ben Nahum
 *
 */
public class RuppinRent implements Serializable {
	// -----------Class Members-------------//
	private Map<Integer, Car> allCars;
	private Map<String, User> allUsers;
	private Map<Integer, Branch> allBranchMap;
	private User manager;
	private static final long serialVersionUID = 1123456789L;

	// -------------------------------Constructors------------------------------
	public RuppinRent(User manager) {
		super();
		allBranchMap = new HashMap<>();
		allCars = new HashMap<>();
		allUsers = new HashMap<>();
		this.manager = manager;
	}

	// -----------------Getters and setters----------------------
	public User getManager() {
		return manager;
	}

	// get all the branch
	public Branch getBranch(Integer branchNum) {
		return allBranchMap.get(branchNum);
	}

	// get all the cars
	public Collection<Car> getAllCars() {
		return allCars.values();
	}

	// ---------------------All Methods----------------------
	/**
	 * the function add new user to hashMap
	 * 
	 * @param email
	 * @param u
	 * @return
	 */
	public boolean addNewUser(String email, User u) {
		if (!allUsers.containsKey(email)) {
			allUsers.put(email, u);
			return true;
		}
		return false;
	}

	/**
	 * print all details of user
	 * 
	 * @return
	 */
	public String getAllUsersDetails() {
		return allUsers.toString();
	}

	/**
	 * הפונקציה מקבלת כפרמטרים מייל וסיסמא היא בודקת האם המייל מופיע בכל רשימת
	 * המשתמשים וגם אם המייל מקבלים תואם לסיסמא של המשתמש
	 * 
	 * @param email
	 * @param pass
	 * @return
	 */
	public boolean CheckUser(String email, String password) {
		return allUsers.containsKey(email) && allUsers.get(email).checkPassword(password);
	}

	/**
	 * add new branch
	 * 
	 * @param num
	 * @param b
	 * @return
	 */

	public boolean addBranch(int num, Branch b) {
		if (!allBranchMap.containsKey(num)) {
			allBranchMap.put(num, b);
			return true;
		}
		return false;
	}

	/**
	 * count how many branch have
	 * 
	 * @return
	 */
	public int countBranch() {
		return allBranchMap.size();
	}

	// הפונקציה מחזירה את כל מספרי הסניפים כמחרוזות, על מנת שנוכל להשתמש בבחירה
	public ArrayList<String> getBranchNumbers() {
		// מערך חדש של שמות הסניפים
		ArrayList<String> namesBranch = new ArrayList<>();
		// לבדוק האם מספר הסניפים גדול מאפס
		if (allBranchMap.size() > 0) {
			// לעבור על כל הסניפים ולהוסיף את המפתח למערך החדש
			for (Integer key : allBranchMap.keySet()) {
				namesBranch.add(key.toString());
			}
			return namesBranch;
		}
		return null;
	}

	/**
	 * להוסיף מכונית חדשה לסניף ספציפי
	 * 
	 * @param licenceCarnum
	 * @param c
	 * @return
	 */
	public boolean addCar(int licenceCarnum, Car c) {
		if (!allCars.containsKey(licenceCarnum)) {
			if (allBranchMap.get(c.getBranchOfCar()).addNewCar(c)) {
				allCars.put(licenceCarnum, c);
				return true;
			}
		}
		return false;
	}

	/**
	 * פונקציה המראה את כל הפרטים של הסניפים באמצעות איטרטור
	 * 
	 * @return
	 */
	public String showAllBranch() {
		// create new string
		String allBranchDetails = "";
		// create iterator that contains the all branch number from hash map of all
		// branches
		Iterator<Integer> branchNumber = allBranchMap.keySet().iterator();

		while (branchNumber.hasNext()) {
			allBranchDetails += allBranchMap.get(branchNumber.next()).toString() + "\n";

		}
		return allBranchDetails;
	}

	
	
	/**
	 * הפונקציה מחזירה את כל המודלים של המכוניות
	 * @return
	 */
	public ArrayList<String> getAllModelCar() {
		
		ArrayList<String> modelsCarNames = new ArrayList<>();
		
		if (allCars.size() > 0) {
		
			for (Car key : allCars.values()) {
				modelsCarNames.add(key.getModel());
			}
			return modelsCarNames;
		}
		return null;
	}
	

}
