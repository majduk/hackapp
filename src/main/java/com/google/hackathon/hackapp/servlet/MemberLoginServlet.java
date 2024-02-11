package com.google.hackathon.hackapp.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.hackathon.hackapp.controller.MemberLogin;
import com.google.hackathon.hackapp.model.Member;

/**
 * Servlet implementation class MemberRegistrationServlet
 */
@WebServlet("/login.do")
public class MemberLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    MemberLogin loginService;

    /**
     * Default constructor.
     */
    public MemberLoginServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder errorMessage = new StringBuilder();

        try {
            HttpSession session = request.getSession();
	    session.removeAttribute("authenticated");
            request.removeAttribute("authenticated");
            System.out.println("Email='" + request.getParameter("email") + "'");
	    if( loginService.login( request.getParameter("email"), request.getParameter("password") ) ) {
	        session.setAttribute("authenticated", true);
                request.setAttribute("authenticated", true);
                System.out.println("Email='" + request.getParameter("email") + "' authenticated");
	    }
        } catch (Exception e) {

            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }

            errorMessage.append("Error========>" + t.getMessage());
            request.setAttribute("infoMessage", "");
            e.printStackTrace();
        } finally {
            request.setAttribute("errorMessage", errorMessage.toString());
            RequestDispatcher resultView = request.getRequestDispatcher("index.jsp");
            resultView.forward(request, response);
        }
    }

}
