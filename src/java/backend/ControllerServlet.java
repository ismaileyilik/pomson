/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.GoalsBean;
import beans.GroupMembersBean;
import beans.GroupsBean;
import beans.UserRoleBean;
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
        String modifiedUrl = "/index.jsp";
        
        if( action.equals("viewGoals")){
            modifiedUrl = "/secureUser/viewGoals.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("pomodoroSession")){
            modifiedUrl = "/secureUser/pomodoroSession.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("updateGoals")){
            modifiedUrl = "/secureUser/updateGoals.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("createGroup")){
            modifiedUrl = "/secureUser/createGroup.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("findGroups")){
            modifiedUrl = "/secureUser/findGroups.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("viewGroups")){
            modifiedUrl = "/secureUser/viewGroups.jsp";
            DatabaseDriver databaseDriverObj = new DatabaseDriver();
            String username = request.getRemoteUser();
            ArrayList<GroupsBean> groups = databaseDriverObj.getMembershipOf(username);
            request.setAttribute("groupList", groups);
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("findFriends")){
            modifiedUrl = "/secureUser/findFriends.jsp";
            //TODO get infro from DB and pass it with request obj
        }
        else if( action.equals("viewFriends")){
            modifiedUrl = "/secureUser/viewFriends.jsp";
            //TODO get infro from DB and pass it with request obj
        }
         else if(action.equals("joinGroup")){
            modifiedUrl = this.executeJoinGroup(request,response);
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
        else if(formAction.equals("addFriendForm")){
            modifiedUrl = this.executeAddFriendForm(request, response);
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
        
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        UsersBean usersBeanObj = new UsersBean();
        usersBeanObj.setGivenUsername(username);
        usersBeanObj.setGivenPassword(password);
        usersBeanObj.setPomodoroLengthPreferenceMins(25);
        usersBeanObj.setPomodoroShortBreakPreferenceMins(5);
        usersBeanObj.setPomodoroLongBreakPreferenceMins(10);
        databaseDriverObj.insertUsersBeanObj(usersBeanObj);
        
        UserRoleBean userRolesBeanObj = new UserRoleBean();
        userRolesBeanObj.setGivenUsername(username);
        userRolesBeanObj.setUserRole("UserRole");
        databaseDriverObj.insertUserRoleBeanObj(userRolesBeanObj);
        
        return urlToRedirectTo;
    }
    
    private String executeUpdateGoalForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/dashboard.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String Username = request.getRemoteUser();
        String goalName = request.getParameter("goalName");
        String goalDescription = request.getParameter("goalDescription");
        int groupIDToApplyTo = Integer.parseInt(request.getParameter("groupIDToApplyTo"));
        Timestamp startTime;
        startTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime; 
        GoalsBean goalsBeanObj = new GoalsBean();
        goalsBeanObj.setGroupID(groupIDToApplyTo);
        goalsBeanObj.setUsername(Username);
        goalsBeanObj.setGoalName(goalName);
        goalsBeanObj.setGoalDescription(goalDescription);
        goalsBeanObj.setStartTime(startTime);
        goalsBeanObj.setEndTime(null);
        databaseDriverObj.insertGoalsBeanObj(goalsBeanObj);
        
        return urlToRedirectTo;
    }

    private String executeSavePomodoroForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/dashboard.jsp";
        
        String Username = request.getRemoteUser();
        String task = request.getParameter("task");
        String comments = request.getParameter("comments");
        String goalIDToApplyTo = request.getParameter("goalIDToApplyTo");
        
        
        return urlToRedirectTo;
    }

    private String executeFindGroupsForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/findGroups.jsp";
        String groupName = request.getParameter("groupName");
        ArrayList<GroupsBean> foundGroupsList = new DatabaseDriver().searchForGroups(groupName);
        request.setAttribute("groupList", foundGroupsList);
        return urlToRedirectTo;
    }

    private String executeAddFriendForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/dashboard.jsp";
        return urlToRedirectTo;
    }

    private String executeCreateGroupForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String Username = request.getRemoteUser();
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
        databaseDriverObj.insertGroupsBeanObj(groupsBeanObj);
        return urlToRedirectTo;
    }

    private String executeJoinGroup(HttpServletRequest request, HttpServletResponse response) {
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        // code to process the form and create the user account and user roles entries in the appropriate tables
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
    

}
