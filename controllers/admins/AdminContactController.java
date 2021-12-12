package edu.vinaenter.controllers.admins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.commons.DAOUtil;
import edu.vinaenter.constants.MessageConstant;
import edu.vinaenter.models.Contact;
import edu.vinaenter.services.ContactService;

@Controller 
@RequestMapping("admin/contact")
public class AdminContactController {
	@Autowired
	private ContactService contactService;
	@GetMapping("index")
	public String index(Model model) {
		List<Contact> listContact = contactService.selectAll();
		model.addAttribute("listContact", listContact);
		return "admin.contact.index";
	}
	
	@GetMapping("del/{cid}")
	public String del(@PathVariable int cid, RedirectAttributes re) {
		int del = contactService.del(cid);
		if (DAOUtil.isSuccess(del)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/contact/index";
	}
}
