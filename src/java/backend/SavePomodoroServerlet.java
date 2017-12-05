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
@WebServlet("/savePomodoroServerlet")
public class SavePomodoroServerlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String Username = request.getRemoteUser();
        String task = request.getParameter("task");
        String comments = request.getParameter("comments");
        String goalIDToApplyTo = request.getParameter("goalIDToApplyTo");
        
        
        //Responding to the client
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>" + Username + "'s Pomodoro</h2><br>";
        htmlResponse += task + "<br>" + comments + "<br>" + goalIDToApplyTo + "<br>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);
    }
   
}