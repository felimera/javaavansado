package com.anncode.amazonviewer.dao;

import static com.anncode.amazonviewer.db.DataBase.TMOVIE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_CREATOR;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_DURATION;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_GENRE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_ID;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_TITLE;
import static com.anncode.amazonviewer.db.DataBase.TMOVIE_YEAR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.anncode.amazonviewer.db.IDBConnetion;
import com.anncode.amazonviewer.model.Movie;

public interface MovieDAO extends IDBConnetion {
	default Movie setMovieViewed(Movie movie) {
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
				movies.add(movie);
			}
		} catch (SQLException sqlException) {
			// TODO: handle exception
		}
		return movies;
	}

	private boolean getMovieViewed() {
		return false;
	}
}
