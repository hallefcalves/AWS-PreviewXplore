package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Movie;
import ec.ftt.util.DBUtil;

public class MovieDao {


	private Connection connection;

	public MovieDao() {
		connection = DBUtil.getConnection();
	} 

	public void addMovie(Movie movie) {

		try {


			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO ftt.MOVIE (NAME, PRODUCER, GENRE, RELEASE_DATE) VALUES (?, ?, ?,?)");


			preparedStatement.setString(1, movie.getName());
			preparedStatement.setString(2, movie.getProducer());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setString(4, movie.getReleaseDate());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

	public void deleteMovie(Long id) {

		Movie movie = new Movie();
		movie.setId(id);

		deleteMovie(movie);

	}

	public void deleteMovie(Movie movie) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM ftt.USER WHERE ID=?");

			preparedStatement.setLong(1, movie.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //deleteUser

	public void updateMovie(Movie movie) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE ftt.MOVIE SET NAME=?, " 
							+ "PRODUCER=?, " 
							+ "GENRE=? " 
							+ "RELEASE_DATE=? " 
							+ "WHERE ID=?");

			preparedStatement.setString(1, movie.getName());
			preparedStatement.setString(2, movie.getProducer());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setString(3, movie.getReleaseDate());

			preparedStatement.setLong(4, movie.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Movie> getAllMovie() {

		List<Movie> movieList = new ArrayList<Movie>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM ftt.MOVIE");
			while (rs.next()) {

				Movie movie = new Movie();

				movie.setId(rs.getLong("ID"));
				movie.setName(rs.getString("NAME"));
				movie.setProducer(rs.getString("PRODUCER"));
				movie.setGenre(rs.getString("GENRE"));
				movie.setReleaseDate(rs.getString("RELEASE_DATE"));

				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieList;
	} 

	public Movie getMovieById(Long id) {

		Movie movie = new Movie();
		movie.setId(id);

		return getMovieById(movie);

	} // getUserById long



	public Movie getMovieById(Movie movie) {

		Movie movieOutput = new Movie();

		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT * from ftt.USER WHERE ID=?");

			preparedStatement.setLong(1, movie.getId());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				movie.setId(rs.getLong("ID"));
				movie.setName(rs.getString("NAME"));
				movie.setProducer(rs.getString("PRODUCER"));
				movie.setGenre(rs.getString("GENRE"));
				movie.setReleaseDate(rs.getString("RELEASE_DATE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieOutput;
	} //getUserById

	public String getDbDate() {

		String output="";

		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT now() NOW");

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				output = rs.getString("NOW");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	} //getDbDate



}
