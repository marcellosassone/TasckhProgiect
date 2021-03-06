package it.al.ma.model;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="documento")
public class Documento {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="data")
	private Date data;
	@Column(name="nome")
	private String nome;
	@Column(name="tipo")
	private String tipo;
	@Column(name="descrizione")
	private String descrizione;
	@Column(name="file")
	private Blob file;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_user")
	private User user;
	
	
	
	public Documento() {
		//default 
	}
	
	public Documento(int id, Date data, String nome, String descrizione) {
		super();
		this.id = id;
		this.data = data;
		this.nome = nome;
		this.descrizione = descrizione.toUpperCase();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione.toUpperCase();
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
