package it.al.ma.dao;

import java.util.List;
import java.util.Map;

import it.al.ma.model.User;

public interface UserDao {

	//ciao Marco
	void insertUser(User user);

	   void updateUser(User user);

	   void deleteUser(User user);

	   User findByIdUser(User user);
	   
	   User findByMailAndPassword(User user);

	   List<User> findAllUser();
	   
	   Map<Integer,String> getCountryMap();
}
