package com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InitialWindow extends JFrame implements ActionListener {
    private JButton userButton;
    private JButton adminButton;
    private JPanel rightPanel;


    public InitialWindow() {
        super("ATM System");

        // left panel (50% of space) ,background image
        BackgroundPanel leftPanel = new BackgroundPanel("C:\\Users\\ASUS\\Java\\JavaProject\\src\\user.jpg");
        leftPanel.setLayout(new BorderLayout());

        // right panel , buttons
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(270, 350));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        //button
        userButton = new JButton("User");
        adminButton = new JButton("Admin");
        userButton.setPreferredSize(new Dimension(165, 47));
        adminButton.setPreferredSize(new Dimension(165, 47));


        Color blueColor = new Color(70, 130, 180);  // RGB values for a blue color
        userButton.setBackground(blueColor);
        adminButton.setBackground(blueColor);

        //  text color to white
        userButton.setForeground(Color.WHITE);
        adminButton.setForeground(Color.WHITE);
        //button done

        JLabel swissBankLabel = new JLabel("SWISS BANK BANGLADESH");
        swissBankLabel.setFont(new Font("Serif", Font.BOLD, 24));  // Bold font, size 24
        swissBankLabel.setForeground(new Color(70, 130, 180));

        //swiss bank text

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // span across both columns
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(swissBankLabel, gbc);


        // Add buttons to right panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(userButton, gbc);

        gbc.gridy = 2;
        rightPanel.add(adminButton, gbc);

        // action listeners for buttons
        userButton.addActionListener(this);
        adminButton.addActionListener(this);

        //  layout of the main window
        this.setLayout(new GridLayout(1, 2));
        this.add(leftPanel);  // Add left panel (with background image) to the window
        this.add(rightPanel); // Add right panel (with buttons) to the window
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            this.dispose(); // closing  the initial window
            LoginForm loginForm = new LoginForm(); ///admin /moderator
            loginForm.setVisible(true);
        } else if (e.getSource() == userButton) {
            this.dispose();
            LoginRegistration loginRegistration = new LoginRegistration();
            loginRegistration.setVisible(true);
        }
    }
}



