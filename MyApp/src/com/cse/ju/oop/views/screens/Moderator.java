package com.cse.ju.oop.views.screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.sql.*;

class Moderator extends JFrame implements ActionListener {
    private JButton addAccount;
    private JButton deleteAccount;
    private JButton PINchange;
    private JButton Back;
    private JButton logOut;
    private JButton seeAccounts;
    private JLabel atmLab;
    private JLabel dashboardLabel;
    private Container con;
    private ArrayList<AccountData> customerlist;
    private ArrayList<AccountData> loadedcustomerlist;

    public Moderator() {
        super("ADMIN");

        loadedcustomerlist = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Desktop\\JAVA\\JavaProject\\src\\Recording.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String pincode = data[0];
                    String customername = data[1];
                    String accounttype = data[2];
                    String accountnumber = data[3];
                    String startbalance = data[4];

                    AccountData atm = new AccountData(pincode, customername, accounttype, accountnumber, startbalance);
                    loadedcustomerlist.add(atm);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts: " + e.getMessage());
        }

        // Initialize components and layout
        con = this.getContentPane();
        con.setLayout(new BorderLayout());
        class BackgroundPanel extends JPanel {
            private Image backgroundImage;

            public BackgroundPanel(String imagePath) {
                this.backgroundImage = new ImageIcon(imagePath).getImage(); // Load the image
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Scale the image to fill the entire panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }


        // Create a left panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:\\Users\\ASUS\\Java\\JavaProject\\src\\DashboardImage.jpg");
        con.add(backgroundPanel, BorderLayout.CENTER);

        // Create right panel for buttons and label
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        //rightPanel.setPreferredSize(new Dimension(270, 350));
        rightPanel.setBackground(Color.WHITE); // Change the background to white

        // Create "Admin Dashboard" label
        dashboardLabel = new JLabel("☰ ADMIN DASHBOARD", JLabel.CENTER);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        Color buttonColor = new Color(70, 130, 180); // customized shade of blue
        dashboardLabel.setForeground(buttonColor);
        rightPanel.add(dashboardLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 1, 10, 10)); // 6 rows, 1 column for buttons
        buttonsPanel.setOpaque(false); // Make buttons panel transparent to show background color

        // Configure the buttons with blue background and white text
        addAccount = new JButton("Add Account");
        addAccount.setBackground(buttonColor);
        addAccount.setForeground(Color.WHITE);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBackground(buttonColor);
        deleteAccount.setForeground(Color.WHITE);

        PINchange = new JButton("Change PIN number");
        PINchange.setBackground(buttonColor);
        PINchange.setForeground(Color.WHITE);

        seeAccounts = new JButton("See All Accounts");
        seeAccounts.setBackground(buttonColor);
        seeAccounts.setForeground(Color.WHITE);

        Back = new JButton("Back");
        Back.setBackground(buttonColor);
        Back.setForeground(Color.WHITE);

        logOut = new JButton("Logout");
        logOut.setBackground(buttonColor);
        logOut.setForeground(Color.WHITE);

        // Add buttons to the panel (centered and with space)
        buttonsPanel.add(addAccount);
        buttonsPanel.add(deleteAccount);
        buttonsPanel.add(PINchange);
        buttonsPanel.add(seeAccounts);
        buttonsPanel.add(Back);
        buttonsPanel.add(logOut);

        // Center the buttons in the right panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(buttonsPanel);
        rightPanel.add(centerPanel, BorderLayout.CENTER);

        // Add space at the bottom
        JPanel bottomSpace = new JPanel();
        bottomSpace.setOpaque(false);
        bottomSpace.setPreferredSize(new Dimension(10, 40)); // Add space at the bottom
        rightPanel.add(bottomSpace, BorderLayout.SOUTH);

