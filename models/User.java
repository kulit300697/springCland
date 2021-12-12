package edu.vinaenter.models;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class User {
	private int uid;
	@NotEmpty(message = "Vui lòng nhập username")
	private String username;
	private String password;
	private String repassword;
	private String fullname;
}
