package com.cse.ju.oop.views.screens;

import javax.swing.*;
import java.awt.*;

public class AccountData {
    String pincode;
    String customername;
    String accounttype;
    String accountnumber;
    String startbalance;

    public AccountData(String p, String c, String a, String an, String s) { // constructor ,
        this.pincode = p;
        this.customername = c;
        this.accounttype = a;
        this.accountnumber = an;
        this.startbalance = s;
    }

    public void print() {
        JOptionPane.showMessageDialog((Component)null, "PINCODE : " + this.pincode + "\n\tCustomer Name : " + this.customername + "\n\tAccount Type : " + this.accounttype + "Account Number : " + this.accountnumber + "\nStarting Balance : " + this.startbalance, "Account Information ", 1);
    }
}
