package ec.ftt.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.MovieDao;
import ec.ftt.model.Movie;

@WebServlet("/movie")
public class MovieApi extends HttpServlet  {
	private static final long serialVersionUID = 3L;

	public MovieApi() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String movieId = request.getParameter("movie-id");

		if(movieId != null) {
			long id = Long.valueOf(movieId);

			MovieDao movieDao = new MovieDao();

			Movie movie = movieDao.getMovieById(id);
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(movie));

		} else {

			MovieDao movieDao = new MovieDao();

			List<Movie> movies = movieDao.getAllMovie();

			Gson gson = new Gson();

			response.getWriter().append(gson.toJson(movies));
		} 


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Movie u = new Movie(
				request.getParameter("movie-name"),
				request.getParameter("movie-producer"),
				request.getParameter("movie-genre"),
				request.getParameter("movie-releaseDate"),
				Integer.parseInt(request.getParameter("movie-duration"))
				);
		MovieDao movieDao = new MovieDao();

		movieDao.addMovie(u);

		response.sendRedirect("list-movies.html");

	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Movie u = new Movie(
				Long.parseLong(request.getParameter("movie-id")),
				request.getParameter("movie-name"),
				request.getParameter("movie-producer"),
				request.getParameter("movie-genre"),
				request.getParameter("movie-releaseDate"),
				Integer.parseInt(request.getParameter("movie-duration"))
				);
		MovieDao movieDao = new MovieDao();

		movieDao.updateMovie(u);

		response.getWriter().append(u.toString());


	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		if (request.getParameter("movie-id") == null)
			response.sendError(407, "Informe o ID do usu�rio a ser retornado!!!" );
		else {
			Long movieId = Long.valueOf(request.getParameter("movie-id"));
			System.out.println(movieId);



			MovieDao ud = new MovieDao();

			ud.deleteMovie(movieId);
			response.setStatus(200);
			response.getWriter().append(request.getParameter("movie-id") + " Movie removido");
		}
	}

}
