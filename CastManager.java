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

import edu.neu.cs5200.ds.msn.ds.model.Cast;

public class CastManager {
	
	DataSource ds;
	public CastManager()
	{
	try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
}
public void create(Cast newCast)
{
	String sql = "insert into cast values (null,?)";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newCast.getCharacterName());
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
	}
public List<Cast> readAllCasts()
{
	List<Cast> casts = new ArrayList<Cast>();
	String sql = "select * from cast";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while(results.next())
		{
			Cast cast = new Cast();
			cast.setCharacterName(results.getString("characterName"));
			casts.add(cast);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return casts;
}
public List<Cast> readAllCastsForActor(String actorId)
{
	List<Cast> casts = new ArrayList<Cast>();
	String sql = "select * from cast where actorId=?";
	try{
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, actorId);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			Cast cast = new Cast();
			cast.setCharacterName(results.getString("characterName"));
			casts.add(cast);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return casts;
}
public List<Cast> readAllCastsForMovie(String movieId)
{
	List<Cast> casts = new ArrayList<Cast>();
	String sql = "select * from cast where movieId=?";
	try{
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, movieId);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			Cast cast = new Cast();
			cast.setCharacterName(results.getString("characterName"));
			casts.add(cast);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return casts;
}
public Cast readCastForId(String castId)
{
	Cast cast = new Cast();
	String sql = "select * from cast where castId = ?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, castId);
		ResultSet results = statement.executeQuery();
		if(results.next())
		{
			cast.setCastId(results.getString("castId"));
			cast.setCharacterName(results.getString("characterName"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return cast;
}
public void updateCast(String castId, Cast newCast)
{
	String sql = "update cast set cast=?, where castId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newCast.getCharacterName());
		statement.setString(2, castId);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return;
}
public void deleteCast(String castId)
{
	String sql = "delete from cast where castId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, castId);
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
}
}