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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colton
 */
@WebServlet("/controllerServerlet")
public class ControllerServerlet extends HttpServlet {
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
        String urlToRedirectTo = "/dashboard.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String Username = request.getRemoteUser();
        String goalName = request.getParameter("goalName");
        String goalDescription = request.getParameter("goalDescription");
        int groupIDToApplyTo = Integer.parseInt(request.getParameter("groupIDToApplyTo"));
        Date today = new java.util.Date();
        Time startTime;
        Time endTime; 
        startTime = new Time(today.getTime());
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
        String urlToRedirectTo = "/dashboard.jsp";
        
        String Username = request.getRemoteUser();
        String task = request.getParameter("task");
        String comments = request.getParameter("comments");
        String goalIDToApplyTo = request.getParameter("goalIDToApplyTo");
        
        
        return urlToRedirectTo;
    }

    private String executeFindGroupsForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/dashboard.jsp";
        return urlToRedirectTo;
    }

    private String executeAddFriendForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/dashboard.jsp";
        return urlToRedirectTo;
    }

    private String executeCreateGroupForm(HttpServletRequest request, HttpServletResponse response){
        String urlToRedirectTo = "/secureUser/viewGroups.jsp";
        
        DatabaseDriver databaseDriverObj = new DatabaseDriver();
        // code to process the form and create the user account and user roles entries in the appropriate tables
        int groupID = 1;
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
        groupsBeanObj.setGroupID(groupID);
        groupsBeanObj.setGroupName(groupName);
        groupsBeanObj.setDescription(description);
        groupsBeanObj.setVerifyBeforeJoining(verifyBeforeJoining);
        databaseDriverObj.insertGroupsBeanObj(groupsBeanObj);
        return urlToRedirectTo;
    }

}
