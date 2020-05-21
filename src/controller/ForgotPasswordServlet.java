package controller;

import service.CustomerService;
import service.EmailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static service.EmailService.senderEmailID;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = "/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        CustomerService customerService = new CustomerService();
        String password = customerService.getPasswordByNameAndEmail(name, email);
        String sub = "send your password";
        String content = "your password : " + password;
        if (password == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/forgotpassword.jsp");
            request.setAttribute("message", "user or email invalid !!");
            requestDispatcher.forward(request, response);
        } else {
            EmailService emailService = new EmailService();
            emailService.send(email, sub, content, senderEmailID);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/forgotpassword.jsp");
            request.setAttribute("message", " check your email to get password !!");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
