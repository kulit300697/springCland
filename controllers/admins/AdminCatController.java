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
import edu.vinaenter.models.Category;
import edu.vinaenter.services.CategoryService;

@Controller
@RequestMapping("admin/cat")
public class AdminCatController {
	@Autowired
	private CategoryService categoryServices;
	@GetMapping("index")
	public String index(Model model) {
		List<Category> listCat = categoryServices.selectAll();
		model.addAttribute("listCat", listCat);
		return "admin.cat.index";
	}
	
	@GetMapping("add")
	public String add() {
		return "admin.cat.add";
	}
	
	@PostMapping("add")
	public String add(Model model, @ModelAttribute Category category, 
			@Valid @ModelAttribute("catErr") Category catErr, BindingResult rs, RedirectAttributes re) {
		if (rs.hasErrors()) {
			return "admin.cat.add";
		}
		int add = categoryServices.add(category);
		if (DAOUtil.isSuccess(add)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
		} else {
			re.addFlashAttribute("err",MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/cat/index";
	}
	
	@GetMapping("edit/{cid}")
	public String edit(@PathVariable int cid, Model model) {
		Category itemCat = categoryServices.selectByID(cid);
		model.addAttribute("itemCat", itemCat);
		return "admin.cat.edit";
	}
	
	@PostMapping("edit/{cid}")
	public String edit(@PathVariable int cid,@Valid @ModelAttribute Category catErr, BindingResult rs,
			Model model, RedirectAttributes re, @ModelAttribute Category category) {
		if (rs.hasErrors()) {
			return "redirect:/admin/cat/edit/"+cid;
		}
		int edit = categoryServices.edit(category);
		if (DAOUtil.isSuccess(edit)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_EDIT);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/cat/index";
	}
	
	@GetMapping("del/{cid}")
	public String del(@PathVariable int cid, RedirectAttributes re) {
		int del = categoryServices.del(cid);
		if (DAOUtil.isSuccess(del)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
		} else {
			re.addFlashAttribute("msg", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/cat/index";
	}
}