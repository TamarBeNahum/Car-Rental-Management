package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JFrame;
/**
 *  @author Rahav Shema and Tamar Ben Nahum
 *
 */
public class ShowUser extends JFrame {
	
	private TextArea araArea;
	private RuppinRent ruppin;
	
	public ShowUser(RuppinRent Ruppin) {
		this.ruppin=Ruppin;
		araArea = new TextArea(10, 50);
		araArea.setText(ruppin.getAllUsersDetails());
		initialize();
	}
	
	private void initialize() {
		setLayout(new FlowLayout());
		getContentPane().setBackground(Color.WHITE);
		add(araArea);
		araArea.setEditable(false);
		pack();
	}
}
