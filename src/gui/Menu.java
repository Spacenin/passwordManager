package gui;

import passwordManager.MenuOptions;
import passwordManager.Password;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JFrame implements ActionListener {
	JPanel panel;
	JButton add;
	JButton get;
	JButton quit;
	JButton submitAdd;
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
		
		quit = new JButton("3. Quit");
		quit.addActionListener(this);
		quit.setBounds(100, 60, 400, 20);
		
		panel.add(add);
		panel.add(get);
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
			
			for (Password each : passwords) {
				JLabel getLabel = new JLabel();
				
				getLabel.setText(num + ". " + each.getName() + ": " + each.getUsername() + ", " + 
						each.getPassword() + ": " + each.getUrl());
				getLabel.setBounds(x, y, width, height);
				
				y += 20;
				num++;
				
				getPanel.add(getLabel);
			}
			
			getFrame.add(getPanel);
			getFrame.setVisible(true);
		}
		
		if (e.getSource() == add) {
			JFrame addFrame = new JFrame();
			JPanel addPanel = new JPanel();
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
			
			MenuOptions.savePassword(pass);
		}
	}
}