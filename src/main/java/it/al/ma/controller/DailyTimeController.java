package it.al.ma.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DailyTimeDao;
import it.al.ma.dao.DocumentoDAO;
import it.al.ma.dao.UserDao;
import it.al.ma.model.DailyTime;
import it.al.ma.model.Documento;
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
		
		int anno= now.get(Calendar.YEAR);
		modelV.getModelMap().addAttribute("currYear", anno);
		modelV.getModelMap().addAttribute("prevYear", anno-1);
		modelV.getModelMap().addAttribute("currMonth", now.get(Calendar.MONTH));
		modelV.getModelMap().addAttribute("user", user);
		return modelV;
	}
	
	@RequestMapping(value = "user/compileTimesheet", method = RequestMethod.POST)
	public ModelAndView compileTimesheet(@RequestParam("currMonth") int month,@RequestParam("currYear") int year,@RequestParam(value= "idUser",required=false) int idUser,  HttpServletRequest req) {
		if (req.getSession().getAttribute("admin").equals("admin"))
			return viewTimesheet(idUser,month,year,req);
		DailyTime daily = new DailyTime();
		daily.setData(new Date(new java.util.Date().getTime()));
		daily.setFirstshiftstart("09:00");
		daily.setFirstshiftstop("13:00");
		daily.setSecondshiftstart("14:00");
		daily.setSecondshiftstop("18:00");
//		if (user==null)
		User user= new User();
		//user.setId((int) req.getSession().getAttribute("id"));
		user.setId(idUser);
		
		Calendar now = Calendar.getInstance();
		int annocorrente=now.get(Calendar.YEAR);
		now.set(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.MONTH,month);
		now.set(Calendar.YEAR,year);
		
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		req.setAttribute("defaultMonth", month);
		
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		modelV.getModelMap().addAttribute("currMonth", month);
		modelV.getModelMap().addAttribute("user", user);
		
		int prevyear = (annocorrente==year)?year-1:year+1;
		modelV.getModelMap().addAttribute("currYear", year);
		modelV.getModelMap().addAttribute("prevYear",prevyear );
		if (req.getSession().getAttribute("admin").equals("admin"))
			modelV.getModelMap().addAttribute("hide","True");
		modelV.getModelMap().addAttribute("hide","False");
		
		return modelV;
	}
	
	@RequestMapping(value = "admin/compileTimesheet/{id}", method = RequestMethod.GET)
	public ModelAndView viewTimesheet(@PathVariable int id, @RequestParam("currMonth") int month,@RequestParam("currYear") int year, HttpServletRequest req) {
		DailyTime daily = new DailyTime();

		System.out.println(month);
		System.out.println(year);
		User user= new User();
		user.setId(id);
		user=userDao.findByIdUser(user);
		
		Calendar now = Calendar.getInstance();
		int annocorrente=now.get(Calendar.YEAR);
		
		if (month==0)
			month=now.get(Calendar.MONTH);
		now.set(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.MONTH,month);
		if (year!=0) 
			now.set(Calendar.YEAR,year);
		Date start=new Date(now.toInstant().toEpochMilli());	
		now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date end=new Date(now.toInstant().toEpochMilli());
		
		req.setAttribute("defaultMonth", month);
		ModelAndView modelV= new ModelAndView("compileDT","formDailyTime", daily);
		modelV.getModelMap().addAttribute("listTimesheet", dailyDao.findByIdUser(user, start, end));
		modelV.getModelMap().addAttribute("user", user);
		
		modelV.getModelMap().addAttribute("currMonth", month);
		int prevyear = (annocorrente==year)?year-1:year+1;
		modelV.getModelMap().addAttribute("currYear", year);
		modelV.getModelMap().addAttribute("prevYear",prevyear );	
		modelV.getModelMap().addAttribute("hide","True");
		return modelV;
	}
	
	
	@RequestMapping(value = "user/finalizeCompile", method=RequestMethod.POST)
	public String insertDaily(DailyTime daily,HttpServletRequest req) {
		daily.setIduser((int) req.getSession().getAttribute("id"));
		dailyDao.insertDaily(daily);
		return "redirect:/user/compileTimesheet/";
	}
	
//	@RequestMapping(value = "timesheetStamp/{id}", method=RequestMethod.POST)
//	public String timesheetStamp(@RequestParam("currMonth") int month, @PathVariable int id, HttpServletRequest req) {
//		User user= new User();
//		user.setId(id);
//		User newuser=userDao.findByIdUser(user);
//		System.out.println(newuser);
//		System.out.println("MESE - "+ month);
//		System.out.println(" metodo timestamp --- "+id);
////		XLSXReaderWriter.writeXlsx(newuser,month);
////		XLSXReaderWriter.readXlsx(month);
//		System.out.println(req.getSession().getAttribute("admin"));
//		if (req.getSession().getAttribute("admin").equals("admin"))
//			return "redirect:/admin/compileTimesheet/{id}";
//		return "redirect:/user/compileTimesheet";
//	}
	
	@Autowired
	private DocumentoDAO documentoDao;
	@RequestMapping(value = "timesheetDownload/{id}", method=RequestMethod.POST)
	public String timesheetStamp(@PathVariable int id, HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		//System.out.println("timesheetdownl ID"+ id);
		User user = new User();
		user.setId(id);
		user= userDao.findByIdUser(user);
		User admin = new User();
		admin.setAdmin(1);
		List<User> listAdmin = userDao.findAdmin(admin);
		Set<Documento> listDoc = new HashSet<Documento>();
		for(User adm:listAdmin){
			listDoc = documentoDao.listaPrivata(adm);
			for(Documento d:listDoc){
				if (d.getDescrizione().equals("TIME")){
					
					res.setHeader("Content-Disposition", "inline;filename=\"" +user.getLastname()+ "_" + d.getNome()+ "\"");
			
					InputStream is = d.getFile().getBinaryStream();
					OutputStream out=null;
					
					out = XLSXReaderWriter.writeXlsx(is,res.getOutputStream(),user);
					
					out = res.getOutputStream();
					res.setContentType(d.getNome());
					IOUtils.copy(is, out);
					
					out.flush();
					out.close();
				}	
				return null;
			}
			return null;
		}		
		return null;
	}
}