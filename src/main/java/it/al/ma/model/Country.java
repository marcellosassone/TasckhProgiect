package it.al.ma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {
  @Id
  @Column(name="idcountry")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Integer idcountry;
  @Column(name="countryname")
 private String countryname;
 
 public Country(){
  
 }
 public Country(String countryname) {
  super();
  this.countryname = countryname;
 }
 public Country(Integer idcountry) {
  super();
  this.idcountry = idcountry;
 }
 public Country(Integer idcountry, String countryname) {
  super();
  this.idcountry = idcountry;
  this.countryname = countryname;
 }
 public Integer getIdcountry() {
  return idcountry;
 }
 public void setIdcountry(Integer idcountry) {
  this.idcountry = idcountry;
 }
 public String getCountryname() {
  return countryname;
 }
 public void setCountryname(String countryname) {
  this.countryname = countryname;
 }
 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + ((countryname == null) ? 0 : countryname.hashCode());
  result = prime * result + idcountry;
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
  Country other = (Country) obj;
  if (countryname == null) {
   if (other.countryname != null)
    return false;
  } else if (!countryname.equals(other.countryname))
   return false;
  if (idcountry != other.idcountry)
   return false;
  return true;
 }
 @Override
 public String toString() {
  return "Country [idcountry=" + idcountry + ", countryname=" + countryname + "]";
 }
 
 
 

}