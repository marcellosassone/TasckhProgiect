package it.al.ma.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dailytime")
public class DailyTime {


	@Id
	@Column(name = "idtime")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtime;
	@Column(name = "iduser")
	private int iduser;
	@Column(name = "data")
	private Date data;
	@Column(name = "firstshiftstart")
	private String firstshiftstart;
	@Column(name = "firstshiftstop")
	private String firstshiftstop;
	@Column(name = "secondshiftstart")
	private String secondshiftstart;
	@Column(name = "secondshiftstop")
	private String secondshiftstop;
	@Column(name="codpermesso")
	private String codpermesso;




	public DailyTime() {
		// default constructor explicitly declared for framwokk purposes
	}

	public int getIdtime() {
		return idtime;
	}
	public void setIdtime(int idtime) {
		this.idtime = idtime;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getFirstshiftstart() {
		return firstshiftstart;
	}
	public void setFirstshiftstart(String firstshiftstart) {
		this.firstshiftstart = firstshiftstart;
	}
	public String getFirstshiftstop() {
		return firstshiftstop;
	}
	public void setFirstshiftstop(String firstshiftstop) {
		this.firstshiftstop = firstshiftstop;
	}
	public String getSecondshiftstart() {
		return secondshiftstart;
	}
	public void setSecondshiftstart(String secondshiftstart) {
		this.secondshiftstart = secondshiftstart;
	}
	public String getSecondshiftstop() {
		return secondshiftstop;
	}
	public void setSecondshiftstop(String secondshiftstop) {
		this.secondshiftstop = secondshiftstop;
	}
	public String getCodpermesso() {
		return codpermesso;
	}
	public void setCodpermesso(String codpermesso) {
		this.codpermesso = codpermesso;
	}
	
	

	@Override
	public String toString() {
		return "DailyTime [idtime=" + idtime + ", iduser=" + iduser + ", data=" + data + ", firstshiftstart="
				+ firstshiftstart + ", firstshiftstop=" + firstshiftstop + ", secondshiftstart=" + secondshiftstart
				+ ", secondshiftstop=" + secondshiftstop + ", codpermesso=" + codpermesso + "]";
	}


}
