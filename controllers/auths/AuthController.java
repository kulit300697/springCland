package edu.vinaenter.controllers.auths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.commons.DAOUtil;
import edu.vinaenter.constants.MessageConstant;
import edu.vinaenter.models.User;
import edu.vinaenter.services.UserService;
import edu.vinaenter.utils.StringUtil;
import edu.vinaenter.validator.RegisterValidator;

@Controller
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private UserService userService;
	private RegisterValidator registerValidator = new RegisterValidator();
	private StringUtil stringUtil = new StringUtil();

	@GetMapping("login")
	public String login() {
		return "auth.login";
	}

	@PostMapping("login")
	public String login(@Valid @ModelAttribute("loginErr") User userErr, BindingResult rs,
			@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes re,
			Model model, HttpSession session) {
		registerValidator.validLogin(userErr, rs);
		if (rs.hasErrors()) {
			model.addAttribute("username", username);
			return "auth.login";
		}
		password = stringUtil.md5(password);
		if (userService.itUser(username, password)) {
			session.setAttribute("userLogin", userService.findByUser(username));
			model.addAttribute("msg", MessageConstant.LOGIN_SUCCESS);
			return "admin.index";
		} else {
			model.addAttribute("username", username);
			model.addAttribute("err", MessageConstant.LOGIN_ERR);
			return "auth.login";
		}
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "auth.login";
	}

	@GetMapping("register")
	public String register() {
		return "auth.register";
	}

	@PostMapping("register")
	public String register(@Valid @ModelAttribute("registerErr") User userErr, BindingResult rs, Model model,
			@ModelAttribute("user") User user, RedirectAttributes re) {
		registerValidator.validRegister(userErr, rs);
		if (rs.hasErrors()) {
			model.addAttribute("user", user);
			return "auth.register";
		}
		if (userService.hasUser(user.getUsername())) {
			user.setPassword(stringUtil.md5(user.getPassword()));
			int add = userService.add(user);
			if (DAOUtil.isSuccess(add)) {
				re.addFlashAttribute("msg", MessageConstant.REGISTER_SUCCESS);
			} else {
				re.addFlashAttribute("err", MessageConstant.REGISTER_ERR);
			}
			return "redirect:/admin/index";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("err", "Tài khoản đã tồn tại");
			return "auth.register";
		}
	}
}
