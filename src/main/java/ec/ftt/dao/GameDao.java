package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ec.ftt.model.Game;
import ec.ftt.util.DBUtil;

public class GameDao {


	private Connection connection;

	public GameDao() {
		connection = DBUtil.getConnection();
	} 

	public void addGame(Game game) {

		try {


			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO ftt.GAME (NAME, PRODUCER, GENRE, RELEASE_DATE) VALUES (?, ?, ?, ?)");

			preparedStatement.setString(1, game.getName());
			preparedStatement.setString(2, game.getProducer());
			preparedStatement.setString(3, game.getGenre());
			preparedStatement.setString(4, game.getReleaseDate());


			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 


	public void deleteGame(Long id) {
		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM ftt.GAME WHERE ID=?");

			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //deleteUser

	public void updateGame(Game game) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("UPDATE ftt.GAME SET NAME=?, " 
							+ "PRODUCER=?, " 
							+ "GENRE=?, " 
							+ "RELEASE_DATE=? " 
							+ "WHERE ID=?");
	        
			preparedStatement.setString(1, game.getName());
			preparedStatement.setString(2, game.getProducer());
			preparedStatement.setString(3, game.getGenre());
			preparedStatement.setString(4, game.getReleaseDate());

			preparedStatement.setLong(5, game.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Game> getAllGame() {

		List<Game> gameList = new ArrayList<Game>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM ftt.GAME");
			while (rs.next()) {

				Game game = new Game();

				game.setId(rs.getLong("ID"));
				game.setName(rs.getString("NAME"));
				game.setProducer(rs.getString("PRODUCER"));
				game.setGenre(rs.getString("GENRE"));
				game.setReleaseDate(rs.getString("RELEASE_DATE"));

				gameList.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gameList;
	} 


	public Game getGameById(Long id) {

		Game gameOutput = new Game();

		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("SELECT * from ftt.GAME WHERE ID=?");

			preparedStatement.setLong(1,id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				gameOutput.setId(rs.getLong("ID"));
				gameOutput.setName(rs.getString("NAME"));
				gameOutput.setProducer(rs.getString("PRODUCER"));
				gameOutput.setGenre(rs.getString("GENRE"));
				gameOutput.setReleaseDate(rs.getString("RELEASE_DATE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gameOutput;
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
