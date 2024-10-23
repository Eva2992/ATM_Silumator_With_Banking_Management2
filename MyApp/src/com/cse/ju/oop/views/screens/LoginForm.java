package com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//admin login
class LoginForm extends JFrame implements ActionListener {
    private JButton btnLogin;
    private JButton btnReset;
    private JButton btnBack; // Add Back button
    private JTextField Tfield;
    private JPasswordField Pfield;
    private JCheckBox showPasswordBox;

    LoginForm() {
        super("Admin Login");
        this.setLayout(new GridLayout(1, 2)); // Split the window into two panels (left and right)
        this.setLocationRelativeTo(null);

        // Left panel (blue background)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(70, 130, 180)); // Blue background
        leftPanel.setLayout(new BorderLayout());

        // Right panel - form and buttons
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lab1 = new JLabel("Username:");
        JLabel lab2 = new JLabel("Password:");
        Tfield = new JTextField(10);
        Pfield = new JPasswordField(10);
        Color buttonColor = new Color(70, 130, 180);
        btnLogin = new JButton("Login");
        btnLogin.setBackground(buttonColor);
        btnReset = new JButton("Reset");
        btnReset.setBackground(buttonColor);
        btnBack = new JButton("Back"); // Initialize the Back button
        btnBack.setBackground(buttonColor); // Set background color for Back button
        showPasswordBox = new JCheckBox("Show Password");

        // Text to add on the left panel
        JLabel adminModeLabel = new JLabel("Admin Mode");
        adminModeLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        adminModeLabel.setForeground(Color.BLACK);
        adminModeLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(adminModeLabel, BorderLayout.CENTER);

        // Layout for right panel (form elements)
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(lab1, gbc);

        gbc.gridx = 1;
        rightPanel.add(Tfield, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(lab2, gbc);

        gbc.gridx = 1;
        rightPanel.add(Pfield, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across both columns
        rightPanel.add(showPasswordBox, gbc);

        gbc.gridwidth = 1; // Reset gridwidth for next buttons
        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(btnLogin, gbc);

        gbc.gridx = 1;
        rightPanel.add(btnReset, gbc);

        // Positioning the Back button below Login and Reset
        gbc.gridx =2;
        // gbc.gridy = 5; // Move down to the next row
        //gbc.gridwidth = 2; // Span across both columns to make it long
        rightPanel.add(btnBack, gbc);

        // Add action listeners for the buttons
        showPasswordBox.addActionListener(this);
        btnLogin.addActionListener(this);
        btnReset.addActionListener(this);
        btnBack.addActionListener(this); // Action listener for Back button

        // Add panels to frame
        this.add(leftPanel);  // Left panel with blue background
        this.add(rightPanel); // Right panel with form elements

        // Frame settings
        this.setSize(700, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnLogin) {
            try (FileReader fr = new FileReader("C:\\Users\\ASUS\\Desktop\\JAVA\\JavaProject\\src\\loginInfo.txt");
                 BufferedReader br = new BufferedReader(fr)) {
                // String savedUsername = br.readLine();
                String savedUsername = "eva";
                // String savedPassword = br.readLine();
                String savedPassword = "eva";
                String enteredUsername = Tfield.getText();
                String enteredPassword = new String(Pfield.getPassword());

                if (enteredUsername.isEmpty() && enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ENTER YOUR USER NAME and Password");
                } else if (enteredUsername.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ENTER YOUR USER NAME ");
                } else if (enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ENTER YOUR Password");
                } else if (!enteredUsername.equals(savedUsername) || !enteredPassword.equals(savedPassword)) {
                    JOptionPane.showMessageDialog(null, "WRONG USERNAME AND PASSWORD");
                } else {
                    JOptionPane.showMessageDialog(null, "Login Successfully");
                    this.dispose();  // Close the login form
                    Moderator t2 = new Moderator();  // Open the Moderator window
                    t2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    t2.setSize(540, 350);
                    t2.setVisible(true);
                    t2.setLocationRelativeTo(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnReset) {
            Tfield.setText("");
            Pfield.setText("");
        } else if (ae.getSource() == btnBack) {
            this.dispose();
            InitialWindow initialWindow =new InitialWindow();
            initialWindow.setVisible(true);
            initialWindow.setSize(700, 400);  // Size of the initial window
            initialWindow.setLocation(450, 220);  // Position the window
            initialWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

        if (showPasswordBox.isSelected()) {
            Pfield.setEchoChar('\u0000');  // Show password
        } else {
            Pfield.setEchoChar('*');  // Hide password
        }
    }
}


