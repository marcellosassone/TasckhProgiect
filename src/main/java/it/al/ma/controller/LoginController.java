package it.al.ma.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.UserDao;
import it.al.ma.model.User;



@Controller
public class LoginController {

 @Autowired
 private UserDao userDao;
 
 private static final String ADMIN = "admin";
 private static final String FORMUSER = "formUser";
 
// @RequestMapping(value = "/", method = RequestMethod.GET)
// public ModelAndView index(ModelMap model) throws IOException{
//	 model.addAttribute("ListaCountry", userDao.getCountryMap());
//	 ModelAndView mav=new ModelAndView("index","formUserSignIn", new User());
//	 mav.getModelMap().addAttribute(FORMUSER, new User());
//	 
//  return mav;
// }

 
 @RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView goToIndex() {
	
		return new ModelAndView("index", FORMUSER, new User());
	}
 

 @RequestMapping(value="/login", method=RequestMethod.POST)
 public ModelAndView verifyLogin(User user, ModelMap model, HttpServletRequest req) {
  if (req.getSession().getAttribute("id")==null){
	  
  
	 User d = userDao.findByMailAndPassword(user);
  
  if(d == null) {
	  model.addAttribute("errore", "Email or password error.");
	  ModelAndView mav=new ModelAndView("index","formUser", new User());
		 mav.getModelMap().addAttribute(FORMUSER, new User());
	  return mav;
	 }
  if(d.getAdmin() == 1) {
		req.getSession().setAttribute("firstname", d.getFirstname());
		req.getSession().setAttribute("lastname", d.getLastname());
		req.getSession().setAttribute(ADMIN, ADMIN);
		return new ModelAndView("redirect:admin/load");
	}
  req.getSession().setAttribute(ADMIN, "user");
  req.getSession().setAttribute("firstname", d.getFirstname());
  req.getSession().setAttribute("lastname", d.getLastname());
  req.getSession().setAttribute("id", d.getId());
  
  model.addAttribute(d.getId());
  }
 return new ModelAndView("welcome", FORMUSER, new User());
 }
 
}
