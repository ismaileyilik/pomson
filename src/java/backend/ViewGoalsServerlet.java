/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import beans.UserRolesBean;
import beans.UsersBean;
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
@WebServlet("/viewGoalsServerlet")
public class ViewGoalsServerlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code to process the form and create the user account and user roles entries in the appropriate tables
        String Username = request.getRemoteUser();
        
        //Responding to the client
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>" + Username + "'s Goals</h2>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);
    }
 
}