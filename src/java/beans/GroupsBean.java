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
import java.sql.Time;

/**
 *
 * @author colton
 */
public class GroupsBean implements Serializable{
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement st = null;
    private String connectionURL = "jdbc:mysql://localhost:3306/PomodoroDatabase";
    
    private int groupID;
    private String groupName;
    private String description;
    private Boolean verifyBeforeJoining;

    public GroupsBean(){
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
    
    public void createGroup(){
        try{
            this.setGroupID(1);
            String sql = "INSERT INTO Groups(GroupID, GroupName, Description, VerificationBeforeJoinBoolean) VALUES("
                    + this.getGroupID() + ", '" + this.getGroupName() + "', '" + this.getDescription() + "', " + this.getVerifyBeforeJoining() + ")";
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

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVerifyBeforeJoining() {
        return verifyBeforeJoining;
    }

    public void setVerifyBeforeJoining(Boolean verifyBeforeJoining) {
        this.verifyBeforeJoining = verifyBeforeJoining;
    }
    
    
}
