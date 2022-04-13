package com.anncode.amazonviewer.db;

public class DataBase {
	public static final String URL = "jdbc:mysql://localhost:3306/";
	public static final String DB = "amazonviewer";
	public static final String USER = "amazonviewer";
	public static final String PASSWORD = "amazonviewer";
	// Mapeo de la tabla Movie.
	public static final String TMOVIE = "movie";
	public static final String TMOVIE_ID = "id";
	public static final String TMOVIE_TITLE = "title";
	public static final String TMOVIE_GENRE = "genre";
	public static final String TMOVIE_CREATOR = "creator";
	public static final String TMOVIE_DURATION = "duration";
	public static final String TMOVIE_YEAR = "year";
	// Mapeo de la tabla Material
	public static final String TMATERIAL = "material";
	public static final String TMATERIAL_ID = "id";
	public static final String TMATERIAL_NAME = "name";
	public static final int[] ID_TMATERIALS = { 1, 2, 3, 4, 5 };
	// Mapeo de la tabla User
	public static final String TUSER = "user";
	public static final String TUSER_ID = "id";
	public static final String TUSER_NAME = "name";
	public static final int TUSER_IDUSUARIO = 1;
	// Mapeo de la tabla Viewed
	public static final String TVIEWED = "viewed";
	public static final String TVIEWED_ID = "id";
	public static final String TVIEWED_ID_MATERIAL = "id_material";
	public static final String TVIEWED_ID_ELEMENT = "id_element";
	public static final String TVIEWED_ID_USER = "id_user";
}
