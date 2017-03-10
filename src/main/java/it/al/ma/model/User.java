package it.al.ma.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;





@Entity
@Table(name = "user")
public class User {

 @Id
 @Column(name = "id", nullable=false)
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
@Column(name = "matricola")
 private String matricola; 
 @Column(name = "salutation")
 private String salutation;
 @Column(name = "firstname")
 private String firstname;
 @Column(name = "lastname")
 private String lastname;
 @Column(name = "cf")
 private String cf;
 @Column(name = "street")
 private String street;
 @Column(name = "housenumber")
 private Integer housenumber;
 @Column(name = "cap")
 private String cap;
 @Column(name = "city")
 private String city;
 @Column(name = "country")
 private int country;
 @Column(name = "email")
 private String email;
 @Column(name = "password")
 private String password;
 @Column(name = "admin")
 private int admin;
 @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	@OrderBy("data DESC")
	private Set<Documento> documenti;

 public User() {
  super();
 }

// public User(String salutation, String firstname, String lastname, String cf, String street, Integer housenumber, int cap, String city,
//   int country, String email, String password, int admin) {
//  super();
//
//  this.salutation = salutation;
//  this.firstname = firstname;
//  this.lastname = lastname;
//  this.street = street;
//  this.housenumber = housenumber;
//  this.city = city;
//  this.country = country;
//  this.email = email;
//  this.password = password;
//  this.admin = admin;
//  this.cf=cf;
//  this.cap=cap;
// }

 public User(String email, String password) {
  super();
  this.email = email;
  this.password = password;
 }

 public User(int id) {
  this.id = id;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
 public String getMatricola() {
	return matricola;
}

public void setMatricola(String matricola) {
	this.matricola = matricola;
}
 public String getSalutation() {
  return salutation;
 }

 public void setSalutation(String salutation) {
  this.salutation = salutation;
 }

 public String getFirstname() {
  return firstname;
 }

 public void setFirstname(String firstname) {
  this.firstname = firstname;
 }

 public String getLastname() {
  return lastname;
 }

 public void setLastname(String lastname) {
  this.lastname = lastname;
 }

 public String getStreet() {
  return street;
 }

 public void setStreet(String street) {
  this.street = street;
 }

 public Integer getHousenumber() {
  return housenumber;
 }

 public void setHousenumber(Integer housenumber) {
  this.housenumber = housenumber;
 }

 public String getCity() {
  return city;
 }

 public void setCity(String city) {
  this.city = city;
 }

 public int getCountry() {
  return country;
 }

 public void setCountry(int country) {
  this.country = country;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public int getAdmin() {
  return admin;
 }

 public void setAdmin(int admin) {
  this.admin = admin;
 }
 
 public Set<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(Set<Documento> documenti) {
		this.documenti = documenti;
	}

 public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + admin;
	result = prime * result + ((cap == null) ? 0 : cap.hashCode());
	result = prime * result + ((cf == null) ? 0 : cf.hashCode());
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + country;
	result = prime * result + ((documenti == null) ? 0 : documenti.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
	result = prime * result + ((housenumber == null) ? 0 : housenumber.hashCode());
	result = prime * result + id;
	result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((salutation == null) ? 0 : salutation.hashCode());
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	return result;
}



 @Override
 public String toString() {
  return "User [id=" + id + ", salutation=" + salutation + ", firstname=" + firstname + ", lastname=" + lastname
    + ", street=" + street + ", housenumber=" + housenumber + ", city=" + city + ", country=" + country
    + ", email=" + email + ", password=" + password + ", admin=" + admin + "]";
 }


 @Override
	public Object clone() throws CloneNotSupportedException {
		User cloned = (User) super.clone();
		cloned.setDocumenti(new TreeSet<Documento>(this.getDocumenti()));
		return cloned;
	}
 
 

}