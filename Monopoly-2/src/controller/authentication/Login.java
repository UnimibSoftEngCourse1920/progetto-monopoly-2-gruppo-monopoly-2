package controller.authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.User;

@WebServlet("/authentication/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String outputL = "UndefiniedError";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		outputL = user.login(username, password);

		if(outputL.equals("CorrectLogin")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/UserInterface.jsp");
			request.setAttribute("user", user);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			request.setAttribute("outputL", outputL);
			dispatcher.forward(request, response);
		}
	}
}
