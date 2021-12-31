package gui;

import passwordManager.MenuOptions;
import passwordManager.Password;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JFrame implements ActionListener {
	JFrame addFrame = new JFrame();
	JPanel addPanel = new JPanel();
	JFrame clearFrame = new JFrame();
	JPanel clearPanel = new JPanel();
	JPanel panel;
	JButton add;
	JButton get;
	JButton quit;
	JButton submitAdd;
	JButton clear;
	JButton clearYes;
	JButton clearNo;
	JPasswordField addPassField;
	JTextField addUserField;
	JTextField addUrlField;
	JTextField addNameField;
	String password;
	String username;
	String url;
	String name;
	Password pass;
	
	public Menu() {
		setSize(640, 480);
		setTitle("Password Management");
		
		panel = new JPanel();
		panel.setLayout(null);
		
		add = new JButton("1. Add a password to the database");
		add.addActionListener(this);
		add.setBounds(100, 20, 400, 20);
		
		get = new JButton("2. Get all passwords currently saved in database");
		get.addActionListener(this);
		get.setBounds(100, 40, 400, 20);
		
		clear = new JButton("3. Clear all passwords in vault");
		clear.addActionListener(this);
		clear.setBounds(100, 60, 400, 20);
		
		quit = new JButton("4. Quit");
		quit.addActionListener(this);
		quit.setBounds(100, 80, 400, 20);
		
		panel.add(add);
		panel.add(get);
		panel.add(clear);
		panel.add(quit);
		
		add(panel);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quit) {
			dispose();
		}
		
		if (e.getSource() == get) {
			int num = 1;
			int x = 100;
			int y = 50;
			int width = 400;
			int height = 50;
			
			JFrame getFrame = new JFrame();
			JPanel getPanel = new JPanel();
			getPanel.setLayout(null);
			
			getFrame.setSize(640, 480);
			getFrame.setTitle("Saved Passwords");
			getFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			ArrayList<Password> passwords = MenuOptions.getPasswords();
			
			if (passwords.size() == 0) {
				JLabel empty = new JLabel();
				
				empty.setText("There are no passwords currently saved!");
				empty.setBounds(100, 50, 400, 50);
				
				getPanel.add(empty);
			}
			
			else {
				for (Password each : passwords) {
					JLabel getLabel = new JLabel();
					
					getLabel.setText(num + ". " + each.getName() + ": " + each.getUsername() + ", " + 
							each.getPassword() + ": " + each.getUrl());
					getLabel.setBounds(x, y, width, height);
					
					y += 20;
					num++;
					
					getPanel.add(getLabel);
				}
			}
			
			getFrame.add(getPanel);
			getFrame.setVisible(true);
		}
		
		if (e.getSource() == add) {
			JLabel addLabel1 = new JLabel("Enter the password:");
			addPassField = new JPasswordField(20);
			JLabel addLabel2 = new JLabel("Enter the username:");
			addUserField = new JTextField(20);
			JLabel addLabel3 = new JLabel("Enter the url:");
			addUrlField = new JTextField(20);
			JLabel addLabel4 = new JLabel("Enter the name of the password:");
			addNameField = new JTextField(20);
			submitAdd = new JButton("Submit");
			
			addPanel.setLayout(null);
			addFrame.setSize(640, 480);
			addFrame.setTitle("Add a Password");
			addFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			addLabel1.setBounds(20, 20, 200, 50);
			addLabel2.setBounds(20, 40, 200, 50);
			addLabel3.setBounds(20, 60, 200, 50);
			addLabel4.setBounds(20, 80, 200, 50);
			
			addPassField.setBounds(220, 35, 150, 20);
			addUserField.setBounds(220, 55, 150, 20);
			addUrlField.setBounds(220, 75, 150, 20);
			addNameField.setBounds(220, 95, 150, 20);
			
			submitAdd.addActionListener(this);
			submitAdd.setBounds(280, 300, 100, 60);
			
			addPanel.add(addLabel1);
			addPanel.add(addLabel2);
			addPanel.add(addLabel3);
			addPanel.add(addLabel4);
			
			addPanel.add(submitAdd);
			
			addPanel.add(addPassField);
			addPanel.add(addUserField);
			addPanel.add(addUrlField);
			addPanel.add(addNameField);
			
			addFrame.add(addPanel);
			addFrame.setVisible(true);
		}
		
		if (e.getSource() == submitAdd) {
			password = new String(addPassField.getPassword());
			username = addUserField.getText();
			url = addUrlField.getText();
			name = addNameField.getText();
			
			pass = new Password(password, username, url, name);
			
			JLabel submitted = new JLabel();
			submitted.setText("Submitted password " + name + "!");
			submitted.setBounds(240, 100, 400, 20);
			
			JPanel addPanel = new JPanel();
			JFrame addFrame = new JFrame();
			
			addPanel.setLayout(null);
			addFrame.setSize(640, 480);
			addFrame.setTitle("Add a Password");
			addFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			addPanel.add(submitted);
			addFrame.add(addPanel);
			addFrame.setVisible(true);
			
			MenuOptions.savePassword(pass);
			
			addPassField.setText("");
			addUserField.setText("");
			addUrlField.setText("");
			addNameField.setText("");
		}
		
		if (e.getSource() == clear) {
			
			clearPanel.setLayout(null);
			
			clearFrame.setSize(640, 480);
			clearFrame.setTitle("Validation");
			clearFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			JLabel clearQ = new JLabel();
			clearQ.setText("Are you sure you'd like to clear all?");
			
			clearYes = new JButton("Yes");
			clearYes.addActionListener(this);
			clearNo = new JButton("No");
			clearNo.addActionListener(this);
			
			clearQ.setBounds(200, 100, 400, 50);
			clearYes.setBounds(200, 150, 100, 50);
			clearNo.setBounds(300, 150, 100, 50);
			
			clearPanel.add(clearQ);
			clearPanel.add(clearYes);
			clearPanel.add(clearNo);
			
			clearFrame.add(clearPanel);
			clearFrame.setVisible(true);
		}
		
		if (e.getSource() == clearYes) {
			MenuOptions.clearPasswords();
			
			JLabel clearDone = new JLabel();
			clearDone.setText("All passwords cleared!");
			clearDone.setBounds(200, 100, 400, 50);
			
			clearPanel = new JPanel();
			clearFrame = new JFrame();
			
			clearPanel.setLayout(null);
			
			clearFrame.setSize(640, 480);
			clearFrame.setTitle("Validation");
			clearFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			clearPanel.add(clearDone);
			clearFrame.add(clearPanel);
			clearFrame.setVisible(true);
		}
		
		if (e.getSource() == clearNo) {
			JLabel notClear = new JLabel();
			notClear.setText("Did not clear passwords!");
			notClear.setBounds(200, 100, 400, 50);
			
			clearPanel = new JPanel();
			clearFrame = new JFrame();
			
			clearPanel.setLayout(null);
			
			clearFrame.setSize(640, 480);
			clearFrame.setTitle("Validation");
			clearFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			clearPanel.add(notClear);
			clearFrame.add(clearPanel);
			clearFrame.setVisible(true);
		}
	}
}