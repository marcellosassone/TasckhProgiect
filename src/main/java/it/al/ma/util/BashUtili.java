package it.al.ma.util;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.al.ma.model.Country;

public class BashUtili {
 // Create session factory object

 static SessionFactory sessionFactory = HibernateUtil.getSf();

 public static void main(String[] args) throws SQLException {

  Session s1 = sessionFactory.openSession();

  BufferedReader br = null;
  Transaction trans = null;

  try {
   br = new BufferedReader(new FileReader(new File("Batch/country.txt")));
   String line = null;

   Country country = null;

   
   while ((line = br.readLine()) != null) {

    country = new Country();
    String cuntryNam = line.substring(3);
    country.setCountryname(cuntryNam.replace("\'", (cuntryNam.charAt(cuntryNam.indexOf('\'')+1) == 's') ? "" : " "));
    trans = s1.beginTransaction();
    s1.save(country);
    trans.commit();
   }

  } catch (IOException e) {
   e.printStackTrace();
  } finally {

   
   s1.close();

   try {

    if (br != null) {

     br.close();
     br = null;
    }

   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
}
