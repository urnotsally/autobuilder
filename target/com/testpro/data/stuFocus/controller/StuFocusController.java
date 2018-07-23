package com.testpro.data.stuFocus.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testpro.data.stuFocus.bean.StuFocus;
import com.testpro.data.stuFocus.bean.StuFocusSearch;
import com.testpro.data.stuFocus.service.spi.IStuFocusService;

/**
 * Controller of StuFocus
 * @author lubo
 * @date 2018-7-23
 */
@Controller
@RequestMapping("/stuFocus")
public class StuFocusController {

	private Logger logger = Logger.getLogger(StuFocusController.class);
	
	@Autowired
	private IStuFocusService stuFocusServiceImpl;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, StuFocusSearch search){
		if (search == null) {
			search = new StuFocusSearch();
			// search.setPageSize(20);
		}
		model.addAttribute("list", stuFocusServiceImpl.page(StuFocus.class, search));
		return "data/stuFocus/list";
	}
	
	@RequestMapping(value="/goAdd", method = RequestMethod.GET)
	public String goAdd(Model model) {
		
		return "data/stuFocus/add";
		
	}
	
	@RequestMapping(value="/goUpdate/{id}", method = RequestMethod.GET)
	public String goUpdate(Model model, @PathVariable Integer id) {
		
		return "data/stuFocus/update";
	}
	
	@RequestMapping(value="/goDetails/{id}", method = RequestMethod.GET)
	public String goDetails(Model model, @PathVariable Integer id) {
		
		return "data/stuFocus/goDetails";
	}
	
	
	@RequestMapping(value="/goDel/{id}", method = RequestMethod.GET)
	public String goDel(Model model, @PathVariable Integer id) {
		
		return "data/stuFocus/del";
	}
	
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(StuFocus stuFocus) {
		stuFocusServiceImpl.save(stuFocus);
		return "redirect:/stuFocus";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(StuFocus stuFocus) {
		stuFocusServiceImpl.update(stuFocus);
		return "redirect:/stuFocus";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		stuFocusServiceImpl.delete(StuFocus.class, id);
		return "redirect:/stuFocus";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public StuFocus getJson(Model model, @PathVariable Integer id){
		return stuFocusServiceImpl.find(StuFocus.class, id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}