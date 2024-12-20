package src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 *  @author Tamar Ben Nahum
 *
 */
public class WriteRuppinRent {
	public static void SaveToFlie(RuppinRent ruppin) {
		String filename = "RuppinRent.ser";
		FileOutputStream fos= null;
		ObjectOutputStream out=null;
		
		try {
			fos=new FileOutputStream(filename);
			out=new ObjectOutputStream(fos);
			out.writeObject(ruppin);
			out.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
