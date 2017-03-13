package it.al.ma.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DocumentoDAO;
import it.al.ma.dao.UserDao;
import it.al.ma.model.Documento;
import it.al.ma.model.User;


/* controller dedicato allo user*/
@Controller
public class UserController {

	@Autowired
	private UserDao userDao; 


	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String insertUser(User user, ModelMap model, HttpServletRequest req) {
		userDao.insertUser(user);
		//req.getSession().setAttribute("firstname", user.getFirstname());
		//req.getSession().setAttribute("lastname", user.getLastname());
		//user.setId(userDao.findByMailAndPassword(user).getId());
		//req.getSession().setAttribute("id", user.getId());
		//model.addAttribute(user.getId());

		return "redirect:/admin/load";
	}

	@RequestMapping(value="/insertUserFromAdmin", method=RequestMethod.POST)
	public String insertUserFromAdmin(User user, ModelMap model, HttpServletRequest req) {
		userDao.insertUser(user);
		//		req.getSession().setAttribute("firstname", user.getFirstname());
		//		req.getSession().setAttribute("lastname", user.getLastname());
		//		user.setId(userDao.findByMailAndPassword(user).getId());
		//		req.getSession().setAttribute("id", user.getId());
		//		model.addAttribute(user.getId());

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
		if (req.getSession().getAttribute("admin").equals("admin"))
			model.addAttribute("hide","True");
		else 
			model.addAttribute("hide","False");
		return new ModelAndView("ModUser", "formUserMod", user1);
	}

	@RequestMapping(value="admin/ModUser/{id}", method=RequestMethod.POST)
	public ModelAndView updateUserFromAdmin(@PathVariable int id,ModelMap model,HttpServletRequest req) {
		model.addAttribute("ListaCountry", userDao.getCountryMap());
		User user=new User();
		user.setId(id);

		User user1=userDao.findByIdUser(user);

		if (req.getSession().getAttribute("admin").equals("admin"))
			model.addAttribute("hide","True");
		else 
			model.addAttribute("hide","False");
		return new ModelAndView("ModUser", "formUserMod", user1);
	}

	@RequestMapping(value="finalizeUpdateUser", method=RequestMethod.POST)
	public String finalizeUpdate(User user, ModelMap model,HttpServletRequest req) {
		userDao.updateUser(user);
		if (req.getSession().getAttribute("admin").equals("admin"))
			return "redirect:/admin/load";
		else{
			req.getSession().setAttribute("firstname", user.getFirstname());
			req.getSession().setAttribute("lastname", user.getLastname());
		}
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
		Calendar now = Calendar.getInstance();
		model.addAttribute("currYear", now.get(Calendar.YEAR));
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

	@Autowired
	private DocumentoDAO documentoDao;
	@RequestMapping(value = "/user/loadAvatar/{id}", method = RequestMethod.GET)
	public String loadAvatar(@PathVariable int id, HttpServletRequest req, HttpServletResponse res) {	

		User u= new User();
		Documento d = new Documento();
		u.setId(id);
		//d.setTipo("AVATAR");
		//Documento doc = documentoDao.cercaDoc(d);
		//System.out.println("USER "+ doc.getUser());
		List<Documento> lstDoc= new ArrayList<Documento>(documentoDao.listaPrivata(u));
		for(Documento d1:lstDoc){
			if (d1.getTipo().equalsIgnoreCase("AVATAR"))
				d=d1;
		}
		String defaultImg="img/avatar.jpg";		
		try {
			if(d.getNome()!=null){			
				res.setHeader("Content-Disposition", "inline;filename=\"" + d.getNome()+ "\"");

				InputStream is = d.getFile().getBinaryStream();

				OutputStream out=null;

				out = res.getOutputStream();
				res.setContentType(d.getNome());
				IOUtils.copy(is, out);

				out.flush();
				out.close();
			}
			else
			{
				//Carico l'immagine di default nella cartella resource.
				ClassLoader classLoader = getClass().getClassLoader();
				FileInputStream file = new FileInputStream(classLoader.getResource(defaultImg).getFile());
				
				res.setHeader("Content-Disposition", "inline;filename=\"avatar.jpg\"");
				InputStream is = file;

				OutputStream out=null;

				out = res.getOutputStream();
				res.setContentType("avatar.jpg");	

				IOUtils.copy(is, out);

				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}




}
