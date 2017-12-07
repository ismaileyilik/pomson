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
import beans.UsersBean;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colton
 */
@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String modifiedUrl = "/secureUser/dashboard.jsp";
        
        if( action.equals("viewGoals")){
            modifiedUrl = "/secureUser/viewGoals.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GoalsBean> goalsList = databaseDriverObj.getGoalsOf(username);
            request.setAttribute("goalsList", goalsList);
        }
        else if( action.equals("pomodoroSession")){
            modifiedUrl = "/secureUser/pomodoroSession.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GoalsBean> goalsList = databaseDriverObj.getGoalsOf(username);
            request.setAttribute("goalsList", goalsList); 
        }
        else if( action.equals("updateGoals")){
            modifiedUrl = "/secureUser/updateGoals.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GroupsBean> membershipGroupIDList = databaseDriverObj.getMembershipOf(username);
            request.setAttribute("membershipGroupIDList", membershipGroupIDList);
        }
        else if( action.equals("createGroup")){
            modifiedUrl = "/secureUser/createGroup.jsp";
        }
        else if( action.equals("findGroups")){
            modifiedUrl = "/secureUser/findGroups.jsp";
            //TODO get pending friend request info from DB and pass it with request obj
        }
        else if( action.equals("viewGroups")){
            modifiedUrl = "/secureUser/viewGroups.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GroupsBean> groups = databaseDriverObj.getMembershipOf(username);
            request.setAttribute("groupList", groups);
        }
        else if( action.equals("findFriends")){
            modifiedUrl = "/secureUser/findFriends.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("viewFriends")){
            modifiedUrl = "/secureUser/viewFriends.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            
            ArrayList<FriendsBean> friendsList = databaseDriverObj.getFriendsOf(username);
            request.setAttribute("friendsList", friendsList);
            
            ArrayList<FriendRequestsBean> incomingFriendRequestsBeanObj = databaseDriverObj.getIncomingFriendRequests(username);
            request.setAttribute("incomingList", incomingFriendRequestsBeanObj);
            
            ArrayList<FriendRequestsBean> outgoingFriendRequestsBeanObj = databaseDriverObj.getOutgoingFriendRequests(username);
            request.setAttribute("outgoingList", outgoingFriendRequestsBeanObj);
        }
        else if(action.equals("joinGroup")){
            modifiedUrl = this.executeJoinGroup(request,response);
        }
        
        else if(action.equals("viewProfile")){
            modifiedUrl = this.executeViewProfile(request,response);
        }
        else if(action.equals("removeFriend")){
            //modifiedUrl = this.executeRemoveFriend(request,response);
        }        
        else if(action.equals("addFriend")){
            modifiedUrl = this.executeAddFriend(request,response);
        }
        else if(action.equals("denyFriendRequest")){
            modifiedUrl = this.executeDenyRequest(request,response);
        }
        else if(action.equals("viewGroup")){
            modifiedUrl = "/secureUser/viewGroup.jsp";
            String groupID = request.getParameter("groupID");
            int groupIDInt = Integer.parseInt(groupID);
            DatabaseDriver dbd = new DatabaseDriver();
            GroupsBean group = dbd.getGroup(groupIDInt);
            request.setAttribute("group", group);
        }
        else if(action.equals("leaveGroup")){
            modifiedUrl = "/secureUser/viewGroups.jsp";
            String groupID = request.getParameter("groupID");
            int groupIDInt = Integer.parseInt(groupID);
            DatabaseDriver dbd = new DatabaseDriver();
            dbd.leaveGroup(groupIDInt,request.getRemoteUser());
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GroupsBean> groups = databaseDriverObj.getMembershipOf(username);
            request.setAttribute("groupList", groups);
        }
        
        getServletContext().getRequestDispatcher(modifiedUrl).forward(request, response); 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formAction = request.getParameter("action");
        String modifiedUrl = "/index.jsp";
        
        if(formAction.equals("signupForm")){
            modifiedUrl = this.executeSignupForm(request, response);
        }
        
        else if(formAction.equals("createGroupForm")){
            modifiedUrl = this.executeCreateGroupForm(request, response);
        }
        else if(formAction.equals("findUserForm")){
            modifiedUrl = this.executeFindUserForm(request, response);
        }        
        else if(formAction.equals("findGroupsForm")){
            modifiedUrl = this.executeFindGroupsForm(request, response);
        }
        else if(formAction.equals("savePomodoroForm")){
            modifiedUrl = this.executeSavePomodoroForm(request, response);
        }
        else if(formAction.equals("updateGoalForm")){
            modifiedUrl = this.executeUpdateGoalForm(request, response);
        }
       
        getServletContext().getRequestDispatcher(modifiedUrl).forward(request, response);
    }
    
    private String executeSignupForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String urlToRedirectTo = "/index.jsp";
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        UsersBean usersBeanObj = new UsersBean();
        usersBeanObj.setUsername(username);
        usersBeanObj.setPassword(password);
        usersBeanObj.setPomodoroLengthPreferenceMins(25);
        usersBeanObj.setPomodoroShortBreakPreferenceMins(5);
        usersBeanObj.setPomodoroLongBreakPreferenceMins(10);
        databaseDriverObj.insertUsersBeanObj(usersBeanObj);
        
        return urlToRedirectTo;
    }
    
    private String executeUpdateGoalForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/dashboard.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        String Username = request.getRemoteUser();
        String goalName = request.getParameter("goalName");
        String goalDescription = request.getParameter("goalDescription");
        int groupIDToApplyTo = Integer.parseInt(request.getParameter("groupIDToApplyTo"));
        GoalsBean goalsBeanObj = new GoalsBean();
        goalsBeanObj.setGroupID(groupIDToApplyTo);
        goalsBeanObj.setUsername(Username);
        goalsBeanObj.setGoalName(goalName);
        goalsBeanObj.setGoalDescription(goalDescription);

        databaseDriverObj.insertGoalsBeanObj(goalsBeanObj);
        
        return urlToRedirectTo;
    }

    private String executeSavePomodoroForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/dashboard.jsp";
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        
        String Username = request.getRemoteUser();
        String task = request.getParameter("task");
        String comments = request.getParameter("comments");

        int goalIDToApplyTo = Integer.parseInt(request.getParameter("goalIDToApplyTo"));
        
        PomodorosBean pomodorosBeanObj = new PomodorosBean();
        pomodorosBeanObj.setGoalID(goalIDToApplyTo);
        pomodorosBeanObj.setTaskDescription(task);
        pomodorosBeanObj.setStartTime(new Timestamp(System.currentTimeMillis()));
        pomodorosBeanObj.setEndTime(new Timestamp(System.currentTimeMillis()));
        pomodorosBeanObj.setComments(comments);
        databaseDriverObj.insertPomodorosBean(pomodorosBeanObj);
        
        return urlToRedirectTo;
    }

    private String executeFindGroupsForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/findGroups.jsp";
        
        String groupName = request.getParameter("groupName");
        ArrayList<GroupsBean> foundGroupsList = new DatabaseDriver().searchForGroups(groupName);
        request.setAttribute("groupList", foundGroupsList);
        
        return urlToRedirectTo;
    }

    private String executeFindUserForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/findFriends.jsp";
        
        String queriedUsername = request.getParameter("queriedUsername");
        ArrayList<UsersBean> usersList = new DatabaseDriver().searchForUsers(queriedUsername);
        request.setAttribute("usersList", usersList);
        
        return urlToRedirectTo;
    }

    private String executeCreateGroupForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        String username = request.getRemoteUser();
        String groupName = request.getParameter("groupName");
        String description = request.getParameter("groupDescription");
        
        Boolean verifyBeforeJoining = false;
        String verifyBeforeJoiningString = request.getParameter("verifyUsersCheckBox");
        if(verifyBeforeJoiningString != null && verifyBeforeJoiningString.equals("on")){
            verifyBeforeJoining = true;
        }
        else if(verifyBeforeJoiningString == null){
            verifyBeforeJoining = false;
        }
        
        GroupsBean groupsBeanObj = new GroupsBean();
        groupsBeanObj.setGroupName(groupName);
        groupsBeanObj.setDescription(description);
        groupsBeanObj.setVerifyBeforeJoining(verifyBeforeJoining);
        databaseDriverObj.insertGroupsBeanObj(groupsBeanObj, username);
        
        ArrayList<GroupsBean> groups = databaseDriverObj.getMembershipOf(username);
        request.setAttribute("groupList", groups);

        return urlToRedirectTo;
    }

    private String executeJoinGroup(HttpServletRequest request, HttpServletResponse response) {
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        String username = request.getRemoteUser();
        int groupID = Integer.parseInt(request.getParameter("groupID"));
        GroupMembersBean newGroupMember = new GroupMembersBean();
        newGroupMember.setGroupID(groupID);
        newGroupMember.setGroupMemberUsername(username);
        newGroupMember.setGroupRole("Member");

        databaseDriverObj.insertGroupMembersBean(newGroupMember);
        //TODO call DB and get list of groups that this user is in so we can pass it onto the viewGroups.jsp
        ArrayList<GroupsBean> groups = databaseDriverObj.getMembershipOf(username);
        request.setAttribute("groupList", groups);
       
        return urlToRedirectTo;
    }
    
    private String executeViewProfile(HttpServletRequest request, HttpServletResponse response) {
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        return urlToRedirectTo;
    }
    
    private String executeDenyRequest(HttpServletRequest request, HttpServletResponse response) {
        String urlToRedirectTo = "/secureUser/viewFriends.jsp";
        FriendsBean friendsBeanObj = new FriendsBean();
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        String currentUser = request.getRemoteUser();
        String requestor = request.getParameter("username");

        friendsBeanObj.setFirstFriend(requestor);
        friendsBeanObj.setSecondFriend(currentUser);
        databaseDriverObj.denyRequest(friendsBeanObj);
        
        ArrayList<FriendsBean> friendsList = databaseDriverObj.getFriendsOf(currentUser);
        request.setAttribute("friendsList", friendsList);

        ArrayList<FriendRequestsBean> incomingFriendRequestsBeanObj = databaseDriverObj.getIncomingFriendRequests(currentUser);
        request.setAttribute("incomingList", incomingFriendRequestsBeanObj);

        ArrayList<FriendRequestsBean> outgoingFriendRequestsBeanObj = databaseDriverObj.getOutgoingFriendRequests(currentUser);
        request.setAttribute("outgoingList", outgoingFriendRequestsBeanObj);
        return urlToRedirectTo;
    }
    
    
    private String executeAddFriend(HttpServletRequest request, HttpServletResponse response) {
        String urlToRedirectTo = "/secureUser/viewFriends.jsp";
                
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        String requestor = request.getRemoteUser();
        String requestee = request.getParameter("username");
        FriendRequestsBean friendRequestsBeanObj = new FriendRequestsBean();
        friendRequestsBeanObj.setRequestor(requestor);
        friendRequestsBeanObj.setRequestee(requestee);
        databaseDriverObj.insertFriendRequestsBean(friendRequestsBeanObj);
        ArrayList<FriendsBean> friendsList = databaseDriverObj.getFriendsOf(requestor);
        request.setAttribute("friendsList", friendsList);
        
        ArrayList<FriendRequestsBean> incomingFriendRequestsBeanObj = databaseDriverObj.getIncomingFriendRequests(requestor);
        request.setAttribute("incomingList", incomingFriendRequestsBeanObj);

        ArrayList<FriendRequestsBean> outgoingFriendRequestsBeanObj = databaseDriverObj.getOutgoingFriendRequests(requestor);
        request.setAttribute("outgoingList", outgoingFriendRequestsBeanObj);
    
        return urlToRedirectTo;
    }

}
