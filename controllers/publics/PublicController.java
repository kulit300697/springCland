package edu.vinaenter.controllers.publics;

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
import edu.vinaenter.models.Contact;
import edu.vinaenter.models.Land;
import edu.vinaenter.services.CategoryService;
import edu.vinaenter.services.ContactService;
import edu.vinaenter.services.LandService;

@Controller
@RequestMapping("cland")
public class PublicController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LandService landService;
	@Autowired
	private ContactService contactService;
	
	@ModelAttribute
	public void setCat(Model model) {
		List<Category> listCat = categoryService.selectAll();
		model.addAttribute("listCat", listCat);
		List<Category> listCatHot = categoryService.selectCatHot();
		model.addAttribute("listCatHot", listCatHot);
		List<Land> listLandNew = landService.selectAllDateNew();
		model.addAttribute("listLandNew", listLandNew);
		List<Land> listLandCountView = landService.selectAllCountView();
		model.addAttribute("listLandCountView", listLandCountView);
	}
	
	@GetMapping("index")
	public String index(Model model) {
		List<Land> listLand = landService.selectAll();
		model.addAttribute("listLand", listLand);
		List<Land> listSlider = landService.selectSlider();
		model.addAttribute("listSlider", listSlider);
		return "cland.index";
	}
	
	@GetMapping("cat/{cid}")
	public String cat(@PathVariable int cid, Model model) {
		List<Land> listLandByCid = landService.selectByCid(cid);
		model.addAttribute("listLandByCid", listLandByCid);
		model.addAttribute("cid", cid);
		return "cland.cat";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "cland.contact";
	}
	
	@PostMapping("contact")
	public String contact(@Valid @ModelAttribute("contactErr") Contact contactErr, BindingResult rs,
			@ModelAttribute Contact contact, Model model, RedirectAttributes re) {
		if (rs.hasErrors()) {
			model.addAttribute("contactErr", contactErr);
			return "cland.contact";
		}
		int add = contactService.add(contact);
		if (DAOUtil.isSuccess(add)) {
			re.addFlashAttribute("msg", MessageConstant.MSG_CONTACT_ADD);
		} else {
			re.addFlashAttribute("err", MessageConstant.MSG_ERR);
		}
		return "redirect:/cland/contact";
	}
	
	@GetMapping("detail")
	public String detail() {
		return "cland.detail";
	}
}
