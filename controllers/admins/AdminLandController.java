package edu.vinaenter.controllers.admins;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.commons.DAOUtil;
import edu.vinaenter.constants.MessageConstant;
import edu.vinaenter.models.Category;
import edu.vinaenter.models.Land;
import edu.vinaenter.services.CategoryService;
import edu.vinaenter.services.LandService;
import edu.vinaenter.validator.PictureValidate;

@Controller
@RequestMapping("admin/land")
public class AdminLandController {
	@Autowired
	private LandService landService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PictureValidate pictureValidate;
	
	@GetMapping("index")
	public String index(Model model) {
		List<Category> listCat = categoryService.selectAll();
		model.addAttribute("listCat", listCat);
		List<Land> listLand = landService.selectAll();
		model.addAttribute("listLands", listLand);
		return "admin.land.index";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		List<Category> listCat = categoryService.selectAll();
		model.addAttribute("listCat", listCat);
		return "admin.land.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("landErr") Land landErr, BindingResult rs,
			@ModelAttribute Land land, @RequestParam("file") MultipartFile multipartFile, 
			HttpServletRequest request, Model model, RedirectAttributes re) throws IllegalStateException, IOException{
		pictureValidate.PicValidate(multipartFile, rs);
		if (rs.hasErrors()) {
			List<Category> listCat = categoryService.selectAll();
			model.addAttribute("listCat", listCat);
			return "admin.land.add";
		}
		int add = landService.add(land, multipartFile, request);
		if (DAOUtil.isSuccess(add)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_ADD);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/land/index";
	}
	
	@GetMapping("del/{lid}")
	public String del(@PathVariable int lid, RedirectAttributes re, HttpServletRequest request) {
		int del = landService.del(lid, request);
		if (DAOUtil.isSuccess(del)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_SUCCESS_DEL);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/admin/land/index";
		
	}
}
