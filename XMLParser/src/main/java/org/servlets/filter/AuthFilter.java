package org.servlets.filter;

import org.clients.Client;
import org.dao.clientsDAO.ClientDAO;
import org.dao.clientsDAO.DBCPDataSourceUsers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by MadYeti on 10.04.2020.
 */
@WebFilter(urlPatterns = "/")
public class AuthFilter implements Filter {

    private static boolean flag = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();
        ClientDAO clientDAO = null;
        Client client;
        Object role = null;
        try {
            clientDAO = new ClientDAO(DBCPDataSourceUsers.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(httpSession.getAttribute("login") != null && httpSession.getAttribute("password") != null){
            role = httpSession.getAttribute("role");
        }else if(login != null && password != null && (client = clientDAO.getClient(login, password)) != null){
            httpSession.setAttribute("login", client.getLogin());
            httpSession.setAttribute("password", client.getPassword());
            role = client.getRole();
            httpSession.setAttribute("role", role);
        }
        moveToMenu(req, res, role);
        /*
        Client client = null;
        if(login != null && password != null) {
            try {
                ClientDAO clientDAO = new ClientDAO(DBCPDataSourceUsers.getConnection());
                client = clientDAO.getClient(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(client != null){
            httpSession.setAttribute("login", client.getLogin());
            httpSession.setAttribute("password", client.getPassword());
            httpSession.setAttribute("role", client.getRole());
            if(client.getRole().equals("admin")){
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin.jsp");
            }else{
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/user.jsp");
            }
            requestDispatcher.forward(req, res);
        }else{
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/login.jsp");
            requestDispatcher.forward(req, res);
        }*/
    }

    public void moveToMenu(HttpServletRequest req, HttpServletResponse res, Object role) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        if(req.getServletPath().contains("/LogoutServlet") && flag){
            flag = false;
            requestDispatcher = req.getRequestDispatcher("/LogoutServlet");
        }else {
            if (role == null) {
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/login.jsp");
            } else if (role.equals("admin")) {
                flag = true;
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin.jsp");
            } else {
                flag = true;
                requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/user.jsp");
            }
        }
        requestDispatcher.forward(req, res);
    }

    @Override
    public void destroy() {

    }

}
