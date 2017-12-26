/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.FriendRequestsBean;
import beans.FriendsBean;
import beans.GoalsBean;
import beans.GroupMembersBean;
import beans.GroupsBean;
import beans.PomodorosBean;
import beans.UserRoleBean;
import beans.UsersBean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    public void insertGroupsBeanObj(GroupsBean groupsBeanObj, String username){
        openConnection();
        
        CallableStatement stmt = null;
        try {
            stmt = connection.prepareCall("{CALL CreateGroupFirstTime(?,?,?,?)}");
            stmt.setString(1, groupsBeanObj.getGroupName());
            stmt.setString(2, groupsBeanObj.getDescription());
            stmt.setBoolean(3, groupsBeanObj.getVerifyBeforeJoining());
            stmt.setString(4, username);
            stmt.execute();
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
     public GroupsBean getGroup(int groupID){
        openConnection();
        
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM Groups WHERE GroupID = ?";
        GroupsBean newGroup = null;
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, groupID);
            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("GroupName");
                int groupId = rs.getInt("GroupID");
                String description = rs.getString("Description");
                boolean verificationBeforeJoinBoolean = rs.getBoolean("VerificationBeforeJoinBoolean");
                newGroup = new GroupsBean();
                newGroup.setGroupName(name);
                newGroup.setGroupID(groupId);
                newGroup.setDescription(description);
                newGroup.setVerifyBeforeJoining(verificationBeforeJoinBoolean);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection(); 
        return newGroup;
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
                Timestamp friendsSinceDate = rs.getTimestamp("FriendsSinceDate");
                FriendsBean friendsBeanObj = new FriendsBean();
                friendsBeanObj.setFriendsSinceDate(friendsSinceDate);
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
    
    public ArrayList<GoalsBean> getGoalsOf(String username){
        openConnection();
        
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "SELECT * FROM Goals WHERE Username = ?";
        ArrayList<GoalsBean> returnList = new ArrayList<>();
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                int goalID = rs.getInt("GoalID");
                int groupID = rs.getInt("GroupID");
                String goalName = rs.getString("GoalName");
                String goalDescription = rs.getString("Description");
                Timestamp startTime = rs.getTimestamp("StartTime");
                GoalsBean goalsBeanObj = new GoalsBean();
                goalsBeanObj.setGoalID(goalID);
                goalsBeanObj.setGroupID(groupID);
                goalsBeanObj.setUsername(username);
                goalsBeanObj.setGoalName(goalName);
                goalsBeanObj.setGoalDescription(goalDescription);
                goalsBeanObj.setStartTime(startTime);
                returnList.add(goalsBeanObj);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        closeConnection();  
        return returnList;
    }
    
    public void insertPomodorosBean(PomodorosBean pomodorosBeanObj){
        openConnection();
        PreparedStatement ps = null;
        
        
        String sql = "INSERT INTO Pomodoros(GoalID, TaskDescription, StartTime, EndTime, Comments) VALUES(?,?,?,?,?)";
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pomodorosBeanObj.getGoalID());
            ps.setString(2,pomodorosBeanObj.getTaskDescription());
            ps.setTimestamp(3,pomodorosBeanObj.getStartTime());
            ps.setTimestamp(4, pomodorosBeanObj.getEndTime());
            ps.setString(5, pomodorosBeanObj.getComments());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public void insertFriendRequestsBean(FriendRequestsBean friendRequestsBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO FriendRequests(Requestor, Requestee, AcceptedStatusBoolean) VALUES(?,?,?)";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, friendRequestsBeanObj.getRequestor());
            ps.setString(2,friendRequestsBeanObj.getRequestee());
            ps.setInt(3,friendRequestsBeanObj.getAcceptedStatusBoolean());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public ArrayList<FriendRequestsBean> getIncomingFriendRequests(String username){
        openConnection();
        
        PreparedStatement ps = null;
        ArrayList<FriendRequestsBean> returnList = new ArrayList<>();
        String sql = "SELECT * FROM FriendRequests fr WHERE fr.Requestor <> ? and fr.Requestee = ?";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String requestor = rs.getString("requestor");
                String requestee = rs.getString("requestee");
                FriendRequestsBean friendRequestsBeanObj = new FriendRequestsBean();
                friendRequestsBeanObj.setRequestee(requestee);
                friendRequestsBeanObj.setRequestor(requestor);
                returnList.add(friendRequestsBeanObj);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        return returnList;
    }
    
    public ArrayList<FriendRequestsBean> getOutgoingFriendRequests(String username){
        openConnection();
        
        PreparedStatement ps = null;
        ArrayList<FriendRequestsBean> returnList = new ArrayList<>();
        String sql = "SELECT * FROM FriendRequests fr WHERE fr.Requestor = ? and fr.Requestee <> ?";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String requestor = rs.getString("requestor");
                String requestee = rs.getString("requestee");
                FriendRequestsBean friendRequestsBeanObj = new FriendRequestsBean();
                friendRequestsBeanObj.setRequestee(requestee);
                friendRequestsBeanObj.setRequestor(requestor);
                returnList.add(friendRequestsBeanObj);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        return returnList;
    }
    
    public void cancelFriendship(FriendsBean friendsBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        ArrayList<FriendRequestsBean> returnList = new ArrayList<>();
        String sql = "DELETE FROM Friends fr WHERE (fr.Firstfriend = ? OR fr.SecondFriend = ?) AND (fr.FirstFriend = ? OR fr.SecondFriend = ?)";
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, friendsBeanObj.getFirstFriend());
            ps.setString(2, friendsBeanObj.getFirstFriend());
            ps.setString(3, friendsBeanObj.getSecondFriend());
            ps.setString(4, friendsBeanObj.getSecondFriend());
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public void denyRequest(FriendsBean friendsBeanObj){
        openConnection();
        
        PreparedStatement ps = null;
        ArrayList<FriendRequestsBean> returnList = new ArrayList<>();
        String sql = "DELETE FROM FriendRequests WHERE (Requestor = ? OR Requestee = ?) AND (Requestor = ? OR Requestee = ?)";

        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, friendsBeanObj.getFirstFriend());
            ps.setString(2, friendsBeanObj.getFirstFriend());
            ps.setString(3, friendsBeanObj.getSecondFriend());
            ps.setString(4, friendsBeanObj.getSecondFriend());
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }

    public void leaveGroup(int groupIDInt,String username) {
        openConnection();
        
        PreparedStatement ps = null;
        String sql = "DELETE FROM GroupMembers WHERE (groupID = ? AND GroupMember = ?)";

        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, groupIDInt);
            ps.setString(2, username);
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    public UsersBean getUserPreferences(String username){
        openConnection();
        
        PreparedStatement ps = null;
        String sql = "SELECT * FROM Users u WHERE u.Username = ?";
        UsersBean usersBeanObj = new UsersBean();
        
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            
            while(rs.next()){
                int pomodoroLengthPreferenceMins = rs.getInt("PomodoroLengthPreferenceMins");
                int pomodoroShortBreakPreferenceMins = rs.getInt("PomodoroShortBreakPreferenceMins");
                int pomodoroLongBreakPreferenceMins = rs.getInt("PomodoroLongBreakPreferenceMins");
                usersBeanObj.setPomodoroLengthPreferenceMins(pomodoroLengthPreferenceMins);
                usersBeanObj.setPomodoroShortBreakPreferenceMins(pomodoroShortBreakPreferenceMins);
                usersBeanObj.setPomodoroLongBreakPreferenceMins(pomodoroLongBreakPreferenceMins);

            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
        return usersBeanObj;
    }
    
    public void updateUserPreferences(UsersBean usersBeanObj){
            openConnection();

            PreparedStatement ps = null;
            String sql = "UPDATE Users u SET PomodoroLengthPreferenceMins = ?,  PomodoroShortBreakPreferenceMins = ?, "
                    + "PomodoroLongBreakPreferenceMins = ? WHERE u.Username = ?";

            try{
                ps = connection.prepareStatement(sql);
                ps.setInt(1, usersBeanObj.getPomodoroLengthPreferenceMins());
                ps.setInt(2, usersBeanObj.getPomodoroShortBreakPreferenceMins());
                ps.setInt(3, usersBeanObj.getPomodoroLongBreakPreferenceMins());
                ps.setString(4, usersBeanObj.getUsername());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            closeConnection();
            
    }
    
}
