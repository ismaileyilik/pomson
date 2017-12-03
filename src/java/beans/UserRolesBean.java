/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author colton
 */
public class UserRolesBean implements Serializable{
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement st = null;
    private String connectionURL = "jdbc:mysql://localhost:3306/PomodoroDatabase";
    private String givenUsername;
    private String userRole;

    public UserRolesBean(){
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
    
    public void updateUserRole(){
        try{
            String sql = "INSERT INTO UserRoles(Username,Role) VALUES('" + this.getGivenUsername() + "','" + this.getUserRole() + "')";
            Statement s = connection.createStatement();
            s.executeUpdate (sql);
            System.out.println("The sql statement [" + sql + "] has been executed.");
            s.close ();
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
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

    public String getGivenUsername() {
        return givenUsername;
    }

    public void setGivenUsername(String givenUsername) {
        this.givenUsername = givenUsername;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
}
