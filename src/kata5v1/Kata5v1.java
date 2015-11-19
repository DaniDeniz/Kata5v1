/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */
public class Kata5v1 {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

        Class.forName("org.sqlite.JDBC");
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conect = DriverManager.getConnection("jdbc:sqlite:KATA.DB");
        //Connection conect = DriverManager.getConnection("jdbc:oracle:thin:@10.22.143.90:1521:orcl", "system", "orcl");
        Statement state = conect.createStatement();
        String query = "SELECT * FROM PEOPLE";

        ResultSet rs = state.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " " + rs.getString("NAME"));
        }

        String nameFile = "DATA\\emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(nameFile)));

        String mail;
        while ((mail = reader.readLine()) != null) {
            if (mail.contains("@")) {
                query = "INSERT INTO MAILS (MAIL) VALUES ('" + mail + "')";
                state.executeUpdate(query);
            }
        }

        rs.close();
        state.close();
        conect.close();

    }
}
