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

import edu.neu.cs5200.ds.msn.ds.model.User;

public class UserManager {
	
	DataSource ds;
	public UserManager()
	{
	try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
}
public void create(User newUser)
{
	String sql = "insert into User values (?,?,?,?,?,?)";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newUser.getUsername());
		statement.setString(2, newUser.getPassword());
		statement.setString(3, newUser.getFirstName());
		statement.setString(4, newUser.getLastName());
		statement.setString(5, newUser.getEmail());
		statement.setDate(6, newUser.getDateOfBirth());		
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
	}
public List<User> readAllUsers()
{
	List<User> users = new ArrayList<User>();
	String sql = "select * from User";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while(results.next())
		{
			User user = new User();
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("password"));
			user.setFirstName(results.getString("firstname"));
			user.setLastName(results.getString("lastname"));
			user.setEmail(results.getString("email"));
			user.setDateOfBirth(results.getDate("dateOfBirth"));
			users.add(user);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return users;
}
public User readUser(String username)
{
	User user = new User();
	
	String sql = "select * from user where username = ?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		if(results.next())
		{
			user.setUsername(results.getString("username"));
			user.setFirstName(results.getString("firstname"));
			user.setLastName(results.getString("lastname"));
			user.setPassword(results.getString("password"));
			user.setEmail(results.getString("email"));
			user.setDateOfBirth(results.getDate("birth"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return user;
}
public void updateUser(String username, User user)
{
	String sql = "update user set password=?, firstname=?, lastname=? email=? dateofbirth=? where username=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, user.getPassword());
		statement.setString(2, user.getFirstName());
		statement.setString(3, user.getLastName());
		statement.setString(4, user.getEmail());
		statement.setDate(5, user.getDateOfBirth());
		statement.setString(6, username);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return;
}
public void deleteUser(String username)
{
	String sql = "delete from user where username=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
}
}