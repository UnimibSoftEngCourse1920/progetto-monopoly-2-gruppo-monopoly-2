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

import model.game.MonopoliGame;
import model.game.Player;
import model.user.User;

@WebServlet("/game/entry")
public class GameEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<MonopoliGame> games = new ArrayList<>();
	private int gameId=1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		
		Player user = new Player();
		user.setUsername(username);
		
		MonopoliGame game = new MonopoliGame();
		game.setGameId(gameId);
		gameId++;
		game.setPlayer(user);	
		games.add(game);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/GameInterface.jsp");
		request.setAttribute("game", game);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String outputGE;
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		
		MonopoliGame game = getGame(gameId);
		
		if(game == null) {
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			outputGE = "InexistentGame";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/UserInterface.jsp");
			request.setAttribute("outputGE", outputGE);
			request.setAttribute("user", user);
			dispatcher.forward(request, response);
		} else {
			Player user = new Player();
			user.setUsername(username);
			boolean joinGame = game.setPlayer(user);	
			
			if(joinGame) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/GameInterface.jsp");
				request.setAttribute("game", game);
				dispatcher.forward(request, response);
			} else {
				outputGE = "FullGame";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/UserInterface.jsp");
				request.setAttribute("outputGE", outputGE);
				request.setAttribute("user", user);
				dispatcher.forward(request, response);
			}
		}
	}
	
	private MonopoliGame getGame(int id) {
		
		if (games.size()==0) return null;
		
		int i = 0;
		while(games.get(i) != null) {
			if(games.get(i).getGameId() == id)
				return games.get(i);
			i++;
		}
		return null;
	}
}
