package it.al.ma.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.UserDao;
import it.al.ma.model.User;


/* controller dedicato allo user*/
@Controller
public class UserController {

	@Autowired
	private UserDao userDao; 


	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String insertUser(User user, ModelMap model, HttpServletRequest req) {
		userDao.insertUser(user);
		req.getSession().setAttribute("firstname", user.getFirstname());
		req.getSession().setAttribute("lastname", user.getLastname());
		user.setId(userDao.findByMailAndPassword(user).getId());
		req.getSession().setAttribute("id", user.getId());
		model.addAttribute(user.getId());

		return "welcome";
	}
	
	@RequestMapping(value="/insertUserFromAdmin", method=RequestMethod.POST)
	public String insertUserFromAdmin(User user, ModelMap model, HttpServletRequest req) {
		userDao.insertUser(user);
		req.getSession().setAttribute("firstname", user.getFirstname());
		req.getSession().setAttribute("lastname", user.getLastname());
		user.setId(userDao.findByMailAndPassword(user).getId());
		req.getSession().setAttribute("id", user.getId());
		model.addAttribute(user.getId());

		return "redirect:/admin/load";
	}
	
	
	@RequestMapping(value="admin/insertUser", method=RequestMethod.GET)
		 public ModelAndView index(ModelMap model){
		 model.addAttribute("ListaCountry", userDao.getCountryMap());
		 ModelAndView mav=new ModelAndView("newUser","formUserSignIn", new User());
		 mav.getModelMap().addAttribute("formUser", new User());
	  return mav;
	 }

	@RequestMapping(value = "user/delUser", method = RequestMethod.GET)
	public String delUser(ModelMap model, HttpServletRequest req) {
		try {
			User d=new User();
			d.setId((int)req.getSession().getAttribute("id"));
			userDao.deleteUser(d);
		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception xxx) {
			xxx.printStackTrace();
		}
		return "redirect:/";

	}

	@RequestMapping(value = "admin/delUser/{id}", method = RequestMethod.POST)
	public String delUserFromAdmin(@PathVariable int id) {
		try {

			User d=new User();
			d.setId(id);
			userDao.deleteUser(d);

		} catch (HibernateException e) {
			System.out.println("Rilevato Errore: " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception xxx) {
			xxx.printStackTrace();
		}
		return "redirect:/admin/load";

	}

	@RequestMapping(value="user/ModUser", method=RequestMethod.GET)
	public ModelAndView updateUser(ModelMap model, HttpServletRequest req) {
		model.addAttribute("ListaCountry", userDao.getCountryMap());
		User user=new User();
		user.setId((int)req.getSession().getAttribute("id"));
		User user1=userDao.findByIdUser(user);
		return new ModelAndView("ModUser", "formUserMod", user1);
	}

	@RequestMapping(value="admin/ModUser/{id}", method=RequestMethod.POST)
	public ModelAndView updateUserFromAdmin(@PathVariable int id,ModelMap model) {
		model.addAttribute("ListaCountry", userDao.getCountryMap());
		User user=new User();
		user.setId(id);
		user.setAdmin(1);
		//System.out.println(user);

		User user1=userDao.findByIdUser(user);
		//System.out.println(user);

		return new ModelAndView("ModUser", "formUserMod", user1);
	}

	@RequestMapping(value="finalizeUpdateUser", method=RequestMethod.POST)
	public String finalizeUpdate(User user, ModelMap model,HttpServletRequest req) {
		userDao.updateUser(user);
		req.getSession().setAttribute("firstname", user.getFirstname());
		req.getSession().setAttribute("lastname", user.getLastname());
		System.out.println(req.getSession().getAttribute("admin"));
		if (req.getSession().getAttribute("admin").equals("admin"))
			return "redirect:/admin/load";
		return "welcome";
		
	}
	
//	@RequestMapping(value="finalizeUpdateUser", method=RequestMethod.POST)
//	public String finalizeUpdateFromAdmin(User user, ModelMap model,HttpServletRequest req) {
//		userDao.updateUser(user);
//		req.getSession().setAttribute("firstname", user.getFirstname());
//		req.getSession().setAttribute("lastname", user.getLastname());
//		return "UserList";
//	}

	@RequestMapping(value="admin/load", method=RequestMethod.GET)
	public ModelAndView loadUser(ModelMap model) {
		model.addAttribute("list", userDao.findAllUser());
		model.addAttribute("listcountry", userDao.getCountryMap());
		model.addAttribute("currMonth", 0);
		return new ModelAndView("UserList", "formUser", new User());
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session= request.getSession(false);

		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