        // Create a JSplitPane to divide the window into left and right sections
        //  JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, rightPanel);
        // splitPane.setDividerLocation(250); // Set initial divider location (you can adjust as needed)
        // splitPane.setDividerSize(0); // Remove the divider bar for a cleaner look
        this.setLayout(new GridLayout(1, 2));
        this.add(backgroundPanel);  // Add left panel (with background image) to the window
        this.add(rightPanel); // Add right panel (with buttons) to the window
        // Add the split pane to the container
        //con.add(splitPane, BorderLayout.CENTER);
        // Set frame properties (larger window size)
        this.setSize(750, 475); // Increase the window size
        this.setVisible(true);
        this.setLocation(450, 220);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Attach event listeners
        addAccount.addActionListener(this);
        deleteAccount.addActionListener(this);
        PINchange.addActionListener(this);
        Back.addActionListener(this);
        seeAccounts.addActionListener(this);
        logOut.addActionListener(this);
    }

    // Method to add a new person/account
    private void addPersons() {
        // Create a panel to hold all the input fields
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, 10px gaps

        // Create labels and text fields
        JLabel pincodeLabel = new JLabel("Enter PIN:");
        JTextField pincodeField = new JTextField(10);

        JLabel customerNameLabel = new JLabel("Enter Customer Name:");
        JTextField customerNameField = new JTextField(20);

        JLabel accountTypeLabel = new JLabel("Enter Account Type (Saving or Current):");
        JTextField accountTypeField = new JTextField(15);

        JLabel accountNumberLabel = new JLabel("Enter Account Number:");
        JTextField accountNumberField = new JTextField(20);

        JLabel startBalanceLabel = new JLabel("Enter Starting Balance:");
        JTextField startBalanceField = new JTextField(15);

        // Add the components to the form panel
        formPanel.add(pincodeLabel);
        formPanel.add(pincodeField);
        formPanel.add(customerNameLabel);
        formPanel.add(customerNameField);
        formPanel.add(accountTypeLabel);
        formPanel.add(accountTypeField);
        formPanel.add(accountNumberLabel);
        formPanel.add(accountNumberField);
        formPanel.add(startBalanceLabel);
        formPanel.add(startBalanceField);

        // Keep asking for the correct PINCODE until a unique one is provided
        String pincode;
        do {
            int result = JOptionPane.showConfirmDialog(null, formPanel, "Account Registration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                pincode = pincodeField.getText(); // Get the entered pincode
                if (isPincodeUsed(pincode)) {
                    JOptionPane.showMessageDialog(null, "This pincode is already in use. Please enter another pincode.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

                return;
            }
        } while (isPincodeUsed(pincode)); // Continue prompting for pincode if it's already used
        JOptionPane.showMessageDialog(null, "Account Created Successfully.", "Account Created", JOptionPane.PLAIN_MESSAGE);
        // Get other inputs from the form fields
        String customername = customerNameField.getText();
        String accounttype = accountTypeField.getText();
        String accountnumber = accountNumberField.getText();
        String startbalance = startBalanceField.getText();

        // database , god save me
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url ="jdbc:mysql://127.0.0.1:3306/my_db2";
        String username= "root";
        String password="mysql";

        try {
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement =connection.createStatement();
            String sql ="INSERT INTO banking_project( PINCODE , Customer_name, Account_type,Account_number,Start_balance)" +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,pincode);
            preparedStatement.setString(2,customername);
            preparedStatement.setString(3,accounttype);
            preparedStatement.setString(4,accountnumber);
            preparedStatement.setString(5,startbalance);
            // preparedStatement.executeUpdate();
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully!");
            }
            preparedStatement.close();
            connection.close();


        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } //datbase done



        // Create a new account object and add it to the list
        AccountData atm = new AccountData(pincode, customername, accounttype, accountnumber, startbalance);
        loadedcustomerlist.add(atm);

        savePerson();
    }


    // Method to check if a pincode is already in use
    private boolean isPincodeUsed(String pincode) {
        for (AccountData atm : loadedcustomerlist) { //loaded
            if (pincode.equals(atm.pincode)) {
                return true;
            }
        }
        return false;
    }
    private void savePerson() {

        //File file2 = new File("Recordings.txt"); // Checking if file1 exists and has conten

        // boolean file1Exists = file1.exists() && file1.length() > 0;
        // boolean file2Exists = file2.exists() && file2.length() > 0;


        try (FileWriter fr = new FileWriter("C:\\Users\\ASUS\\Desktop\\JAVA\\JavaProject\\src\\Recording.txt");
             PrintWriter pw = new PrintWriter(fr);
             FileWriter fr1 = new FileWriter("C:\\Users\\ASUS\\Desktop\\JAVA\\JavaProject\\src\\Recordings.txt");
             PrintWriter pw1 = new PrintWriter(fr1)) {

            // captions
            pw1.printf("%-10s %-20s %-15s %-20s %-10s\n",
                    "PINCODE", "CUSTOMER NAME", "ACCOUNT TYPE", "ACCOUNT NUMBER", "BALANCE");

            for (AccountData atm : loadedcustomerlist) {
                String line = String.format("%s,%s,%s,%s,%s\n", atm.pincode, atm.customername, atm.accounttype, atm.accountnumber, atm.startbalance);
                String line1 = String.format("%-10s %-20s %-15s %-20s %-10s" ,
                        atm.pincode, atm.customername, atm.accounttype, atm.accountnumber, atm.startbalance);

                pw1.println(line1);
                pw.print(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a person/account
    private void delete(String pincode) {
        for (int i = 0; i < loadedcustomerlist.size(); i++) {
            AccountData atm = loadedcustomerlist.get(i);
            if (pincode.equals(atm.pincode)) {
                int confirm = JOptionPane.showConfirmDialog(null, String.format("Do you really want to delete the following record?\n\nPINCODE: %s\nCustomer name: %s\nAccount Type: %s\nAccount Number: %s\nTotal Balance: %s",
                        atm.pincode, atm.customername, atm.accounttype, atm.accountnumber, atm.startbalance), "CONFIRMATION ABOUT DELETION", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    loadedcustomerlist.remove(i);

                    //  MySQL JDBC driver
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    // Database connection
                    String url = "jdbc:mysql://127.0.0.1:3306/my_db2";
                    String username = "root";
                    String password = "mysql";

                    try {

                        Connection connection = DriverManager.getConnection(url, username, password);

                        // SQL query
                        String sql = "DELETE FROM banking_project WHERE PINCODE = ?";

                        //  statement
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);

                        // setting the PIN number (replacing the ? with the actual PIN)
                        preparedStatement.setString(1, pincode);

                        // Execute the (delete)
                        int rowsAffected = preparedStatement.executeUpdate();


                        if (rowsAffected > 0) {
                            System.out.println("Record deleted successfully!");
                        } else {
                            System.out.println("No record found with the specified PIN.");
                        }

                        // Close the statement and connection
                        preparedStatement.close();
                        connection.close();

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }//database done

                    JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                }
                break;
            }
        }
        savePerson();
    }

    // Method to change PIN
    private void edit(String pincode) {
        for (AccountData atm : loadedcustomerlist) {
            if (pincode.equals(atm.pincode)) {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        String.format(
                                "Do you want to edit the PIN code for the following record?\n\n" +
                                        "PINCODE: %s\nCustomer name: %s\nAccount Type: %s\nAccount Number: %s\nStarting Balance: %s",
                                atm.pincode, atm.customername, atm.accounttype, atm.accountnumber, atm.startbalance
                        ),
                        "CONFIRMATION BOX",
                        JOptionPane.YES_NO_OPTION
                );

                if (option == JOptionPane.YES_OPTION) {
                    String newPincode = JOptionPane.showInputDialog(
                            null,
                            "Enter new PIN code to replace the old one",
                            "EDIT PINCODE",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    if (newPincode != null && !newPincode.trim().isEmpty()) {
                        atm.pincode = newPincode;

                        //sql code for updated PIN
                        String url = "jdbc:mysql://127.0.0.1:3306/my_db2";
                        String username = "root";
                        String password = "mysql";

                        // sql query
                        String sql = "UPDATE banking_project SET PINCODE = ? WHERE PINCODE = ?";

                        try {
                            //  database connection
                            Connection connection = DriverManager.getConnection(url, username, password);

                            // prepare the SQL statement with placeholders
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);

                            // set the values for the placeholders
                            preparedStatement.setString(1, newPincode); // Set new PIN
                            preparedStatement.setString(2, pincode); // Replace old PIN

                            // execute the update
                            int rowsAffected = preparedStatement.executeUpdate();


                            if (rowsAffected > 0) {
                                System.out.println("PIN updated in record  successfully!");
                            } else {
                                System.out.println("No record found with the old PIN.");
                            }

                            // Close the connection and statement
                            preparedStatement.close();
                            connection.close();

                        } catch (SQLException e) {

                            e.printStackTrace();
                        }
                        //databse done

                        JOptionPane.showMessageDialog(
                                null,
                                "PIN code updated successfully.",
                                "SUCCESS",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        savePerson(); // Save changes to file
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "PIN code update was cancelled or invalid.",
                                "CANCELLED",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }
                break;
            }
        }

    }


    //method to see all accounts
    private void seeAccount() {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ASUS\\Desktop\\JAVA\\JavaProject\\src\\Recordings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading the file: " + e.getMessage());
            return;
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); //colum same gap (monospace )

        JScrollPane scrollPane = new JScrollPane(textArea);

        // scrollPane.setPreferredSize(new Dimension(500, 400));//scroll panel
        JOptionPane.showMessageDialog(this, scrollPane, "All Accounts", JOptionPane.INFORMATION_MESSAGE);
    }



    // Implement action listeners for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String s1;
        if (sourceButton == addAccount) {
            addPersons();
        } else if (sourceButton == deleteAccount) {
            s1 = JOptionPane.showInputDialog(null, "Enter PinCode To Delete Account", "DELETION MENU", JOptionPane.INFORMATION_MESSAGE);
            delete(s1);
        } else if (sourceButton == PINchange) {
            s1 = JOptionPane.showInputDialog(null, "Enter PinCode for the Account", "PIN Change", JOptionPane.INFORMATION_MESSAGE);
            edit(s1);
        } else if (sourceButton == Back) {
            this.dispose();
            LoginForm loginform = new LoginForm();
        } else if (sourceButton == seeAccounts) {
            seeAccount();
        } else if (sourceButton == logOut) {
            int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Good Bye", "ATM", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }

}


