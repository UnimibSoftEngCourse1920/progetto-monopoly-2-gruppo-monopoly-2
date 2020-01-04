package controller.authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.User;

@WebServlet("/authentication/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String outputR = "UndefiniedError";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		outputR = user.registration(username, password);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		request.setAttribute("outputR", outputR);
		dispatcher.forward(request, response);
	}
}
