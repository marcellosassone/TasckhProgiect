package it.al.ma.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.UserDao;
import it.al.ma.dao.UserDaoImpl;
import it.al.ma.model.User;



@Controller
public class LoginController {

 @Autowired
 private UserDaoImpl dao;
 
 
 @RequestMapping(value = "/", method = RequestMethod.GET)
 public ModelAndView index(ModelMap model){
	 model.addAttribute("ListaCountry", dao.getCountryMap());
	 ModelAndView mav=new ModelAndView("index","formUserSignIn", new User());
	 mav.getModelMap().addAttribute("formUser", new User());
  return mav;
 }
 


 @RequestMapping(value="/login", method=RequestMethod.POST)
 public ModelAndView verifyLogin(User user, ModelMap model, HttpServletRequest req) {
  User d = dao.findByMailAndPassword(user);
  
  if(d == null) {
	  model.addAttribute("errore", "Email or password error.");
	  ModelAndView mav=new ModelAndView("index","formUserSignIn", new User());
		 mav.getModelMap().addAttribute("formUser", new User());
	  return mav;
	 }
  if(d.getAdmin() == 1) {
		req.getSession().setAttribute("firstname", d.getFirstname());
		req.getSession().setAttribute("lastname", d.getLastname());
		req.getSession().setAttribute("admin", "admin");
		return new ModelAndView("redirect:admin/load");
	}
  
  req.getSession().setAttribute("firstname", d.getFirstname());
  req.getSession().setAttribute("lastname", d.getLastname());
  req.getSession().setAttribute("id", d.getId());
  
  model.addAttribute(d.getId());
  
 return new ModelAndView("welcome", "formUser", new User());
 }
 
}
