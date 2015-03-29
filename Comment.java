package edu.neu.cs5200.ds.msn.ds.model;

import java.sql.Date;

public class Comment {
	private String commentId;
	private String comment;
	private Date date;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment(String commentId, String comment, Date date) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.date = date;
	}
	public Comment() {
		super();
	}
}
