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

import edu.neu.cs5200.ds.msn.ds.model.Comment;

public class CommentManager {
	
	DataSource ds;
	public CommentManager()
	{
	try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MovieSocialNetworkDB");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
}
public void create(Comment newComment)
{
	String sql = "insert into comment values (null,?,?)";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newComment.getComment());
		statement.setDate(2, newComment.getDate());
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
	}
public List<Comment> readAllComments()
{
	List<Comment> comments = new ArrayList<Comment>();
	String sql = "select * from comment";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while(results.next())
		{
			Comment comment = new Comment();
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("date"));
			comments.add(comment);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return comments;
}
public List<Comment> readAllCommentsForUsername(String username)
{
	List<Comment> comments = new ArrayList<Comment>();
	String sql = "select * from comment where username=?";
	try{
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			Comment comment = new Comment();
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("date"));
			comments.add(comment);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return comments;
}
public List<Comment> readAllCommentsForMovie(String movieId)
{
	List<Comment> comments = new ArrayList<Comment>();
	String sql = "select * from comment where movieId=?";
	try{
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, movieId);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			Comment comment = new Comment();
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("date"));
			comments.add(comment);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return comments;
}
public Comment readCommentForId(String commentId)
{
	Comment comment = new Comment();
	String sql = "select * from comment where commentId = ?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, commentId);
		ResultSet results = statement.executeQuery();
		if(results.next())
		{
			comment.setCommentId(results.getString("commentId"));
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("dateofbirth"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return comment;
}
public void updateComment(String commentId, String newComment)
{
	String sql = "update comment set comment=?, where commentId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, newComment);
		statement.setString(2, commentId);
		statement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	return;
}
public void deleteComment(String commentId)
{
	String sql = "delete from comment where commentId=?";
	try {
		Connection connection = ds.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, commentId);
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return;
}
}