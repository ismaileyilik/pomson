/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.GoalsBean;
import beans.GroupsBean;
import beans.UserRoleBean;
import beans.UsersBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author colton
 */
public class DatabaseDriver {
    private Connection connection = null;
    private ResultSet rs = null;
    private Statement st = null;
    private String connectionURL = "jdbc:mysql://localhost:3306/PomodoroDatabase";
    
    private void openConnection(){
        try {
        // Load the database driver
            Class.forName("com.mysql.jdbc.Driver");
        // Get a Connection to the database
            connection = DriverManager.getConnection(connectionURL, "root", "wordpass");
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
    }
    
    private void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertGoalsBeanObj(GoalsBean goalsBeanObj){
        openConnection();
        
        try{
            String sql = "INSERT INTO Goals(GoalID, GroupID, Username, GoalName, Description, StartTime, EndTime) VALUES("
                    + goalsBeanObj.getGoalID() + ", " + goalsBeanObj.getGroupID() + ", '" + goalsBeanObj.getUsername() + "', '" + goalsBeanObj.getGoalName() +
                    "', '" + goalsBeanObj.getGoalDescription() + "', '" + goalsBeanObj.getStartTime() + "', '" + goalsBeanObj.getStartTime() + "')";
            Statement s = connection.createStatement();
            s.executeUpdate (sql);
            s.close ();
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
        
        closeConnection();
    }
    
    public void insertGroupsBeanObj(GroupsBean groupsBeanObj){
        openConnection();
        
        try{
            int booleanAsInt = 0;
            if(groupsBeanObj.getVerifyBeforeJoining() == true){
                booleanAsInt = 1;
            }else if(groupsBeanObj.getVerifyBeforeJoining() == false){
                booleanAsInt = 0;
            }
            String sql = "INSERT INTO Groups(GroupID, GroupName, Description, VerificationBeforeJoinBoolean) VALUES("
                    + groupsBeanObj.getGroupID() + ", '" + groupsBeanObj.getGroupName() + "', '" + groupsBeanObj.getDescription() + "', " + booleanAsInt + ")";
            Statement s = connection.createStatement();
            s.executeUpdate (sql);
            s.close ();
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
        
        closeConnection();
    }
    
    public void insertUserRoleBeanObj(UserRoleBean userRoleBeanObj){
        openConnection();

        try{
            String sql = "INSERT INTO UserRoles(Username,Role) VALUES('" + userRoleBeanObj.getGivenUsername() + "','" + userRoleBeanObj.getUserRole() + "')";
            Statement s = connection.createStatement();
            s.executeUpdate (sql);
            s.close ();
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
        
        closeConnection();
    } 
    
    public void insertUsersBeanObj(UsersBean usersBeanObj){
        openConnection();

        try{
            String sql = "INSERT INTO Users(Username,LoginPassword, PomodoroLengthPreferenceMins, PomodoroShortBreakPreferenceMins, PomodoroLongBreakPreferenceMins) VALUES('" 
                    + usersBeanObj.getGivenUsername() + "','" + usersBeanObj.getGivenPassword() + "'," + usersBeanObj.getPomodoroLengthPreferenceMins() + ", " 
                    + usersBeanObj.getPomodoroShortBreakPreferenceMins() + ", " + usersBeanObj.getPomodoroLongBreakPreferenceMins() + ")";
            Statement s = connection.createStatement();
            s.executeUpdate (sql);
            s.close ();
        }catch(Exception e){
            System.out.println("Exception is ;"+e);
        }
        
        closeConnection();
    }  
    
}
