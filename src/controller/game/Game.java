package controller.game;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.User;
import model.game.MonopoliGame;


@WebServlet("/game/entrygame")
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<MonopoliGame> games = new ArrayList<>();
	private int idGameCounter = 1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		
		System.out.println(id);
		
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		
		MonopoliGame game = new MonopoliGame();
		game.setPlayer(user);	
		game.setIdGame(idGameCounter);
		idGameCounter++;
		games.add(game);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/GameInterface.jsp");
		request.setAttribute("game", game);
		dispatcher.forward(request, response);
	}

}
