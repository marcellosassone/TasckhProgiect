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
import it.al.ma.dao.UserDao;
import it.al.ma.model.DailyTime;
import it.al.ma.model.User;
import it.al.ma.util.XLSXReaderWriter;

@Controller
public class DailyTimeController {
	
	@Autowired
	private DailyTimeDao dailyDao; 
	
	@Autowired
	private UserDao userDao; 
	/***
	 * metodo viene richiamato la prima volta dalla pagina dell'Utente 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.GET)
	public ModelAndView compileTimesheet(HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		daily.setData(new Date(new java.util.Date().getTime()));
		daily.setFirstshiftstart("09:00");
		daily.setFirstshiftstop("13:00");
		daily.setSecondshiftstart("14:00");
		daily.setSecondshiftstop("18:00");
		User user= new User();
		user.setId((int) req.getSession().getAttribute("id"));
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 1);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		req.setAttribute("defaultMonth", now.get(Calendar.MONTH));
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		modelV.getModelMap().addAttribute("currMonth", now.get(Calendar.MONTH));
		return modelV;
	}
	
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.POST)
	public ModelAndView compileTimesheet(@RequestParam("currMonth") int month, HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		User user= new User();
		user.setId((int) req.getSession().getAttribute("id"));
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.MONTH,month);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		req.setAttribute("defaultMonth", month);
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		modelV.getModelMap().addAttribute("currMonth", month);
		return modelV;
	}
	
	@RequestMapping(value = "admin/compileTimesheet/{id}", method = RequestMethod.GET)
	public ModelAndView viewTimesheet(@PathVariable int id, HttpServletRequest req) {
		DailyTime daily = new DailyTime();
		User user= new User();
		user.setId(id);
		
		Calendar now = Calendar.getInstance();
		int month=now.get(Calendar.MONTH);
		now.set(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.MONTH,month);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		req.setAttribute("defaultMonth", month);
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		modelV.getModelMap().addAttribute("currMonth", month);
		return modelV;
	}
	@RequestMapping(value = "user/finalizeCompile", method=RequestMethod.POST)
	public String insertDaily(DailyTime daily,HttpServletRequest req) {
		daily.setIduser((int) req.getSession().getAttribute("id"));
		dailyDao.insertDaily(daily);
		return "redirect:/user/compileTimesheet";
	}
	
	@RequestMapping(value = "timesheetStamp/{id}", method=RequestMethod.POST)
	public String timesheetStamp(@RequestParam("currMonth") int month, @PathVariable int id, HttpServletRequest req) {
		User user= new User();
		user.setId(id);
		User newuser=userDao.findByIdUser(user);
		System.out.println(newuser);
		System.out.println("MESE - "+ month);
		System.out.println(" metodo timestamp --- "+id);
		XLSXReaderWriter.writeXlsx(newuser,month);
		XLSXReaderWriter.readXlsx(month);
		System.out.println(req.getSession().getAttribute("admin"));
		if (req.getSession().getAttribute("admin").equals("admin"))
			return "redirect:/admin/compileTimesheet/{id}";
		return "redirect:/user/compileTimesheet";
	}
	
}