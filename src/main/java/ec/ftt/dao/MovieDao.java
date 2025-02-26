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
					.prepareStatement("INSERT INTO ftt.MOVIE (NAME, PRODUCER, GENRE, RELEASE_DATE, DURATION) VALUES (?, ?, ?,?, ?)");


			preparedStatement.setString(1, movie.getName());
			preparedStatement.setString(2, movie.getProducer());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setString(4, movie.getReleaseDate());
			preparedStatement.setInt(5, movie.getMovieDuration());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 

	public void deleteMovie(Long id) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM ftt.MOVIE WHERE ID=?");

			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement);
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
							+ "GENRE=?, " 
							+ "RELEASE_DATE=?, " 
							+ "DURATION=? " 
							+ "WHERE ID=?");

			preparedStatement.setString(1, movie.getName());
			preparedStatement.setString(2, movie.getProducer());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setString(4, movie.getReleaseDate());
			preparedStatement.setInt(5, movie.getMovieDuration());
			
			preparedStatement.setLong(6, movie.getId());
			System.out.println(preparedStatement);
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
				movie.setMovieDuration(rs.getInt("DURATION"));
				
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieList;
	} 





	public Movie getMovieById(Long id) {

		Movie movieOutput = new Movie();

		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT * from ftt.MOVIE WHERE ID=?");

			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				movieOutput.setId(rs.getLong("ID"));
				movieOutput.setName(rs.getString("NAME"));
				movieOutput.setProducer(rs.getString("PRODUCER"));
				movieOutput.setGenre(rs.getString("GENRE"));
				movieOutput.setReleaseDate(rs.getString("RELEASE_DATE"));
				movieOutput.setMovieDuration(rs.getInt("DURATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieOutput;
	} 

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
