/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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
@WebServlet("/signupServerlet")
public class SignupServerlet extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // code to process the form...
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        UsersBean UsersBeanObj = new UsersBean();
        UsersBeanObj.setGivenUserName(username);
        UsersBeanObj.setGivenPassword(password);
        UsersBeanObj.createAccount();
        System.out.println("ACCOUNT CREATED!!!! for @" + username + " " + password);
        
        //Responding to the client
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";
        htmlResponse += "<h2>Your username is: " + username + "</h2>";
        htmlResponse += "</html>";

        writer.println(htmlResponse);
    }
 
}
