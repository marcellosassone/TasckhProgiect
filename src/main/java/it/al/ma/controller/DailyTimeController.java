package it.al.ma.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DailyTimeDao;
import it.al.ma.model.DailyTime;
import it.al.ma.model.User;

@Controller
public class DailyTimeController {
	
	@Autowired
	private DailyTimeDao dailyDao; 
	
	/***
	 * metodo viene richiamato la prima volta dalla pagina dell'Utente 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.GET)
	public ModelAndView compileTimesheet(HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		User user= new User();
		user.setId((int) req.getSession().getAttribute("id"));
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 1);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("formListTime", dailyDao.findByIdUser(user, start, end));
		return modelV;
	}
	@RequestMapping(value = "user/compileTimesheet?currMonth={currMonth}", method = RequestMethod.GET)
	public ModelAndView compileTimesheet(@PathVariable("currMonth") int month, HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		User user= new User();
		user.setId((int) req.getSession().getAttribute("id"));
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.MONTH,month);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		return modelV;
	}
	@RequestMapping(value = "user/finalizeCompile", method=RequestMethod.POST)
	public String insertDaily(DailyTime daily,HttpServletRequest req) {
		daily.setIduser((int) req.getSession().getAttribute("id"));
		dailyDao.insertDaily(daily);
		return "welcome";
	}
}