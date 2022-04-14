package com.anncode.amazonviewer.dao;

import static com.anncode.amazonviewer.db.DataBase.*;

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
				movie.setViewed(this.getMovieViewed(preparedStatement, connection, Integer.valueOf(rs.getString(TMOVIE_ID))));
				movies.add(movie);
			}
		} catch (SQLException sqlException) {
			// TODO: handle exception
		}
		return movies;
	}

	private boolean getMovieViewed(PreparedStatement preparedStatement,Connection connection,int idMovie) {
		boolean viewed=false;
		String sql="SELECT * FROM "+TVIEWED+
				" WHERE "+
				TVIEWED_ID_MATERIAL+"= ? "+
				" AND "+
				TVIEWED_ID_ELEMENT+"= ? "+
				" AND "+
				TVIEWED_ID_USER+"= ? ";
		ResultSet rs=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, ID_TMATERIALS[0]);
			preparedStatement.setInt(2, idMovie);
			preparedStatement.setInt(3,TUSER_IDUSUARIO);
			
			rs=preparedStatement.executeQuery();
			
			viewed=rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return viewed;
	}
}
