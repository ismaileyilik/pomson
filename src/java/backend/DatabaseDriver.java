/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.FriendsBean;
import beans.GoalsBean;
import beans.GroupMembersBean;
import beans.GroupsBean;
import beans.UserRoleBean;
import beans.UsersBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
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
        
        PreparedStatement ps = null;
        String sql = "INSERT INTO Goals(GroupID, Username, GoalName, Description) VALUES(?,?,?,?)";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, goalsBeanObj.getGroupID());
            ps.setString(2, goalsBeanObj.getUsername());
            ps.setString(3, goalsBeanObj.getGoalName());
            ps.setString(4, goalsBeanObj.getGoalDescription());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    
    //input needs to be sanitized, the 's in the text for the group name can cause the sql to be messed up. use prepared statement
    public void insertGroupsBeanObj(GroupsBean groupsBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        String sql = "INSERT INTO Groups(GroupName, Description, VerificationBeforeJoinBoolean) VALUES(?,?,?)" ;
        
        try{
            int booleanAsInt = 0;
            if(groupsBeanObj.getVerifyBeforeJoining() == true){
                booleanAsInt = 1;
            }else if(groupsBeanObj.getVerifyBeforeJoining() == false){
                booleanAsInt = 0;
            }
            ps = connection.prepareStatement(sql);
            ps.setString(1, groupsBeanObj.getGroupName());
            ps.setString(2, groupsBeanObj.getDescription());
            ps.setInt(3, booleanAsInt);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public void insertUserRoleBeanObj(UserRoleBean userRoleBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        String sql = "INSERT INTO UserRoles(Username,Role) VALUES(?,?)";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, userRoleBeanObj.getUsername());
            ps.setString(2, userRoleBeanObj.getUserRole());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    } 
    
    public void insertUsersBeanObj(UsersBean usersBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO Users(Username,LoginPassword, PomodoroLengthPreferenceMins, "
                    + "PomodoroShortBreakPreferenceMins, PomodoroLongBreakPreferenceMins) VALUES(?,?,?,?,?)";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, usersBeanObj.getUsername());
            ps.setString(2,usersBeanObj.getPassword());
            ps.setInt(3,usersBeanObj.getPomodoroLengthPreferenceMins());
            ps.setInt(4,usersBeanObj.getPomodoroShortBreakPreferenceMins());
            ps.setInt(5,usersBeanObj.getPomodoroLongBreakPreferenceMins());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public ArrayList<GroupsBean> searchForGroups(String groupName){
        openConnection();
        
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM Groups WHERE GroupName LIKE ?";
        ArrayList<GroupsBean> returnList = new ArrayList<>();
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+groupName+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("GroupName");
                int groupId = rs.getInt("GroupID");
                String description = rs.getString("Description");
                boolean verificationBeforeJoinBoolean = rs.getBoolean("VerificationBeforeJoinBoolean");
                GroupsBean newGroup = new GroupsBean();
                newGroup.setGroupName(name);
                newGroup.setGroupID(groupId);
                newGroup.setDescription(description);
                newGroup.setVerifyBeforeJoining(verificationBeforeJoinBoolean);
                returnList.add(newGroup);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();  
        return returnList;
    }
    
    public ArrayList<GroupsBean> getMembershipOf(String username){
        openConnection();
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM GroupMemberGroups WHERE GroupMember = ?";
        ArrayList<GroupsBean> returnList = new ArrayList<>();
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("GroupName");
                int groupId = rs.getInt("GroupID");
                String description = rs.getString("Description");
                boolean verificationBeforeJoinBoolean = rs.getBoolean("VerificationBeforeJoinBoolean");
                GroupsBean newGroup = new GroupsBean();
                newGroup.setGroupName(name);
                newGroup.setGroupID(groupId);
                newGroup.setDescription(description);
                newGroup.setVerifyBeforeJoining(verificationBeforeJoinBoolean);
                returnList.add(newGroup);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        closeConnection();  
        return returnList;
    }
    
    public void insertGroupMembersBean(GroupMembersBean groupMembersBeanObj){
        openConnection();
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO GroupMembers(GroupID, GroupMember, GroupRole) VALUES(?,?,?)";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, groupMembersBeanObj.getGroupID());
            ps.setString(2,groupMembersBeanObj.getGroupMemberUsername());
            ps.setString(3,groupMembersBeanObj.getGroupRole());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public ArrayList<FriendsBean> getFriendsOf(String username){
        openConnection();
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM Friends WHERE FirstFriend = ? OR SecondFriend = ?";
        ArrayList<FriendsBean> returnList = new ArrayList<>();
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            rs = ps.executeQuery();
            while(rs.next()){
                String firstUser = rs.getString("FirstFriend");
                String secondUser = rs.getString("SecondFriend");
                FriendsBean friendsBeanObj = new FriendsBean();
                //Ensuring that the first user is always stored as the logged in user
                if(firstUser.equals(username)){
                    friendsBeanObj.setFirstFriend(firstUser);
                    friendsBeanObj.setSecondFriend(secondUser);
                }else{
                    friendsBeanObj.setFirstFriend(secondUser);
                    friendsBeanObj.setSecondFriend(firstUser);
                }

                returnList.add(friendsBeanObj);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        closeConnection();  
        return returnList;
    }
    
    public ArrayList<UsersBean> searchForUsers(String enteredUsername){
        openConnection();
        
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM Users WHERE Username LIKE ?";
        ArrayList<UsersBean> returnList = new ArrayList<>();
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+enteredUsername+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                String username = rs.getString("Username");
                UsersBean usersBeanObj = new UsersBean();
                usersBeanObj.setUsername(username);
                returnList.add(usersBeanObj);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();  
        return returnList;
    }
    
}
