package com.cse.ju.oop.views.screens;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegistration extends JFrame implements ActionListener {
    private JButton loginButton;
    private JButton registrationButton;
    private JButton backButton;
    private JPanel leftPanel, rightPanel;

    public LoginRegistration() {
        super("Login or Register");

        // Left panel (text only)
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        // Set the background color to match the button color (same blue as buttons)
        Color buttonColor = new Color(70, 130, 180); // Same shade of blue as buttons
        leftPanel.setBackground(buttonColor);

        // Create a label with the text "Welcome to User Mode"
        JLabel welcomeLabel = new JLabel("Welcome to User Mode", JLabel.CENTER);

        // Set the font to bold and italic, with a size of 24
        welcomeLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));

        // Set the text color to black
        welcomeLabel.setForeground(Color.WHITE);

        // Add the label to the center of the left panel
        leftPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Right panel (buttons and welcome message) - no changes here
        rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add space around components

        // Welcome label at the top center of the right panel
        JLabel welcomeRightLabel = new JLabel("Welcome To User Menu", JLabel.CENTER);
        welcomeRightLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeRightLabel.setForeground(buttonColor); // Same shade of blue

        // Add the welcome text at the top of the right panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Span across the entire width
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(welcomeRightLabel, gbc);

        // Initialize buttons
        loginButton = new JButton("Login");
        registrationButton = new JButton("Registration");
        backButton = new JButton("Back");

        // Set button size and background color (blue)
        loginButton.setPreferredSize(new Dimension(150, 40));
        registrationButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(150, 40));

        // Set button background color
        loginButton.setBackground(buttonColor);
        registrationButton.setBackground(buttonColor);
        backButton.setBackground(buttonColor);

        // Set button text color
        loginButton.setForeground(Color.WHITE);
        registrationButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        // Add buttons to the right panel
        gbc.gridwidth = 1;  // Reset grid width
        gbc.gridy = 1;
        rightPanel.add(loginButton, gbc);

        gbc.gridy = 2;
        rightPanel.add(registrationButton, gbc);

        gbc.gridy = 3;
        rightPanel.add(backButton, gbc);

        // Set the layout of the main frame (split into two panels)
        this.setLayout(new GridLayout(1, 2)); // One row, two columns
        this.add(leftPanel);  // Left panel with text
        this.add(rightPanel); // Right panel with buttons and welcome text

        // Add action listeners for the buttons
        loginButton.addActionListener(this);
        registrationButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set frame properties
        this.setSize(800, 400);  // Increased width for more space
        this.setLocationRelativeTo(null);  // Center the window on the screen
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            this.dispose();
            User user=new User();
        } else if (e.getSource() == registrationButton) {
            this.dispose();
            CustomerRegister customerRegister = new CustomerRegister();
        } else if (e.getSource() == backButton) {
            this.dispose();
            InitialWindow initialWindow = new InitialWindow();
            initialWindow.setVisible(true);
            initialWindow.setSize(750, 475);  // Size of the initial window
            initialWindow.setLocation(450, 220);  // Position the window
            initialWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }


}

