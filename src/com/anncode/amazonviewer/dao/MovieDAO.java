package com.anncode.amazonviewer.dao;

import static com.anncode.amazonviewer.db.DataBase.ID_TMATERIALS;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_CREATOR;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_DURATION;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_GENRE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_ID;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_TITLE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_YEAR;
import static com.anncode.amazonviewer.db.DataBase.TUSER_IDUSUARIO;
import static com.anncode.amazonviewer.db.DataBase.TVIEWED;
import static com.anncode.amazonviewer.db.DataBase.TVIEWED_ID_ELEMENT;
import static com.anncode.amazonviewer.db.DataBase.TVIEWED_ID_MATERIAL;
import static com.anncode.amazonviewer.db.DataBase.TVIEWED_ID_USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.anncode.amazonviewer.db.IDBConnetion;
import com.anncode.amazonviewer.model.Movie;

public interface MovieDAO extends IDBConnetion {
	default Movie setMovieViewed(Movie movie) {
		try (Connection connection = connectToDB()) {
			Statement statement = connection.createStatement();
			String query = "INSERT INTO " + TVIEWED + "(" + TVIEWED_ID_MATERIAL + ", " + TVIEWED_ID_ELEMENT + ", "
					+ TVIEWED_ID_USER + " "

					+ ") " + "VALUES(" + ID_TMATERIALS[0] + ", " + movie.getId() + ", " + TUSER_IDUSUARIO + ")";
			if (statement.executeUpdate(query) > 0) {
				System.out.println("Se marcò en visto.");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return movie;
	}

	default ArrayList<Movie> read() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try (Connection connection = connectToDB()) {
			String query = "SELECT * FROM " + TMOVIE;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie(rs.getString(TMOVIE_TITLE), rs.getString(TMOVIE_GENRE),
						rs.getString(TMOVIE_CREATOR), Integer.valueOf(rs.getString(TMOVIE_DURATION)),
						Short.valueOf(rs.getString(TMOVIE_YEAR)));
				movie.setId(Integer.valueOf(rs.getString(TMOVIE_ID)));
				movie.setViewed(
						this.getMovieViewed(preparedStatement, connection, Integer.valueOf(rs.getString(TMOVIE_ID))));
				movies.add(movie);
			}
		} catch (SQLException sqlException) {
			// TODO: handle exception
		}
		return movies;
	}

	private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int idMovie) {
		boolean viewed = false;
		String sql = "SELECT * FROM " + TVIEWED + " WHERE " + TVIEWED_ID_MATERIAL + "= ? " + " AND "
				+ TVIEWED_ID_ELEMENT + "= ? " + " AND " + TVIEWED_ID_USER + "= ? ";
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ID_TMATERIALS[0]);
			preparedStatement.setInt(2, idMovie);
			preparedStatement.setInt(3, TUSER_IDUSUARIO);

			rs = preparedStatement.executeQuery();

			viewed = rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return viewed;
	}
}
