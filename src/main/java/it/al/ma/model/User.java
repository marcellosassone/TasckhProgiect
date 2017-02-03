package it.al.ma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

 @Id
 @Column(name = "id")
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 @Column(name = "salutation")
 private String salutation;
 @Column(name = "firstname")
 private String firstname;
 @Column(name = "lastname")
 private String lastname;
 @Column(name = "street")
 private String street;
 @Column(name = "housenumber")
 private Integer housenumber;
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

 public User() {
  super();
 }

 public User(String salutation, String firstname, String lastname, String street, Integer housenumber, String city,
   int country, String email, String password, int admin) {
  super();

  this.salutation = salutation;
  this.firstname = firstname;
  this.lastname = lastname;
  this.street = street;
  this.housenumber = housenumber;
  this.city = city;
  this.country = country;
  this.email = email;
  this.password = password;
  this.admin = admin;
 }

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

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + admin;
  result = prime * result + ((city == null) ? 0 : city.hashCode());
  result = prime * result + country;
  result = prime * result + ((email == null) ? 0 : email.hashCode());
  result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
  result = prime * result + housenumber;
  result = prime * result + id;
  result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
  result = prime * result + ((password == null) ? 0 : password.hashCode());
  result = prime * result + ((salutation == null) ? 0 : salutation.hashCode());
  result = prime * result + ((street == null) ? 0 : street.hashCode());
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
  User other = (User) obj;
  if (admin != other.admin)
   return false;
  if (city == null) {
   if (other.city != null)
    return false;
  } else if (!city.equals(other.city))
   return false;
  if (country != other.country)
   return false;
  if (email == null) {
   if (other.email != null)
    return false;
  } else if (!email.equals(other.email))
   return false;
  if (firstname == null) {
   if (other.firstname != null)
    return false;
  } else if (!firstname.equals(other.firstname))
   return false;
  if (housenumber != other.housenumber)
   return false;
  if (id != other.id)
   return false;
  if (lastname == null) {
   if (other.lastname != null)
    return false;
  } else if (!lastname.equals(other.lastname))
   return false;
  if (password == null) {
   if (other.password != null)
    return false;
  } else if (!password.equals(other.password))
   return false;
  if (salutation == null) {
   if (other.salutation != null)
    return false;
  } else if (!salutation.equals(other.salutation))
   return false;
  if (street == null) {
   if (other.street != null)
    return false;
  } else if (!street.equals(other.street))
   return false;
  return true;
 }

 @Override
 public String toString() {
  return "User [id=" + id + ", salutation=" + salutation + ", firstname=" + firstname + ", lastname=" + lastname
    + ", street=" + street + ", housenumber=" + housenumber + ", city=" + city + ", country=" + country
    + ", email=" + email + ", password=" + password + ", admin=" + admin + "]";
 }


 
 
 

}