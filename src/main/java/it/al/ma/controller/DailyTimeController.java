package it.al.ma.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DailyTimeDao;
import it.al.ma.model.DailyTime;

@Controller
public class DailyTimeController {
	
	@Autowired
	private DailyTimeDao DailyDao; 
	
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.GET)
	public ModelAndView compileTimesheet(ModelMap model, HttpServletRequest req) {
		
		 model.addAttribute("formDailyTime", new DailyTime());
		return new ModelAndView("user/compileDT");
	}
	
	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String insertUser(DailyTime daily, ModelMap model, HttpServletRequest req) {
		req.getSession().getAttribute("id");
		DailyDao.insertDaily(daily);
		return "redirect:user/compileDT";
	}
}