package main;

import java.io.Serializable;
import java.util.Objects;
/**
 *   @author Rahav Shema and Tamar Ben Nahum
 *
 */
public class User implements Serializable{

	private String fName;
	private String lName;
	private int ID;
	private String email;
	private String birthString;
	private int dateLicense;
	private String password;
	private String Passwordverification;
	private static final long serialVersionUID = 1123456789L;

	// --------------------Getter and Setter------------------------------//
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	// -------------------------------Constructors------------------------------

	public User(String fName, String lName, int iD, String email, String birthString , int dateLicense,String password) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.ID = iD;
		this.email = email;
		this.birthString=birthString;
		this.dateLicense = dateLicense;
		this.password = password;
	}

//----------------------All Methods--------------------


	public boolean checkPassword(String pass) {
		return this.password.equals(pass);
	}

	// -------------------------------hashCode equals & toString------------------


	@Override
	public String toString() {
		return "User [fName=" + fName + ", lName=" + lName + ", ID=" + ID + ", email=" + email + ", birthString="
				+ birthString + ", dateLicense=" + dateLicense + "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);

	}

}
