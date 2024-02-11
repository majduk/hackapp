package com.google.hackathon.hackapp.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.hackathon.hackapp.controller.MemberRegistration;
import com.google.hackathon.hackapp.data.MemberListProducer;
import com.google.hackathon.hackapp.model.Member;

/**
 * Servlet implementation class MemberRegistrationServlet
 */
@WebServlet("/register.do")
public class MemberRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    MemberRegistration registrationService;

    @Inject
    MemberListProducer memberListService;

    /**
     * Default constructor.
     */
    public MemberRegistrationServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder errorMessage = new StringBuilder();

        try {

            System.out.println("EMAIL='" + request.getParameter("email") + "'");
            Member member;
            // create a new member, remember :) the memberservice do not
            // call the initMethod if an error occur during the previous persist request

            while ((member = registrationService.getNewMember()) == null) {
                registrationService.initNewMember();
            }

            String value;

            if ((value = request.getParameter("name")).length() < 1) {
                errorMessage.append("Name can not be null\n");
            } else {
                member.setName(value);

                if ((value = request.getParameter("email")).length() < 1) {
                    errorMessage.append("email required!\n");
                } else {
                    member.setEmail(value);

                    if ((value = request.getParameter("password")).length() < 1) {
                        errorMessage.append("password required \n");
                    } else // all parameters are filled, register
                    {
                        member.setPassword(value);

                        log("\n*****************Try Registration of Member=" + member);
                        registrationService.register();
                        request.setAttribute("infoMessage", member.getName() + " Registered!");
                    }
                }
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
