package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author colton
 */
public class UsersBean implements Serializable{
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement st = null;
    private String connectionURL = "jdbc:mysql://localhost:3306/PomodoroDatabase";
    private String givenUserName;
    private String givenPassword;
    
    public UsersBean(){
        super();
        try {
        // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");
        // Get a Connection to the database
            connection = DriverManager.getConnection(connectionURL, "root", "wordpass");
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
    }
    
    public void createAccount(){
     try{
        String sql = "INSERT INTO Users(Username,Password) VALUES('" + this.getGivenUserName() + "','" + this.getGivenPassword() + "')";
        Statement s = connection.createStatement();
        s.executeUpdate (sql);
        System.out.println("Sql query occured");
        s.close ();
      }catch(Exception e){
        System.out.println("Exception is ;"+e);
      }
    }            


    public String getGivenUserName() {
        return givenUserName;
    }

    public void setGivenUserName(String givenUserName) {
        this.givenUserName = givenUserName;
    }

    public String getGivenPassword() {
        return givenPassword;
    }

    public void setGivenPassword(String givenPassword) {
        this.givenPassword = givenPassword;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    
    
}
