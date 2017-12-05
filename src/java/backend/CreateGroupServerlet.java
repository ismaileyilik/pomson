/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colton
 */
@WebServlet("/createGroupServerlet")
public class CreateGroupServerlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code to process the form and create the user account and user roles entries in the appropriate tables
        int groupID;
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
        
        //Responding to the client
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>" + Username + "'s Group</h2><br>";
        htmlResponse += groupName + "<br>" + description + "<br>" + verifyBeforeJoining + "<br>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);
    }
   
}