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

import edu.neu.cs5200.ds.msn.ds.model.Actor;

public class ActorManager {
	
	DataSource ds;
	public ActorManager()
	{
	try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
}
public void create(Actor newActor)
{
	String sql = "insert into Actor values (null,?,?,?)";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newActor.getFirstName());
		statement.setString(2, newActor.getLastName());
		statement.setDate(3, newActor.getDateOfBirth());	
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
	}
public List<Actor> readAllActors()
{
	List<Actor> actors = new ArrayList<Actor>();
	String sql = "select * from Actor";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while(results.next())
		{
			Actor actor = new Actor();
			actor.setFirstName(results.getString("firstname"));
			actor.setLastName(results.getString("lastname"));
			actor.setDateOfBirth(results.getDate("dateofbirth"));
			actors.add(actor);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return actors;
}
public Actor readActor(String actorId)
{
	Actor actor = new Actor();
	
	String sql = "select * from user where actorId = ?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, actorId);
		ResultSet results = statement.executeQuery();
		if(results.next())
		{
			actor.setFirstName(results.getString("firstname"));
			actor.setLastName(results.getString("lastname"));
			actor.setDateOfBirth(results.getDate("birth"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return actor;
}
public void updateActor(String actorId, Actor actor)
{
	String sql = "update actor set firstname=?, lastname=? dateofbirth=? where actorId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, actor.getFirstName());
		statement.setString(2, actor.getLastName());
		statement.setDate(3, actor.getDateOfBirth());
		statement.setString(4, actorId);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return;
}
public void deleteActor(String actorId)
{
	String sql = "delete from user where actorId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, actorId);
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
}
}