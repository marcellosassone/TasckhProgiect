package it.al.ma.model;

import java.sql.Date;
import java.sql.Time;

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
	 private Time firstshiftstart;
	 @Column(name = "firstshiftstop")
	 private Time firstshiftstop;
	 @Column(name = "secondshiftstart")
	 private Time secondshiftstart;
	 @Column(name = "secondshiftstop")
	 private Time secondshiftstop;
	 @Column(name="codpermesso")
	 private String codpermesso;
	
	 
	 
	 
	 public DailyTime() {
		
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
	public Time getFirstshiftstart() {
		return firstshiftstart;
	}
	public void setFirstshiftstart(Time firstshiftstart) {
		this.firstshiftstart = firstshiftstart;
	}
	public Time getFirstshiftstop() {
		return firstshiftstop;
	}
	public void setFirstshiftstop(Time firstshiftstop) {
		this.firstshiftstop = firstshiftstop;
	}
	public Time getSecondshiftstart() {
		return secondshiftstart;
	}
	public void setSecondshiftstart(Time secondshiftstart) {
		this.secondshiftstart = secondshiftstart;
	}
	public Time getSecondshiftstop() {
		return secondshiftstop;
	}
	public void setSecondshiftstop(Time secondshiftstop) {
		this.secondshiftstop = secondshiftstop;
	}
	public String getCodpermesso() {
		return codpermesso;
	}
	public void setCodpermesso(String codpermesso) {
		this.codpermesso = codpermesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codpermesso == null) ? 0 : codpermesso.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((firstshiftstart == null) ? 0 : firstshiftstart.hashCode());
		result = prime * result + ((firstshiftstop == null) ? 0 : firstshiftstop.hashCode());
		result = prime * result + idtime;
		result = prime * result + iduser;
		result = prime * result + ((secondshiftstart == null) ? 0 : secondshiftstart.hashCode());
		result = prime * result + ((secondshiftstop == null) ? 0 : secondshiftstop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyTime other = (DailyTime) obj;
		if (codpermesso == null) {
			if (other.codpermesso != null)
				return false;
		} else if (!codpermesso.equals(other.codpermesso))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (firstshiftstart == null) {
			if (other.firstshiftstart != null)
				return false;
		} else if (!firstshiftstart.equals(other.firstshiftstart))
			return false;
		if (firstshiftstop == null) {
			if (other.firstshiftstop != null)
				return false;
		} else if (!firstshiftstop.equals(other.firstshiftstop))
			return false;
		if (idtime != other.idtime)
			return false;
		if (iduser != other.iduser)
			return false;
		if (secondshiftstart == null) {
			if (other.secondshiftstart != null)
				return false;
		} else if (!secondshiftstart.equals(other.secondshiftstart))
			return false;
		if (secondshiftstop == null) {
			if (other.secondshiftstop != null)
				return false;
		} else if (!secondshiftstop.equals(other.secondshiftstop))
			return false;
		return true;
	}
	 
	 
	
	

}
