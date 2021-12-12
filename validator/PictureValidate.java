package edu.vinaenter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PictureValidate implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}
	public void PicValidate(MultipartFile multipartFile, Errors errors) {
		if (multipartFile.getOriginalFilename().equals("")) {
			errors.rejectValue("picture", null, "Vui lòng chọn hình ảnh");
		}
	}

}
