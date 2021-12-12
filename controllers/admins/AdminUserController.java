package edu.vinaenter.controllers.admins;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.commons.DAOUtil;
import edu.vinaenter.constants.MessageConstant;
import edu.vinaenter.models.User;
import edu.vinaenter.services.UserService;
import edu.vinaenter.utils.StringUtil;
import edu.vinaenter.validator.RegisterValidator;

@Controller 
@RequestMapping("admin/user")
public class AdminUserController {
	@Autowired
	private UserService userService;
	private RegisterValidator registerValidator = new RegisterValidator();
	private StringUtil stringUtil = new StringUtil();
	
	@GetMapping("index")
	public String index(Model model) {
		List<User> listUser = userService.selectAll();
		model.addAttribute("listUser", listUser);
		return "admin.user.index";
	}
	
	@GetMapping("add")
	public String add() {
		return "admin.user.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("userErr") User userErr, BindingResult rs, Model model,
			@ModelAttribute("user") User user, RedirectAttributes re) {
		registerValidator.validAdd(userErr, rs);
		if (rs.hasErrors()) {
			model.addAttribute("user", user);
			return "admin.user.add";
		}
		if (userService.hasUser(user.getUsername())) {
			user.setPassword(stringUtil.md5(user.getPassword()));
			int add = userService.add(user);
			if (DAOUtil.isSuccess(add)) {
				re.addFlashAttribute("msg", MessageConstant.REGISTER_SUCCESS);
			} else {
				re.addFlashAttribute("err", MessageConstant.REGISTER_ERR);
			}
			return "redirect:/admin/user/index";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("err", "Tài khoản đã tồn tại");
			return "admin.user.add";
		}
	}
	
	@GetMapping("del/{uid}")
	public String del(@PathVariable int uid, RedirectAttributes re) {
		if (uid == 1) {
			re.addFlashAttribute("msg", MessageConstant.MSG_DEL_ADMIN);
			return "redirect:/admin/user/index";
		} else {
			int del = userService.del(uid);
			if (DAOUtil.isSuccess(del)) {
				re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
			} else {
				re.addFlashAttribute("msg", MessageConstant.MSG_ERR);
			}
			return "redirect:/admin/user/index";
		}
	}
	@GetMapping("edit/{uid}")
	public String edit(@PathVariable int uid, Model model) {
		User user = userService.selectByID(uid);
		model.addAttribute("user", user);
		return "admin.user.edit";
	}
	
	@PostMapping("edit/{uid}")
	public String edit(@PathVariable int uid ,@ModelAttribute User user, RedirectAttributes re,
			@Valid @ModelAttribute User userErr, BindingResult rs) {
		registerValidator.validEdit(userErr, rs);
		if (rs.hasErrors()) {
			re.addFlashAttribute("user", user);
			return "redirect:/admin/user/edit/"+uid;
		}
		int edit = userService.edit(user);
		if (DAOUtil.isSuccess(edit)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_EDIT);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/user/index";
	}
}
