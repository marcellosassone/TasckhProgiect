package it.al.ma.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import it.al.ma.dao.DocumentoDAO;
import it.al.ma.dao.UserDao;
import it.al.ma.model.Documento;
import it.al.ma.model.User;



@Controller
public class DocumentoController {
	@Autowired
	private DocumentoDAO documentoDao;

	@Autowired
	private UserDao userDao;
	
	private Logger log = Logger.getLogger(DocumentoController.class);

	@RequestMapping(value = "/user/downloadDoc/{id}", method = RequestMethod.GET)
	public void downloadDoc(@PathVariable int id, HttpServletRequest req, HttpServletResponse res) {
		Documento d = new Documento();
		d.setId(id);
		Documento doc = documentoDao.cercaDoc(d);

		String mimeType = req.getServletContext().getMimeType(doc.getNome());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		res.setContentType(mimeType);
		try {
			res.setContentLength((int) doc.getFile().length());
		} catch (SQLException e) {
			log.error(e.getStackTrace());
		}

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", doc.getNome());
		res.setHeader(headerKey, headerValue);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = (ByteArrayInputStream) doc.getFile().getBinaryStream();

			os = res.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (SQLException | IOException e) {
			log.error("Download failed", e);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getStackTrace());
				}

			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					log.error(e.getStackTrace());
				}
		}
	}

	@RequestMapping(value = "/user/inserisciDoc", method = RequestMethod.POST)
	public String inserisciDoc(@RequestParam("file") MultipartFile file, Documento doc, HttpServletRequest req) {
		if (!file.isEmpty()) {
			File myFile = new File(file.getOriginalFilename());
			doc.setNome(myFile.getName());
			
			User user = new User();
			user.setId((int)req.getSession().getAttribute("id"));
			
			doc.setUser(user);
			try {
				Blob blob = new javax.sql.rowset.serial.SerialBlob(file.getBytes());
				doc.setFile(blob);
			} catch (SQLException | IOException e) {
				log.error(e.getStackTrace());
			}

			java.util.Date date = new java.util.Date();
			Date sqlDate = new Date(date.getTime());
			doc.setData(sqlDate);
			documentoDao.inserisciDoc(doc);
		}

		return  "welcome";
	}

	@RequestMapping(value = "/user/deleteDoc/{id}")
	public String eliminaDoc(@PathVariable int id) {
		Documento doc = new Documento();
		doc.setId(id);
		documentoDao.eliminaDoc(doc);
		return "redirect:/user/loadDoc";
	}

	@RequestMapping(value = "/user/loadDoc", method = RequestMethod.GET)
	public ModelAndView loadDoc(ModelMap model, HttpServletRequest req) {

		User user=new User();
		user.setId((int)req.getSession().getAttribute("id"));
		System.out.println("id utente" + user.getId());
		
		model.addAttribute("listaDoc", documentoDao.listaPrivata(user));
		
//		List<Documento> myOrdyyyList = new ArrayList<>(documentoDao.listaPrivata(user));
//		Collections.sort(myOrdyyyList, new Comparator<Documento>() {
//
//			@Override
//			public int compare(Documento arg0, Documento arg1) {
//				return arg0.getNome().compareTo(arg1.getNome());
//			}
//
//		});
		
		return new ModelAndView("gestioneDoc", "formDoc", new Documento());
	}

	@RequestMapping(value = "/user/sortDoc/name/{flag}", method = RequestMethod.GET)
	public ModelAndView sortDocName(@PathVariable("flag") String flag, ModelMap model, HttpSession session) {

		int id = (int) session.getAttribute("id");
		User user=new User();
		user.setId(id);
		List<Documento> myOrdyyyList = new ArrayList<>(documentoDao.listaPrivata(user));
		Collections.sort(myOrdyyyList, new Comparator<Documento>() {

			@Override
			public int compare(Documento arg0, Documento arg1) {
				if (flag.equalsIgnoreCase("ASC")) {
					model.addAttribute("nameSort", false);
					return arg0.getNome().compareTo(arg1.getNome());
				} else if(flag.equalsIgnoreCase("DESC")) {
					model.addAttribute("nameSort", true);
					return -arg0.getNome().compareTo(arg1.getNome());
				}
				
				return -1;
			}

		});
		model.addAttribute("listaDoc", myOrdyyyList);
		return new ModelAndView("gestioneDoc", "formDoc", new Documento());
	}
	
	@RequestMapping(value = "/user/sortDoc/date/{flag}", method = RequestMethod.GET)
	public ModelAndView sortDocDate(@PathVariable("flag") String flag, ModelMap model, HttpSession session) {

		int id = (int) session.getAttribute("id");
		User user=new User();
		user.setId(id);
		List<Documento> myOrdyyyList = null;
		if(flag.equalsIgnoreCase("ASC")) {
			model.addAttribute("dateSort", false);
			myOrdyyyList = new ArrayList<>(documentoDao.listaPrivata(user));
		} else if(flag.equalsIgnoreCase("DESC")) {
			model.addAttribute("dateSort", true);
			myOrdyyyList = new ArrayList<>(documentoDao.listaPrivata(user));
			Collections.sort(myOrdyyyList, new Comparator<Documento>() {

				@Override
				public int compare(Documento arg0, Documento arg1) {
						return arg0.getData().compareTo(arg1.getData());
				}

			});
		}
		
		model.addAttribute("listaDoc", myOrdyyyList);
		return new ModelAndView("gestioneDoc", "formDoc", new Documento());
	}


	@RequestMapping(value = "/user/updateDoc/{id}", method = RequestMethod.GET)
	public ModelAndView modificaDoc(@PathVariable int id, ModelMap model, HttpSession session) {
		model.addAttribute("editable", true);
		model.addAttribute("id_editable", id);
		int id2 = (int) session.getAttribute("id");
		User user=new User();
		user.setId(id2);
		model.addAttribute("listaDoc", documentoDao.listaPrivata(user));
		return new ModelAndView("gestioneDoc", "formDoc", new Documento());
	}

	
	
	@RequestMapping(value = "/user/finalizzaModificaDoc", method = RequestMethod.POST)
	public String finalizzaModifica(@RequestParam("descr") String descrizione, @RequestParam("id_editabile") int id,
			HttpServletRequest req) {
		log.error("ID EDITABILE:" + id);
		Documento d = new Documento();
		d.setId(id);
		Documento doc = documentoDao.cercaDoc(d);
		doc.setDescrizione(descrizione);
		// maybe opzionalia
		java.util.Date dataOdierna = new java.util.Date();
		Date dataSQL = new Date(dataOdierna.getTime());
		doc.setData(dataSQL);
		documentoDao.modificaDoc(doc);

		return "redirect:/user/loadDoc";
	}

