package main;

import java.awt.Color;
/**
 * 
 * @author Rahav Shema and Tamar Ben Nahum
 *
 */
public class Main {

	public static void main(String[] args) {
		// קריאה של הקובץ 
		RuppinRent ruppin;
		 if( ReadRuppinRent.readRuppinRent() == null) {
			 ruppin = new RuppinRent(new User("admin", "last", 0, "admin@gmail.com", "08/09/1995", 2000, "pass"));
		 }
		 else {
			 ruppin = ReadRuppinRent.readRuppinRent();
		 }


		 SignIn my = new SignIn(ruppin);
		 my.setSize(500, 200); // הגדרת גודל החלון
		 my.setVisible(true);  // הפיכת החלון לגלוי
		 my.getContentPane().setBackground(Color.pink); // הגדרת צבע הרקע

	}

}