/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author null
 */
public class SSLConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "/home/null/newcerts/keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "welcome123");
        System.setProperty("javax.net.ssl.trustStore", "/home/null/newcerts/truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "welcome123");
        String username = "app1";
        String password = "welcome123";
        String dbURL = "jdbc:mysql://localhost:3306/ismv3"
                + "?verifyServerCertificate=false"
                + "&useSSL=true"
                + "&requireSSL=true";

        try {
            Connection myConn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
