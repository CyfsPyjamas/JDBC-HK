package edu.neu.cs5200.ds.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.ds.msn.ds.model.Movie;

public class MovieManager {
	
	DataSource ds;
	public MovieManager()
	{
	try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
}
public void create(Movie newMovie)
{
	String sql = "insert into movie values (null,?,?,?)";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newMovie.getTitle());
		statement.setString(2, newMovie.getPosterImage());
		statement.setDate(3, newMovie.getReleaseDate());
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
	}
public List<Movie> readAllMovies()
{
	List<Movie> movies = new ArrayList<Movie>();
	String sql = "select * from movie";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while(results.next())
		{
			Movie movie = new Movie();
			movie.setId(results.getString("id"));
			movie.setTitle(results.getString("title"));
			movie.setPosterImage(results.getString("posterImage"));
			movie.setReleaseDate(results.getDate("ReleaseDate"));
			movies.add(movie);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return movies;
}
public Movie readMovie(String movieId)
{
	Movie movie = new Movie();
	
	String sql = "select * from movie where movieId = ?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, movieId);
		ResultSet results = statement.executeQuery();
		if(results.next())
		{
			movie.setId(results.getString("id"));
			movie.setTitle(results.getString("title"));
			movie.setPosterImage(results.getString("posterImage"));
			movie.setReleaseDate(results.getDate("ReleaseDate"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return movie;
}
public void updateMovie(String movieId, Movie movie)
{
	String sql = "update movie set title=?, posterImage=?, ReleaseDate=? where id=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getPosterImage());
		statement.setDate(3, movie.getReleaseDate());
		statement.setString(4, movieId);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return;
}
public void deleteMovie(String movieId)
{
	String sql = "delete from movie where id=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, movieId);
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
}
}