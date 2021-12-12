package edu.vinaenter.models;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Category {
	private int cid;
	@NotEmpty(message = "Vui lòng nhập tên danh mục")
	private String cname;
	
}
