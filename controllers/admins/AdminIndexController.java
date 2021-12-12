package edu.vinaenter.controllers.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.vinaenter.services.CategoryService;
import edu.vinaenter.services.ContactService;
import edu.vinaenter.services.LandService;
import edu.vinaenter.services.UserService;

@Controller
@RequestMapping("admin")
public class AdminIndexController {
	@Autowired 
	private CategoryService categoryService;
	@Autowired 
	private ContactService contactService;
	@Autowired 
	private LandService landService;
	@Autowired 
	private UserService userService;
	@GetMapping("index")
	public String index(Model model) {
		int countCat = categoryService.countAll();
		int countUser = userService.countAll();
		int countLand = landService.countAll();
		int countContact = contactService.countAll();
		model.addAttribute("countCat", countCat);
		model.addAttribute("countUser", countUser);
		model.addAttribute("countLand", countLand);
		model.addAttribute("countContact", countContact);
		return "admin.index";
	}
}
