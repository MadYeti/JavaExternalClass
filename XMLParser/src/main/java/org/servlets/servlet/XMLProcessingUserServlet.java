package org.servlets.servlet;

import org.dao.accountDAO.BankAccountDAO;
import org.dao.accountDAO.DBCPDataSource;
import org.factory.ParserFactory;
import org.parsers.Parser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.*;

/**
 * Created by MadYeti on 14.04.2020.
 */
@WebServlet(name = "/XMLProcessingUserServlet", urlPatterns = "/XMLProcessingUserServlet")
public class XMLProcessingUserServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        boolean result = false;
        String fileId = httpServletRequest.getParameter("fileid");
        String operation = httpServletRequest.getParameter("operation");
        String parserParameter = httpServletRequest.getParameter("parser");
        Parser parser = ParserFactory.createParser(parserParameter);
        BankAccountDAO bankAccountDAO = null;
        try {
            bankAccountDAO = new BankAccountDAO(DBCPDataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch (operation){
            case "readElement":
                try {
                    parser.createXMLElement(bankAccountDAO.read(parseInt(fileId)), parseInt(fileId));
                }catch (NumberFormatException e) {
                    break;
                }
                result = true;
                break;
            case "readList":
                parser.createXMLList(bankAccountDAO.getBankAccountList());
                result = true;
                break;
            default:
                break;
        }
        httpServletRequest.setAttribute("result", result);
        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/WEB-INF/view/user.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }

}
