package it.al.ma.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DailyTimeDao;
import it.al.ma.model.DailyTime;

@Controller
public class DailyTimeController {
	
	@Autowired
	private DailyTimeDao dailyDao; 
	
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.GET)
	public ModelAndView compileTimesheet(HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		return new ModelAndView("compileDT","formDailyTime", daily);
	}
	
	@RequestMapping(value = "/finalizeCompile", method=RequestMethod.POST)
	public String insertDaily(DailyTime daily,HttpServletRequest req) {
		daily.setIduser((int) req.getSession().getAttribute("id"));
		dailyDao.insertDaily(daily);
		return "welcome";
	}
}