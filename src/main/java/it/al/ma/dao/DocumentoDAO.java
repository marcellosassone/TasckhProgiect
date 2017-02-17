package it.al.ma.dao;

import java.util.List;
import java.util.Set;

import it.al.ma.model.Documento;
import it.al.ma.model.User;



public interface DocumentoDAO {
	
	public Documento cercaDoc(Documento doc);
	public void inserisciDoc(Documento doc);
	public void eliminaDoc(Documento doc);
	public void modificaDoc(Documento doc);
	public List<Documento> listaDoc();
	public Set<Documento> listaPrivata(User user);
}
