package edu.vinaenter.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Contact {
	private int cid;
	@NotEmpty(message = "Vui lòng nhập họ và tên")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập email")
	@Email(message = "Email không đúng định dạng")
	private String email;
	@NotEmpty(message = "Vui lòng nhập chủ đề")
	private String subject;
	@NotEmpty(message = "Vui lòng nhập nội dung")
	private String content;
}
