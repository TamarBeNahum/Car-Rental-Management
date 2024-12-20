package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 *   @author Rahav Shema and Tamar Ben Nahum
 */
public class ReadRuppinRent {
	public static RuppinRent readRuppinRent() {
		String filename = "RuppinRent.ser";
		RuppinRent ruppin = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			ruppin = (RuppinRent) in.readObject();
			in.close();

		} catch (IOException ex) {
			System.out.println("There is no data in the Ruppin Rent system - creating a new one.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return ruppin;
	}
}
