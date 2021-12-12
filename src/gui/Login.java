package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import passwordManager.*;
import java.sql.Connection;

public class Login extends JFrame implements ActionListener {
	JPanel panel;
	JButton login;
	JButton reset;
	JLabel userLabel;
	JLabel passLabel;
	JTextField username;
	JPasswordField password;
	
	public Login() {
		setSize(640, 480);
		setTitle("Password Manager Login");
		
		panel = new JPanel();
		panel.setLayout(null);
		
		userLabel = new JLabel("Username:");
		userLabel.setBounds(25, 100, 80, 80);
		
		passLabel = new JLabel("Password:");
		passLabel.setBounds(25, 150, 80, 80);
		
		login = new JButton("Login");
		login.addActionListener(this);
		login.setBounds(200, 300, 100, 80);
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setBounds(300, 300, 100, 80);
		
		username = new JTextField(20);
		username.setBounds(105, 110, 200, 50);
		password = new JPasswordField(20);
		password.setBounds(105, 160, 200, 50);
		
		panel.add(userLabel);
		panel.add(passLabel);
		panel.add(username);
		panel.add(password); 
		panel.add(login);
		panel.add(reset);
		
		add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String userText = username.getText();
			String passText = new String(password.getPassword());
			
			Connection myDB = LocalDB.getConnection(userText, passText);
			
			if (myDB == null) {
				JOptionPane.showMessageDialog(null, "Invalid login!", "Login Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				login.setEnabled(false);
				
				dispose();
				
				new Menu();
			}
		}
		
		if (e.getSource() == reset) {
			username.setText("");
			password.setText("");
		}
	}
}
