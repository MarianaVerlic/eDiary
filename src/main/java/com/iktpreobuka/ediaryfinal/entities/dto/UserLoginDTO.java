package com.iktpreobuka.ediaryfinal.entities.dto;

public class UserLoginDTO {
	
	private String username;
	private String token;
	
	public UserLoginDTO() {
		super();
	}

	
	
	public UserLoginDTO(String email, String token) {
		super();
		this.username = username;
		this.token = token;
	}



	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}


