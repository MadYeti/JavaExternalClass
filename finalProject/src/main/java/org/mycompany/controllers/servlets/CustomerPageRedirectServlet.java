package org.mycompany.controllers.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/customer", urlPatterns = "/customer")
public class CustomerPageRedirectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("client") != null){
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/customer.jsp");
        }else{
            requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/notEnoughPrivilegesPage.jsp");
        }
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

}
