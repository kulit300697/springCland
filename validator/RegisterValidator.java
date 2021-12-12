package edu.vinaenter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.vinaenter.models.User;

public class RegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public void validRegister(Object obj, Errors errors) {
		User userRegister = (User) obj;
		if (userRegister.getPassword().equals("")) {
			errors.rejectValue("password", null, "Vui lòng nhập mật khẩu");
		}
		if (!userRegister.getPassword().equals(userRegister.getRepassword())) {
			errors.rejectValue("repassword", null, "Không trùng với mật khẩu ở trên");
		}
		if (userRegister.getFullname().equals("")) {
			errors.rejectValue("fullname", null, "Vui lòng nhập họ và tên");
		}
	}

	public void validLogin(Object obj, Errors errors) {
		User userLogin = (User) obj;
		if (userLogin.getPassword().equals("")) {
			errors.rejectValue("password", null, "Vui lòng nhập mật khẩu");
		}
	}

	public void validAdd(Object obj, Errors errors) {
		User userAdd = (User) obj;
		if (userAdd.getPassword().equals("")) {
			errors.rejectValue("password", null, "Vui lòng nhập mật khẩu");
		}
		if (userAdd.getFullname().equals("")) {
			errors.rejectValue("fullname", null, "Vui lòng nhập họ và tên");
		}
	}

	public void validEdit(Object obj, Errors errors) {
		User userAdd = (User) obj;
		if (userAdd.getFullname().equals("")) {
			errors.rejectValue("fullname", null, "Vui lòng nhập họ và tên");
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

}
