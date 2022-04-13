package com.anncode.amazonviewer.db;

import static com.anncode.amazonviewer.db.DataBase.DB;
import static com.anncode.amazonviewer.db.DataBase.PASSWORD;
import static com.anncode.amazonviewer.db.DataBase.URL;
import static com.anncode.amazonviewer.db.DataBase.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public interface IDBConnetion {
	default Connection connectToDB() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
			if (!Objects.isNull(connection)) {
				System.out.println("Se establecio la conexion :)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return connection;
		}
	}
}
