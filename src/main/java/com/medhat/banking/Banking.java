
package com.medhat.banking;

import static com.medhat.banking.DBConnection.connect;

public class Banking {

    public static void main(String[] args) {
        Login l = new Login();
        connect();
    }
}
