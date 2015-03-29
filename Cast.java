package edu.neu.cs5200.ds.msn.ds.model;

public class Cast {
	private String castId;
	private String characterName;
	public String getCastId() {
		return castId;
	}
	public void setCastId(String castId) {
		this.castId = castId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Cast(String castId, String characterName) {
		super();
		this.castId = castId;
		this.characterName = characterName;
	}
	public Cast() {
		super();
	}
	
}
