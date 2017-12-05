/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.GoalsBean;
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
@WebServlet("/updateGoalsServerlet")
public class UpdateGoalsServerlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        goalsBeanObj.saveGoal();
        
        
        
        //Responding to the client
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>" + Username + "'s Goal</h2><br>";
        htmlResponse += goalName + "<br>" + goalDescription + "<br>" + groupIDToApplyTo + "<br>"
                + goalsBeanObj.getGoalID() + "<br>" + startTime + "<br>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);
    }
   
}