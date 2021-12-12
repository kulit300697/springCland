package edu.vinaenter.models;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class Land {
	private int lid;
	@NotEmpty(message = "Vui lòng nhập tên BĐS")
	private String lname;
	@NotEmpty(message = "Vui lòng nhập mô tả BĐS")
	private String description;
	private Timestamp date_create;
	private int cid;
	private String picture;
	@Positive(message = "Vui lòng nhập số lớn hơn 0 ")
	@NotNull(message = "Vui lòng nhập diện tích")
	private int area;
	@NotEmpty(message = "Vui lòng nhập địa chỉ BĐS")
	private String address;
	private int count_views;
}