//	@RequestMapping(value = "/user/filtraDocumenti", method = RequestMethod.POST)
//	public ModelAndView filtaDocumenti(@RequestParam("ricerca") String ricerca, HttpSession session, ModelMap model) {
//		int id = (int) session.getAttribute("id");
//		User user=new User();
//		user.setId(id);
//		Set<Documento> unfilteredDocuments = documentoDao.listaPrivata(user);
//		Set<Documento> filteredDocuments = new HashSet<>();
//		for (Documento doc : unfilteredDocuments) {
//			if (doc.getNome().contains(ricerca) || doc.getDescrizione().contains(ricerca)) {
//				filteredDocuments.add(doc);
//			}
//		}
//
//		model.addAttribute("listaDoc", filteredDocuments);
//		return new ModelAndView("gestioneDoc", "formDoc", new Documento());
//	}
//	
	@RequestMapping(value="/admin/dammiDoc/{id}", method=RequestMethod.GET)
	public String dammiDoc(@PathVariable("id") int id, ModelMap model) {
		
		User user=new User();
		user.setId(id);
		User theRealUser = userDao.findByIdUser(user);
		model.addAttribute("dipendente", theRealUser);
		model.addAttribute("listaDoc", documentoDao.listaPrivata(user));
		
		return "leggiDocs";
	}

}
